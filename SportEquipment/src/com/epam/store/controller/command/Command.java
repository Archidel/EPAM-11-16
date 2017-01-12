package com.epam.store.controller.command;

import com.epam.store.bean.Request;
import com.epam.store.bean.Response;
import com.epam.store.controller.command.exception.CommandException;

public interface Command {
	Response execute(Request request) throws CommandException;
}
