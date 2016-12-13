package com.epam.shop.command;

import com.epam.shop.bean.Request;
import com.epam.shop.bean.Response;

public interface Command {
	Response execute(Request request);
}
