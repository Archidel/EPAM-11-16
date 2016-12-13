package com.epam.shop.service;

import com.epam.shop.bean.Response;

public interface InitializationService {
	Response initDAO() throws ServiceException;
	Response initStore() throws ServiceException;
}
