package com.epam.store.service;

import com.epam.store.bean.Response;

public interface InitializationService {
	Response initDAO() throws ServiceException;
	Response initStore() throws ServiceException;
}