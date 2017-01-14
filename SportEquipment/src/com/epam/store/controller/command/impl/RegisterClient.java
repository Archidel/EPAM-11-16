package com.epam.store.controller.command.impl;

import com.epam.store.bean.RegisterClientRequest;
import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.controller.command.Command;
import com.epam.store.controller.command.exception.CommandException;
import com.epam.store.controller.logging.StoreLogger;
import com.epam.store.service.StoreService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.factory.ServiceFactory;

public class RegisterClient implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		Response response = null;
		RegisterClientRequest registerClient = null;
		
		if(request instanceof RegisterClientRequest){
			registerClient = (RegisterClientRequest) request;
		}else{
			throw new CommandException("Not command set");
		}
		
		String name = registerClient.getName();
		String surname = registerClient.getSurname();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		StoreService storeService = factory.getStoreService();
		
		try {
			response = storeService.addNewClient(name, surname);
		} catch (ServiceException e) {
			StoreLogger.getLog().error(e);
		}
		
		return response;
	}
	
}