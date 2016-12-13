package com.epam.shop.command;

import com.epam.shop.bean.Request;
import com.epam.shop.bean.Response;
import com.epam.shop.command.exception.CommandException;

public interface Command {
	Response execute(Request request) throws CommandException;
}
