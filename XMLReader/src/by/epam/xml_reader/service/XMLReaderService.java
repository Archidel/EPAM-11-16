package by.epam.xml_reader.service;

import java.util.ArrayList;

import by.epam.xml_reader.service.exception.ServiceException;

public interface XMLReaderService {
	ArrayList<String> getDataFromFile(String path) throws ServiceException;
	ArrayList<String> readFilesTag(ArrayList<String> list) throws ServiceException;
	void setDataToFile(ArrayList<String> list) throws ServiceException;
	
}
