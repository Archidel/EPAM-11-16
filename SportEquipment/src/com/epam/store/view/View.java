package com.epam.store.view;

import com.epam.store.bean.EquipmentRequest;
import com.epam.store.bean.RegisterClientRequest;
import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.command.exception.CommandNotFoundException;
import com.epam.store.controller.Controller;

public class View {
	
	private static final Controller controller = new Controller();
	
	public static void main(String [] args){
		Response response = null;

		//инициализация коннекшен пула - инициализация магазина
		Request initReq = new Request();
		initReq.setCommand("initialization");
		
		try {
			response = controller.doAction(initReq);
			PrintAnswer.Dispatcher(response);  // вывод ответа в консоль
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		
		//Добавить товары в магазин
		Equipment equipment = new Equipment();
		equipment.setTitle("Санки");
		equipment.setCategory("Sport equipment");
		equipment.setPrice(12);
		equipment.setQuantity(20);
		
		EquipmentRequest equipmentRequest = new EquipmentRequest();
		equipmentRequest.setCommand("add_new_equipment");
		equipmentRequest.setEquipment(equipment);
		
		try {
			response = controller.doAction(equipmentRequest);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//регистрация клиента который хочет взять снаряжения в аренду
		RegisterClientRequest registerClient = new RegisterClientRequest();
		registerClient.setCommand("register_client");
		registerClient.setName("Alex");
		registerClient.setSurname("Zayac");
		
		try {
			response = controller.doAction(registerClient);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
				
		//Показать список всех товараов что есть в наличии
		Request getEquipmentList = new Request();
		getEquipmentList.setCommand("get_equipment_list");
		
		try {
			response = controller.doAction(getEquipmentList);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		
		//Взять 3 предмета в аренду а потом еще затребовать 4й.
		
	/*	TakeEquipmentRequest takeEquipmentRequest = new TakeEquipmentRequest();
		takeEquipmentRequest.setCommand(NameCommand.TAKE_EQUIPMENT_CMD);
		takeEquipmentRequest.setTitle("Парашют");
		
	/*	try {
			response = controller.doAction(takeEquipmentRequest);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
	*/	
	}
}

/*
1. регистрация
2. показать лист 
3. взять 3 предмета 
	потом еще 1
4. показать список арендованных 

*/