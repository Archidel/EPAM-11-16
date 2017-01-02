package com.epam.store.command;

import java.util.HashMap;
import java.util.Map;

import com.epam.store.command.exception.CommandNotFoundException;
import com.epam.store.command.impl.AddNewEquipment;
import com.epam.store.command.impl.Initialization;
import com.epam.store.command.impl.RegisterClient;
import com.epam.store.command.impl.ReturnItem;
import com.epam.store.command.impl.GetEquipmentList;
import com.epam.store.command.impl.GetRentList;
import com.epam.store.command.impl.RentEquipment;

public class CommandProvider {
	
	private static CommandProvider instance = null;
	private Map<String, Command> list = null;
	
	private CommandProvider() {
		list = new HashMap<String, Command>();
		list.put(NameCommand.RENT_EQUIPMENT_CMD, new RentEquipment());
		list.put(NameCommand.RETURN_EQUIPMENT_CMD, new ReturnItem());
		list.put(NameCommand.GET_RENT_LIST_CMD, new GetRentList());
		list.put(NameCommand.GET_EQUIPMENT_LIST_CMD, new GetEquipmentList());
		list.put(NameCommand.REGISTER_CLIENT_CMD, new RegisterClient());
		list.put(NameCommand.INITIALIZATION_CMD, new Initialization());
		list.put(NameCommand.ADD_NEW_EQUIPMENT_CMD, new AddNewEquipment());
	}

	public static CommandProvider getInstance() {
		if(instance == null){
			instance = new CommandProvider();
		}
		return instance;
	}

	public Command getCommand(String nameCommand) throws CommandNotFoundException{
		Command command = list.get(nameCommand);
		
		if(command == null){
			throw new CommandNotFoundException();
		}
		
		return  command;
	}
	
}
