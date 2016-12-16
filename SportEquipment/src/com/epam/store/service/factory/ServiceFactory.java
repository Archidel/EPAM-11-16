package com.epam.store.service.factory;

import com.epam.store.service.ClientService;
import com.epam.store.service.InitializationService;
import com.epam.store.service.StoreService;
import com.epam.store.service.impl.ClientServiceImpl;
import com.epam.store.service.impl.InitializationServiceImpl;
import com.epam.store.service.impl.StoreServiceImpl;

public class ServiceFactory {
	private static ServiceFactory instance = null;
	private InitializationService initializationService = new InitializationServiceImpl();
	private ClientService clientService = new ClientServiceImpl();
	private StoreService storeService = new StoreServiceImpl();
	
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

	public ClientService getClientService() {
		return clientService;
	}

	public StoreService getStoreService() {
		return storeService;
	}

}
