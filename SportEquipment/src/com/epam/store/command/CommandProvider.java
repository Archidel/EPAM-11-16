package com.epam.store.command;

import java.util.HashMap;
import java.util.Map;

import com.epam.store.command.exception.CommandNotFoundException;
import com.epam.store.command.impl.Initialization;
import com.epam.store.command.impl.RegisterClient;
import com.epam.store.command.impl.ReturnItem;
import com.epam.store.command.impl.ShowEquipmentList;
import com.epam.store.command.impl.ShowRentList;
import com.epam.store.command.impl.TakeItem;

public class CommandProvider {
	
	private static CommandProvider instance = null;
	private Map<String, Command> list = null;
	
	private CommandProvider() {
		list = new HashMap<String, Command>();
		list.put(NameCommand.TAKE_ITEM_CMD, new TakeItem());
		list.put(NameCommand.RETURN_ITEM_CMD, new ReturnItem());
		list.put(NameCommand.SHOW_RENT_LIST_CMD, new ShowRentList());
		list.put(NameCommand.SHOW_EQUIPMENT_LIST_CMD, new ShowEquipmentList());
		list.put(NameCommand.REGISTER_CLIENT_CMD, new RegisterClient());
		list.put(NameCommand.INITIALIZATION_CMD, new Initialization());
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
