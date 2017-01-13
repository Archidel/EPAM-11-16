package com.epam.store.controller.command;

import java.util.HashMap;
import java.util.Map;

import com.epam.store.controller.command.exception.CommandNotFoundException;
import com.epam.store.controller.command.impl.AddNewEquipment;
import com.epam.store.controller.command.impl.GetEquipmentList;
import com.epam.store.controller.command.impl.GetRentList;
import com.epam.store.controller.command.impl.Initialization;
import com.epam.store.controller.command.impl.RegisterClient;
import com.epam.store.controller.command.impl.RentEquipment;
import com.epam.store.controller.command.impl.ReturnEquipment;

public final class CommandProvider {
	
	private static CommandProvider instance = null;
	private Map<String, Command> list = null;
	
	private CommandProvider() {
		list = new HashMap<String, Command>();
		list.put(CommandName.RENT_EQUIPMENT, new RentEquipment());
		list.put(CommandName.RETURN_EQUIPMENT, new ReturnEquipment());
		list.put(CommandName.GET_RENT_LIST, new GetRentList());
		list.put(CommandName.GET_EQUIPMENT_LIST, new GetEquipmentList());
		list.put(CommandName.REGISTER_CLIENT, new RegisterClient());
		list.put(CommandName.INITIALIZATION, new Initialization());
		list.put(CommandName.ADD_NEW_EQUIPMENT, new AddNewEquipment());
	}

	public static CommandProvider getInstance() {
		if(instance == null){
			instance = new CommandProvider();
		}
		
		return instance;
	}

	public Command getCommand(String CommandName) throws CommandNotFoundException{
		Command command = null;
		command = list.get(CommandName);
		
		if(command == null){
			throw new CommandNotFoundException();
		}
		
		return  command;
	}
	
}
