package com.epam.store.command.impl;

import com.epam.store.bean.RentEquipmentRequest;
import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.command.Command;
import com.epam.store.command.exception.CommandException;
import com.epam.store.service.ClientService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.factory.ServiceFactory;

public class ReturnEquipment implements Command {

	@Override
	public Response execute(Request request) throws CommandException {	
		Response response = null;
		
		RentEquipmentRequest returnEquipmentRequest = null;
		if(request instanceof RentEquipmentRequest){
			returnEquipmentRequest = (RentEquipmentRequest) request;
		}else{
			throw new CommandException("Not command set");
		}
		
		ServiceFactory factory = ServiceFactory.getInstance();
		ClientService clientService = factory.getClientService();
		
		try {
			response = clientService.ReturnEquipment(returnEquipmentRequest);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return response;
	}
	
}
