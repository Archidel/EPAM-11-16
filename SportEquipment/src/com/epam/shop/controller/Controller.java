package com.epam.shop.controller;

import com.epam.shop.bean.Request;
import com.epam.shop.bean.Response;
import com.epam.shop.command.Command;
import com.epam.shop.command.CommandProvider;
import com.epam.shop.command.exception.CommandException;
import com.epam.shop.command.exception.CommandNotFoundException;

public class Controller {

	public Response doAction(Request request) throws CommandNotFoundException{
		String nameCommand = request.getCommand();
		
		CommandProvider provider = CommandProvider.getInstance();
		Command command = null;
		Response response = null;
		
		try {
			command = provider.getCommand(nameCommand);
			response = command.execute(request);
		} catch (CommandNotFoundException e) {
			e.printStackTrace();
		} catch (CommandException e) {
			e.printStackTrace();
		}
		
		if(command == null){
			throw new CommandNotFoundException();
		}
		
		return response;
	}
}
