package com.epam.store.view;

import com.epam.store.bean.ListResponse;
import com.epam.store.bean.Response;

public class PrintAnswer {
	public static void Dispatcher(Response response){
		
		if(!response.isStatusError()){
			System.out.println(response.getMessage());
		}else{
			System.out.println(response.getErrorMessage());
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
