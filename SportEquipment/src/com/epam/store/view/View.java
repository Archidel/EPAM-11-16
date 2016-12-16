package com.epam.store.view;

import com.epam.store.bean.RegisterClientRequest;
import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.command.exception.CommandNotFoundException;
import com.epam.store.controller.Controller;

public class View {
	private static final Controller controller = new Controller();
	
	public static void main(String [] args){
		Response response = null;

		//инициализация коннекшен пула, инициализация магазина
		Request initReq = new Request();
		initReq.setCommand("initialization");
		
		try {
			response = controller.doAction(initReq);
			PrintAnswer.Dispatcher(response);  // вывод ответа в консоль
		} catch (CommandNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//регистрация клиента который хочет взять снаряжения в аренду
		RegisterClientRequest registerClient = new RegisterClientRequest();
		registerClient.setCommand("register_client");
		registerClient.setName("Vasya");
		registerClient.setSurname("Pupkin");
		
		try {
			response = controller.doAction(registerClient);
			PrintAnswer.Dispatcher(response);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
			
		
		
		
	}
	
}

/*
1. регистрация
2. показать лист 
3. взять 3 предмета 
	потом еще 1
4. показать список арендованных 

*/