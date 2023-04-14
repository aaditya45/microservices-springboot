package com.my.pack.inventoryservice.controller;

import com.my.pack.inventoryservice.dto.InventoryResponse;
import com.my.pack.inventoryservice.model.Inventory;
import com.my.pack.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {


    private final InventoryService inventoryService;

    //request parameter
    //http://localhost:8082/api/inventory?skuCode=iphone_13&skuCode=iphone_13_red
    @GetMapping
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }
}
