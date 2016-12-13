package com.epam.store.bean;

import java.util.HashMap;
import java.util.Map;

public class Shop {
	private Map<SportEquipment, Integer> goods;
	private Map<String, SportEquipment> equipmentInShop;
	private static Shop instance = null;
	
	private Shop() {
		goods = new HashMap<SportEquipment, Integer>();
		equipmentInShop = new HashMap<String, SportEquipment>();
	}
	
	public static Shop getInstance() {
		if(instance == null){
			instance = new Shop();
		}
		return instance;
	}

	public Map<SportEquipment, Integer> getGoods() {
		return goods;
	}
	
	public Map<String, SportEquipment> getEquipmentInShop() {
		return equipmentInShop;
	}
	
}
