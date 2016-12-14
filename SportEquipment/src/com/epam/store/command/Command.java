package com.epam.store.command;

import com.epam.store.bean.entity.Request;
import com.epam.store.bean.entity.Response;
import com.epam.store.command.exception.CommandException;

public interface Command {
	Response execute(Request request) throws CommandException;
}
