package com.epam.store.controller.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class StoreLogger {
	private static final Logger LOG = Logger.getLogger("src/log/logFile.txt");

	public StoreLogger() {
		PropertyConfigurator.configure("src/resources/log4j.properties");
		LOG.error("asdasd");
	}
	
	public static Logger getLog() {
		return LOG;
	}

}
