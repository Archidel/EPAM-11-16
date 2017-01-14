package com.epam.store.view;

import com.epam.store.bean.EquipmentRequest;
import com.epam.store.bean.EquipmentResponse;
import com.epam.store.bean.RegisterClientRequest;
import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.bean.RentEquipmentRequest;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.bean.entity.Rent;
import com.epam.store.controller.Controller;
import com.epam.store.controller.command.exception.CommandNotFoundException;
import com.epam.store.controller.command.impl.RentEquipment;
import com.epam.store.controller.logging.StoreLogger;

public class View {
	
	private static final Controller controller = new Controller();
	
	public static void main(String [] args){
		Response response = null;
		StoreLogger.getInstance(); // инициализация логгера
		
		//*****************************Инициализация магазина (connection poll)*****************************//
		Request initReq = new Request();
		initReq.setCommand("initialization");
		
		try {
			response = controller.doAction(initReq);
			PrintAnswer.Dispatcher(response);  // вывод ответа в консоль
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		
		//*****************************Добавить товары в магазин*****************************//
	/*	Equipment equipment = new Equipment();
		equipment.setTitle("Коньки");
		equipment.setCategory("Sport equipment");
		equipment.setPrice(20);
		equipment.setQuantity(10);
		
		EquipmentRequest equipmentRequest = new EquipmentRequest();
		equipmentRequest.setCommand("add_new_equipment");
		equipmentRequest.setEquipment(equipment);
		
		try {
			response = controller.doAction(equipmentRequest);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e1) {
			e1.printStackTrace();
		}
	*/	
		//*****************************Регистрация клиента который хочет взять снаряжения в аренду*****************************//
	/*	RegisterClientRequest registerClient = new RegisterClientRequest();
		registerClient.setCommand("register_client");
		registerClient.setName("qwerty");
		registerClient.setSurname("qwerty");
		
		try {
			response = controller.doAction(registerClient);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		*/
		
		//*****************************Показать список всех товараов что есть в наличии*****************************//
	/*	Request getEquipmentList = new Request();
		getEquipmentList.setCommand("get_equipment_list");
		
		try {
			response = controller.doAction(getEquipmentList);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
	*/
		//*****************************Берём в аренду снаряжения*****************************//
		/*	БАГ
		 * Нет высчита total price for rent table(подсчёт дней из внесённой даты)
		 *//*
		Client clinet_1 = new Client();
		clinet_1.setName("qwerty");
		clinet_1.setSurname("qwerty");
		
		Rent rentEquipment = new Rent();
		rentEquipment.setDateFrom(DateRent(2016, 12, 31));
		rentEquipment.setDateTo(DateRent(2017, 10, 24));
	
		RentEquipmentRequest rentEquipmentRequest = new RentEquipmentRequest();
		rentEquipmentRequest.setClient(clinet_1);
		rentEquipmentRequest.setRentEquipment(rentEquipment);
		rentEquipmentRequest.setCommand("rent_equipment");
		rentEquipmentRequest.setTitle("Коньки");
		
		try {
			response = controller.doAction(rentEquipmentRequest);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
	*/	
		//*****************************Возвращаем снаряжение в магазин (возвращать будем по названию снаряжения)*****************************// 
	/*	Client client_2 = new Client();
		client_2.setName("qwerty");
		client_2.setSurname("qwerty");
		
		RentEquipmentRequest returnEquipmentRequest = new RentEquipmentRequest();
		returnEquipmentRequest.setClient(client_2);
		returnEquipmentRequest.setCommand("return_equipment");
		returnEquipmentRequest.setTitle("Коньки");
		
		try {
			response = controller.doAction(returnEquipmentRequest);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
	*/	
		
	}
	
	private static String DateRent(int year, int month, int day){
		String date = year + "-" + month + "-" + day;
		return date;
	}

}

/*
1. регистрация
2. показать лист 
3. взять 3 предмета 
	потом еще 1
4. показать список арендованных 
*/

