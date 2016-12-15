package com.epam.store.service;

import com.epam.store.bean.Response;
import com.epam.store.service.exception.ServiceException;

public interface InitializationService {
	Response initDAO() throws ServiceException;
}
