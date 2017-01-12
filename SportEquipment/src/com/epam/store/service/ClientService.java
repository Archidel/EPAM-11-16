package com.epam.store.service;

import com.epam.store.bean.RentEquipmentRequest;
import com.epam.store.bean.Response;
import com.epam.store.service.exception.ServiceException;

public interface ClientService {
	Response rentEquipment(RentEquipmentRequest rentEquipmentRequest) throws ServiceException;
	Response returnEquipment(RentEquipmentRequest returnEquipmentRequest) throws ServiceException;
}
