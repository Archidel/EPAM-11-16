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
		list.put("take_item", new TakeItem());
		list.put("return_item", new ReturnItem());
		list.put("show_rent_list", new ShowRentList());
		list.put("show_equipment_list", new ShowEquipmentList());
		list.put("register_clinet", new RegisterClient());
		list.put("initialization", new Initialization());
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