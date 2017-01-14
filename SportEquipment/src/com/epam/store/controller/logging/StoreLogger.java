package com.epam.store.controller.logging;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class StoreLogger {
	
	private static StoreLogger instance = null;
	private static final String CONFIG_PATH  = "src/resources/log4j.properties";
	private static final String TYPE_LOGGING = "logfile";
	private static final Logger LOG = Logger.getLogger(TYPE_LOGGING);
	
	private StoreLogger() {
		PropertyConfigurator.configure(CONFIG_PATH);
	}
	
	public static StoreLogger getInstance() {
		if(instance == null){
			instance = new StoreLogger();
		}
		return instance;
	}

	public static Logger getLog() {
		return LOG;
	}

}
