package com.my.pack.orderservice.service;

import com.my.pack.orderservice.dto.InventoryResponse;
import com.my.pack.orderservice.dto.OrderLineItemsDto;
import com.my.pack.orderservice.dto.OrderRequest;
import com.my.pack.orderservice.event.OrderPlacedEvent;
import com.my.pack.orderservice.model.Order;
import com.my.pack.orderservice.model.OrderLineItems;
import com.my.pack.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String,OrderPlacedEvent> kafkaTemplate;

    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<OrderLineItems> orderLineItemsList=orderRequest
                .getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);
        //getting the skuCodes
        List<String> skuCodes=order.getOrderLineItemsList()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();
        //call inventory service , and place order if product is in
        //stock
        //we use web client to communicate between services
        //we will use uriBuilder to build URI in a way we want and send to inventory controller
        //we can replace localhost:8082 with inventory-service now because we have service registry now.
        System.out.println("done");
        InventoryResponse[] result=webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory",
                        uriBuilder -> {
                            return uriBuilder.queryParam("skuCode",skuCodes).build();
                        })
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();
        boolean allProductsInStock = Arrays.stream(result).allMatch(InventoryResponse::getIsInStock);
        System.out.println("done");
        if(!allProductsInStock){
            throw new IllegalArgumentException("Product is not in stock, try again later.");
        }
        orderRepository.save(order);
        try{
            kafkaTemplate.send("notificationTopic",new OrderPlacedEvent(order.getOrderNumber()));
        }catch (Exception excepion){
            System.out.println(excepion.getMessage());
        }

    }
    public OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto){
        OrderLineItems orderLineItems=new OrderLineItems();
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }
}
