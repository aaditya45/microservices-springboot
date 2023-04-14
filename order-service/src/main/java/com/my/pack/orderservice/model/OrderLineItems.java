package com.my.pack.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;


@Entity
@Getter
@Setter
@Table(name = "t_order_line_items")
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //stock keeping unit code
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
