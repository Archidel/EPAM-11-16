package com.epam.store.view;

import com.epam.store.bean.ListResponse;
import com.epam.store.bean.Response;
import com.epam.store.controller.logging.StoreLogger;

public class PrintAnswer {
	public static void Dispatcher(Response response){
		
		if(!response.isStatusError()){
			System.out.println(response.getMessage());
			StoreLogger.getLog().info(response.getMessage());
		}else{
			System.err.println(response.getErrorMessage());
			StoreLogger.getLog().error(response.getErrorMessage());
		}
		
		ListResponse listResponse = null;
		if(response instanceof ListResponse){
			listResponse = (ListResponse) response;
			
			for(int i = 0; i < listResponse.getList().size(); i++){
				System.out.println(listResponse.getElementListByIndex(i).toString());
			}
		}
		
	} 
	
}
