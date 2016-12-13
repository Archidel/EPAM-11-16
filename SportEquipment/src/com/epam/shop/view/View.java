package com.epam.shop.view;

import com.epam.shop.bean.Request;
import com.epam.shop.bean.Response;
import com.epam.shop.bean.entity.RegisterClientRequest;
import com.epam.shop.command.exception.CommandNotFoundException;
import com.epam.shop.controller.Controller;
import com.epam.shop.controller.PrintAnswer;

public class View {
	private static final Controller controller = new Controller();
	
	public static void main(String [] args){
		Response response = null;
	
		//инициализация коннекшен пула, инициализация магазина
		Request initReq = new Request();
		initReq = init();
		
		try {
			response = controller.doAction(initReq);
			PrintAnswer.Distributor(response);  // вывод ответа в консоль
		} catch (CommandNotFoundException e1) {
			e1.printStackTrace();
		}
		
		//регистрация клиента который хочет взять снаряжения в аренду
		RegisterClientRequest registerReq = new RegisterClientRequest();
		registerReq.setNameClient("Juliy");
		registerReq.setSurnameCline("CezaR");
		registerReq.setCommand("register_clinet");
		
		try {
			response = controller.doAction(registerReq);
			PrintAnswer.Distributor(response);	// вывод ответа в консоль
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		
	}

	private static Request init(){
		Request request = new Request();
		request.setCommand("initialization");
		return request;
	}
	
}

/*
1. регистрация
2. показать лист 
3. взять 3 предмета 
	потом еще 1
4. показать список арендованных 

*/