package com.epam.store.command.impl;

import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.command.Command;
import com.epam.store.command.exception.CommandException;
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
			throw new CommandException(e);
		}	
		return response;
	}
	
}
