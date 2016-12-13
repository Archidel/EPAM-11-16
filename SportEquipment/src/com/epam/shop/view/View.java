package com.epam.shop.view;

import com.epam.shop.bean.Response;
import com.epam.shop.bean.entity.RegisterClientRequest;
import com.epam.shop.command.exception.CommandNotFoundException;
import com.epam.shop.controller.Controller;
import com.epam.shop.controller.PrintAnswer;

public class View {
	
	public static void main(String [] args){
		Controller controller = new Controller();
		Response response = null;
		
		//регистрация клиента который хочет взять снаряжения в аренду
		RegisterClientRequest request = new RegisterClientRequest();
		request.setNameClient("Juliy");
		request.setSurnameCline("CezaR");
		request.setCommand("register_clinet");
		
		try {
			response = controller.doAction(request);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		
		PrintAnswer.Distributor(response);	// вывод ответа в консоль
		
	}
}

/*
1. регистрация
2. показать лист 
3. взять 3 предмета 
	потом еще 1
4. показать список арендованных 


*/