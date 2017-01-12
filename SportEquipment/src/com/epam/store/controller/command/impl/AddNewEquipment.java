package com.epam.store.controller.command.impl;

import com.epam.store.bean.EquipmentRequest;
import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.controller.command.Command;
import com.epam.store.controller.command.exception.CommandException;
import com.epam.store.service.StoreService;
import com.epam.store.service.exception.ServiceException;
import com.epam.store.service.factory.ServiceFactory;

public class AddNewEquipment implements Command {

	@Override
	public Response execute(Request request) throws CommandException {
		Response response = null;
		
		EquipmentRequest equipmentRequest = null;
		if(request instanceof EquipmentRequest){
			equipmentRequest = (EquipmentRequest) request;
		}else{
			throw new CommandException("Not command set");
		}
		
		Equipment equipment = equipmentRequest.getEquipment();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		StoreService storeService = factory.getStoreService();
		
		try {
			response = storeService.addNewEquipment(equipment);
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	
		return response;
	}

}
