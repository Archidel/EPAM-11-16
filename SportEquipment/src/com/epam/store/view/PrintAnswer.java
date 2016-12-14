package com.epam.store.view;

import com.epam.store.bean.entity.Response;

public class PrintAnswer {
	public static void Distributor(Response response){
		System.out.println("Message = " + response.getMessage());
		System.out.println("Error Message = " + response.getErrorMessage());
		System.out.println("Error Status = " + response.isStatusError());
	}
}
