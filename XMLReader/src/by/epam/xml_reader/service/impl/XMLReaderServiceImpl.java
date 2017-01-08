package by.epam.xml_reader.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.omg.PortableServer.THREAD_POLICY_ID;

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
			tagAnalyzer(list.get(i).toCharArray());
		}
		return null;
	}

	private String oneTag(char [] array){
		String line = null;
		int numberOpeningTag = -1;
		int numberClosingTag = -1;
		int numberTagAttribute = -1;
		boolean isClosedTag = false;
		boolean isAttribute = false;
		
		//Запись индексов открывающегося, закрывающегося и определение закрывается тег или нет
		for(int j = 0; j < array.length; j++){
			if(array[j] == '<'){
				numberOpeningTag = j;
			}
			if(array[j] == '>'){
				numberClosingTag = j;
			}
			if(array[j] == '<' && array[j+1] == '/'){
				isClosedTag = true;
			}
		}
	
		//Проверка значений индекосв тега (вдруг запись в XML файле не корректа)
		if(numberOpeningTag != -1 && numberClosingTag != -1){
			String nameTag = "";
			String attribute = "";
			
			for(int i = numberOpeningTag + 1; i < numberClosingTag; i++){
				if(array[i] == ' '){
					isAttribute = true;
					break;
				}else{
					nameTag += array[i];
					numberTagAttribute = i + 2;
				}
			}
			//Читаем атрибут тега
			if(isAttribute){
				for(int i = numberTagAttribute; i < numberClosingTag; i++) attribute += array[i];
			}
			line = formatterOneTag(nameTag, attribute, isAttribute, isClosedTag);
		} 
		return line;
	}
	
	private String formatterOneTag(String nameTag, String attribute, boolean isAttribute, boolean isClosedTag){
		String line = null;
		if(isClosedTag){
			line = "Тег " + nameTag + " закрывается.";
		}else{
			if(isAttribute){
				line = "Открывается тег " + nameTag + " который имеет атрибуты: " + attribute + ".";
			}else{
				line = "Открывается тег " + nameTag + " который не имеет атрибутов.";
			}
		}
		return line;
	}

	private String twoTag(char [] array){
		String line = null;
		int firstNumberOpeningTag = -1;
		int secondNumberClosingTag = -1;
		int thirdNumberOpeningTag = -1;
		int fourthNumberClosingTag = -1;
		int numberTagAttribute = -1;
		boolean isAttribute = false;
		
		for(int j = 0; j < array.length; j++){
			if(array[j] == '<'){
				if(firstNumberOpeningTag == -1){
					firstNumberOpeningTag = j;
				}else{
					thirdNumberOpeningTag = j;
				}
			}	
			if(array[j] == '>'){
				if(secondNumberClosingTag == -1){
					secondNumberClosingTag = j;
				}else{
					fourthNumberClosingTag = j;
				}
			}
		}
		
		if(firstNumberOpeningTag != -1 && secondNumberClosingTag != -1 && thirdNumberOpeningTag != -1 && fourthNumberClosingTag != -1){
			String firstNameTag = "";
			String secondNameTag = "";
			String content = "";
			String attribute = "";
			//Определяем название первого тега
			for(int i = firstNumberOpeningTag + 1; i < secondNumberClosingTag; i++){
				if(array[i] == ' '){
					isAttribute = true;
					break;
				}else{
					firstNameTag += array[i];
					numberTagAttribute = i + 2;
				}
			}
			//Определяем название второго тега
			for(int i = firstNumberOpeningTag + 1; i < secondNumberClosingTag; i++){
				if(array[i] == ' '){
					isAttribute = true;
					break;
				}else{
					secondNameTag += array[i];
					numberTagAttribute = i + 2;
				}
			}
			
			//Ищем атрибут
			if(isAttribute){
				for(int i = numberTagAttribute; i < secondNumberClosingTag; i++) attribute += array[i];
			}
			
			//ПРОВЕРКА соответсвие двух тегов (вдруг чушь написана в xml фале)
			if(!firstNameTag.equalsIgnoreCase(secondNameTag)){
				System.err.println("EXCEPTION THE FIRST TAG NOT EQUALSE THE SECOND TAG");
			}else{
				//Здесь-я мы определяем содержимое тегов
				for(int k = secondNumberClosingTag + 1; k < thirdNumberOpeningTag; k++){
					content += array[k];
				}
				line = formatterTwoTag(secondNameTag, attribute, content, isAttribute);
			}
		}else{
			System.err.println("EXCEPTION ONE OF DATE NOT FOUND");
		}
		
		return line;
	}
	
	private String formatterTwoTag(String nameTag, String attribute, String content, boolean isAttribute){
		String line = null;
		if(isAttribute){
			line = "Открывается тег " + nameTag + " который имеет атрибуты: " + attribute + "и содержит в себе: " + content + ". Тег " + nameTag + " закрывается.";
		}else{
			line = "Открывается тег " + nameTag + " который не имеет атрибутов" + "и содержит в себе: " + content + ". Тег " + nameTag + " закрывается.";;
		}
		return line;
	}

	private void tagAnalyzer(char [] array){
		int TagCounter = 0;
		
		//Счётчик количество тегов в строке
		for(char count : array){
			if(count == '<') TagCounter++;
		}
		
		//Распеределение
		switch (TagCounter) {
		case 1: //В строке 1 тег
			System.out.println(oneTag(array));	
	//		oneTag(array);
			break;
		case 2: // В строке 2 тега
			System.out.println(twoTag(array));
	//		twoTag(array);
			break;

		default: // В строке ни одного или 3 и более тегов
			//Exception
			break;
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
