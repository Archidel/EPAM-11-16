package by.epam.xml_reader.view;

import java.util.ArrayList;

import by.epam.xml_reader.service.XMLReaderService;
import by.epam.xml_reader.service.exception.ServiceException;
import by.epam.xml_reader.service.factory.ServiceFactory;
import by.epam.xml_reader.service.manager.FileManager;

public class ViewConsole {
	public static void main(String [] args){
		FileManager fileManager = FileManager.getInstance();
		String path = fileManager.getPath();
		
		ServiceFactory factory = ServiceFactory.getInstance();
		XMLReaderService readerService = factory.getXMLReaderService();
		
		ArrayList<String> list = null;
		
		try {
			list = readerService.getDataFromFile(path);
			Printer.printArray(list);
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		
		
		
		readerService.readFilesTag(list);
	
	
	}
}
