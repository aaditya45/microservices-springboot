package com.my.pack.inventoryservice;

import com.my.pack.inventoryservice.model.Inventory;
import com.my.pack.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	//use this once
	//@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository){
		System.out.println("Inventory Service has been working on port 8082, inventory is initialized.");
		return args -> {
			Inventory inventory=new Inventory();
			inventory.setSkuCode("iphone_13");
			inventory.setQuantity(100);
			Inventory inventory1=new Inventory();
			inventory1.setSkuCode("iphone_13_red");
			inventory1.setQuantity(0);
			Inventory inventory2=new Inventory();
			inventory2.setSkuCode("iphone_12_white");
			inventory2.setQuantity(10);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
			inventoryRepository.save(inventory2);
		};
	}
}
