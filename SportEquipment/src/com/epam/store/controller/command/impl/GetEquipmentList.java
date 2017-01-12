package com.epam.store.controller.command.impl;

import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.controller.command.Command;
import com.epam.store.service.StoreService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.factory.ServiceFactory;

public class GetEquipmentList implements Command {

	@Override
	public Response execute(Request request) {
		Response response = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		StoreService storeService = factory.getStoreService();
		
		try {
			response = storeService.GetEquipmentList();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	
		return response;
	}



}
