package com.epam.store.DAO;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.Response;
import com.epam.store.bean.entity.Client;

public interface StoreDAO {
	Response AddNewClient(Client client) throws DAOException;
}
