package com.epam.store.controller;

import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.controller.command.Command;
import com.epam.store.controller.command.CommandProvider;
import com.epam.store.controller.command.exception.CommandException;
import com.epam.store.controller.command.exception.CommandNotFoundException;
import com.epam.store.controller.logging.StoreLogger;

public class Controller {

	public Response doAction(Request request) throws CommandNotFoundException{
		Response response = null;
		Command command = null;
		
		String nameCommand = request.getCommand();
		
		CommandProvider provider = CommandProvider.getInstance();
		
		try {
			command = provider.getCommand(nameCommand);
			response = command.execute(request);
		} catch (CommandNotFoundException e) {
			StoreLogger.getLog().error(e);
		} catch (CommandException e) {
			StoreLogger.getLog().error(e);
		}
		
		if(command == null){
			throw new CommandNotFoundException();
		}
		
		return response;
	}
}
