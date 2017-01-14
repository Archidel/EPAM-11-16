package com.epam.store.service;

import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Equipment;
import com.epam.store.service.exception.ServiceException;

public interface StoreService {
	Response addNewClient(String name, String surname) throws ServiceException;
	Response addNewEquipment(Equipment equipment) throws ServiceException;
	Response getEquipmentList() throws ServiceException;
	Response getRentList() throws ServiceException;
}
