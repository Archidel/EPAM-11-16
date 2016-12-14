package com.epam.store.view;

import com.epam.store.bean.RegisterClientRequest;
import com.epam.store.bean.entity.Request;
import com.epam.store.bean.entity.Response;
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
			PrintAnswer.Distributor(response);  // вывод ответа в консоль
		} catch (CommandNotFoundException e1) {
			e1.printStackTrace();
		}

		/*
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