package com.epam.store.DAO;

import java.util.ArrayList;

import com.epam.store.DAO.exception.DAOException;
import com.epam.store.bean.entity.Response;
import com.epam.store.bean.entity.SportEquipment;

public interface InitializationDAO {
	Response initDAO() throws DAOException;
	ArrayList<SportEquipment> initStore() throws DAOException;
}
