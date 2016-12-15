package com.epam.store.view;

import com.epam.store.bean.Response;

public class PrintAnswer {
	public static void Dispatcher(Response response){
		
		if(!response.isStatusError()){
			System.out.println(response.getMessage());
		}else{
			System.out.println(response.getErrorMessage());
		}
		
	}
}
