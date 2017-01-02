package com.epam.store.bean;

import java.io.Serializable;

public class Request implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String command;

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}
	
}
