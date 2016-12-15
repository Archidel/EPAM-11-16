package com.epam.store.view;

import com.epam.store.bean.Response;

public class PrintAnswer {
	public static void Dispatcher(Response response){
		System.out.println("Message = " + response.getMessage());
		System.out.println("Error Message = " + response.getErrorMessage());
		System.out.println("Error Status = " + response.isStatusError());
	}
}
