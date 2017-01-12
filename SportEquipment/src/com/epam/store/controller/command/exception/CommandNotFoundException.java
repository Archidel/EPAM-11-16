package com.epam.store.controller.command.exception;

public class CommandNotFoundException extends CommandException{
	private static final long serialVersionUID = 4064483459800219551L;
	
	public CommandNotFoundException() {
		super();
	}
	
	public CommandNotFoundException(String message) {
		super(message);
	}
	
	public CommandNotFoundException(Exception e) {
		super(e);
	}
	
	public CommandNotFoundException(String message, Exception e) {
		super(message, e);
	}

}
