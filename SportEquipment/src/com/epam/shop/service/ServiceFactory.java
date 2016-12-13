package com.epam.shop.service;

import com.epam.shop.service.impl.InitializationServiceImpl;

public class ServiceFactory {
	private static ServiceFactory instance = null;
	private InitializationService initializationService = new InitializationServiceImpl();
	
	
	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public InitializationService getInitializationService() {
		return initializationService;
	}

}
