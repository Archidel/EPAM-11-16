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
import com.epam.store.command.exception.CommandNotFoundException;
import com.epam.store.command.impl.RentEquipment;
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
		/*Equipment equipment = new Equipment();
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
		*/
		//регистрация клиента который хочет взять снаряжения в аренду
		/*RegisterClientRequest registerClient = new RegisterClientRequest();
		registerClient.setCommand("register_client");
		registerClient.setName("Will");
		registerClient.setSurname("Smith");
		
		try {
			response = controller.doAction(registerClient);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		*/		
		//Показать список всех товараов что есть в наличии
		Request getEquipmentList = new Request();
		getEquipmentList.setCommand("get_equipment_list");
		
		try {
			response = controller.doAction(getEquipmentList);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		
		//Берём в аренду снаряжения
		/*	БАГ
		 * Нет высчита total price for rent table(подсчёт дней из внесённой даты)
		 */
	/*	Client clinet_1 = new Client();
		clinet_1.setName("Will");
		clinet_1.setSurname("Smith");
		
		Rent rentEquipment = new Rent();
		rentEquipment.setDateFrom(DateRent(2016, 12, 31));
		rentEquipment.setDateTo(DateRent(2017, 10, 24));
	
		RentEquipmentRequest rentEquipmentRequest = new RentEquipmentRequest();
		rentEquipmentRequest.setClient(clinet_1);
		rentEquipmentRequest.setRentEquipment(rentEquipment);
		rentEquipmentRequest.setCommand("rent_equipment");
		rentEquipmentRequest.setTitle("Санки");
		
		try {
			response = controller.doAction(rentEquipmentRequest);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
	*/	
		//Возвращаем снаряжение в магазин (возвращать будем по названию снаряжения) 
		Client client_2 = new Client();
		client_2.setName("Will");
		client_2.setSurname("Smith");
		
		RentEquipmentRequest returnEquipmentRequest = new RentEquipmentRequest();
		returnEquipmentRequest.setClient(client_2);
		returnEquipmentRequest.setCommand("return_equipment");
		returnEquipmentRequest.setTitle("Cанки");
		
		try {
			response = controller.doAction(returnEquipmentRequest);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		
		
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

