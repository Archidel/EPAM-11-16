package com.epam.store.command.impl;

import com.epam.store.bean.entity.Request;
import com.epam.store.bean.entity.Response;
import com.epam.store.command.Command;
import com.epam.store.command.exception.CommandException;
import com.epam.store.service.InitializationService;
import com.epam.store.service.ServiceException;
import com.epam.store.service.ServiceFactory;

public class Initialization implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		Response response = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		InitializationService initializationService = factory.getInitializationService();
		
		try {
			initializationService.initDAO();
			response = initializationService.initStore();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		return response;
	}
	
}
