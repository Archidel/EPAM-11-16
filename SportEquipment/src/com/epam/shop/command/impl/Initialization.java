package com.epam.shop.command.impl;

import com.epam.shop.bean.Request;
import com.epam.shop.bean.Response;
import com.epam.shop.command.Command;
import com.epam.shop.command.exception.CommandException;
import com.epam.shop.service.InitializationService;
import com.epam.shop.service.ServiceException;
import com.epam.shop.service.ServiceFactory;

public class Initialization implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		Response response = null;
		ServiceFactory factory = ServiceFactory.getInstance();
		InitializationService initializationService = factory.getInitializationService();
		
		try {
			initializationService.initDAO();
			initializationService.initStore();
		} catch (ServiceException e) {
			throw new CommandException(e);
		}
		
		
		return response;
	}
	
}
