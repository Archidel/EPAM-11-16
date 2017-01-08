package by.epam.xml_reader.service.factory;

import by.epam.xml_reader.service.XMLReaderService;
import by.epam.xml_reader.service.impl.XMLReaderServiceImpl;

public class ServiceFactory {
	private static ServiceFactory instance = null;
	private static final XMLReaderService XML_READER_SERVICE = new XMLReaderServiceImpl();
	
	private ServiceFactory() {}

	public static ServiceFactory getInstance() {
		if(instance == null){
			instance = new ServiceFactory();
		}
		return instance;
	}

	public XMLReaderService getXMLReaderService() {
		return XML_READER_SERVICE;
	}
	
	
}
