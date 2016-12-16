package com.epam.store.service;

import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.service.exception.ServiceException;

public interface StoreService {
	Response AddNewClient(Client client) throws ServiceException;
	Response AddNewEquipment(Equipment equipment) throws ServiceException;
}
