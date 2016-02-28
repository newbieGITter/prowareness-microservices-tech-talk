package com.prowareness.example.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class InventoryServiceImpl implements InventoryService {

	@Override
	public boolean isValidInventory(String inventoryId) {
		if(inventoryId == null || inventoryId == ""){
			return false;
		}
		return true;
	}

	
}
