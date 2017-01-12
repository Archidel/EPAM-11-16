package com.epam.store.controller;

import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.controller.command.Command;
import com.epam.store.controller.command.CommandProvider;
import com.epam.store.controller.command.exception.CommandException;
import com.epam.store.controller.command.exception.CommandNotFoundException;

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
