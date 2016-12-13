package com.epam.store.DAO;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;

public interface InitializationDAO {
	Response initDAO() throws DAOException;
}
