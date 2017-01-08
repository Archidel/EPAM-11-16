package by.epam.xml_reader.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import by.epam.xml_reader.service.XMLReaderService;
import by.epam.xml_reader.service.exception.ServiceException;

public class XMLReaderServiceImpl implements XMLReaderService{

	@Override
	public ArrayList<String> getDataFromFile(String path) throws ServiceException {
		ArrayList<String> list = null;
		if(path == null || path.equals("")){
			throw new ServiceException();
		}else{
			list = new ArrayList<>();
			BufferedReader reader = null;
			try {
				reader = new BufferedReader(new FileReader(new File(path)));
				while(reader.ready()){
					list.add(reader.readLine().trim());
				}
			} catch (FileNotFoundException e) {
				throw new ServiceException(e);
			} catch (IOException e) {
				throw new ServiceException(e);
			}finally{
				close(reader);
			}
		}
		return list;
	}

	@Override
	public ArrayList<String> readFilesTag(ArrayList<String> list) {
		System.out.println("array size = " + list.size());
		
		for(int i = 1; i < list.size(); i++){
			tagFinder(list.get(i).toCharArray());
		}
		return null;
	}

	/* Подсчёт количества открывающихся тегов если 2
	 * 
	 */
	
	private void tagFinder(char [] array){
		int openTag = -1;
		int closeTag = -1;
		boolean closebleTag = false;
		boolean tagHaveSoul = false;
		int indexTagHaveSout = -1;
		
		int openTagCouter = 0;

		//Подсчёт сколько количество открыв. тегов имеется в строке
		for(int k = 0; k < array.length; k++){
			if(array[k] == '<'){
				openTagCouter++;
			}
		}
		
		//условие расперделения 
		if(openTagCouter == 1){ 
			System.err.println("В строке 1 тег: ");
			for(int j = 0; j < array.length; j++){
				if(array[j] == '<'){
					openTag = j;
				}
				if(array[j] == '>'){
					closeTag = j;
				}
				if(array[j] == '<' && array[j+1] == '/'){
					closebleTag = true;
				}
			}
		
			//Проверка на соответсие открыв. и закрыв. тега
			if(openTag != -1 && closeTag != -1){
				String strTag = "";
				for(int i = openTag+1; i < closeTag; i++){
					if(array[i] == ' '){
						tagHaveSoul = true;
						break;
					}else{
						strTag += array[i];
						indexTagHaveSout = i;
					}
				}
				
				//если содержит атрибуты в себе (тег)
				if(tagHaveSoul){
					// Передача в другой метод для разсбора по деталя (массив и indexTagHaveSoul, closeTag); !!!!!!!!!!!!!
					// Может еще и вкинуть strTag(название тега) для формирования полной информационной строки
				}
				
				//Какой тип тега "закрывающийся" "открывающийся"
				if(closebleTag){
					System.out.println("Закрывается тег с названием: " + strTag);
				}else{
					System.out.println("Открывается тег с названием: " + strTag);		
				}
				
			}else{
				System.err.println("FATAL EXCEPTION IN OPEN CLOSE TAG (-1)");
			}
		
		}else if (openTagCouter == 2){
			System.err.println("В строке 2 тега");
		
		}else{
			System.err.println("FATAL EXCEPTION");
		}
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public boolean setDataToFile(ArrayList<String> list) {
		// TODO Auto-generated method stub
		return false;
	}

	private void close(BufferedReader reader) throws ServiceException{
		if(reader != null){
			try {
				reader.close();
			} catch (IOException e) {
				throw new ServiceException("Close reader is ERROR");
			}
		}
	}
	
}
