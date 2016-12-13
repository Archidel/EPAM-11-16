package com.epam.shop.controller;

import com.epam.shop.command.Command;
import com.epam.shop.command.CommandProvider;
import com.epam.shop.command.exception.CommandNotFoundException;

public class Controller {
	
	public void doAction(String nameCommand){
		CommandProvider provider = CommandProvider.getInstance();
		Command command = null;
	
		try {
			command = provider.getCommand(nameCommand);
			command.execute(nameCommand);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
