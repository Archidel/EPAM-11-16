package com.epam.store.controller.command.impl;

import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.controller.command.Command;
import com.epam.store.controller.command.exception.CommandException;
import com.epam.store.controller.logging.StoreLogger;
import com.epam.store.service.InitializationService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.factory.ServiceFactory;

public class Initialization implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		Response response = null;
		
		ServiceFactory factory = ServiceFactory.getInstance();
		InitializationService initializationService = factory.getInitializationService();
		
		try {
			response = initializationService.initDAO();
		} catch (ServiceException e) {
			StoreLogger.getLog().error(e);
		}	
		
		return response;
	}
	
}