package by.epam.xml_reader.service.manager;

import java.util.ResourceBundle;

public class FileManager {
	private static FileManager instance = null;
	private ResourceBundle bundle = ResourceBundle.getBundle("resource.XMLPath");

	private FileManager() {}
	
	public static FileManager getInstance() {
		if(instance == null){
			instance = new FileManager();
		}
		return instance;
	}

	public String getPath(){
		return bundle.getString(ManagerParameter.PATH);
	}
	
}
