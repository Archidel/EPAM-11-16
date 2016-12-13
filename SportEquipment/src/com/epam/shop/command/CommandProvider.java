package com.epam.shop.command;

import java.util.HashMap;
import java.util.Map;

import com.epam.shop.command.exception.CommandNotFoundException;
import com.epam.shop.command.impl.ReturnItem;
import com.epam.shop.command.impl.ShowEquipmentList;
import com.epam.shop.command.impl.ShowRentList;
import com.epam.shop.command.impl.TakeItem;

public class CommandProvider {
	private static CommandProvider instance = null;
	private Map<String, Command> list = null;
	
	private CommandProvider() {
		list = new HashMap<String, Command>();
		list.put("take_item", new TakeItem());
		list.put("return_item", new ReturnItem());
		list.put("show_rent_list", new ShowRentList());
		list.put("show_equipment_list", new ShowEquipmentList());
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
