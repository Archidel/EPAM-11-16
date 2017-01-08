package by.epam.xml_reader.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
			try(BufferedReader reader = new BufferedReader(new FileReader(new File(path)));){
				while(reader.ready()){
					list.add(reader.readLine().trim());
				}
			} catch (FileNotFoundException e) {
				throw new ServiceException(e);
			} catch (IOException e) {
				throw new ServiceException(e);
			}
		}
		return list;
	}

	@Override
	public ArrayList<String> readFilesTag(ArrayList<String> list) throws ServiceException {
		ArrayList<String> answerList = new ArrayList<String>();
		for(int i = 1; i < list.size(); i++){
			answerList.add(tagAnalyzer(list.get(i).toCharArray()));
		}
		return answerList;
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

	private String twoTag(char [] array) throws ServiceException{
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
				throw new ServiceException("The first name tag not equalse name the second tag");
			}else{
				//Здесь-я мы определяем содержимое тегов
				for(int k = secondNumberClosingTag + 1; k < thirdNumberOpeningTag; k++){
					content += array[k];
				}
				line = formatterTwoTag(secondNameTag, attribute, content, isAttribute);
			}
		}else{
			throw new ServiceException("One of date not found");
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

	private String tagAnalyzer(char [] array) throws ServiceException{
		String line = null;
		int TagCounter = 0;
		
		//Счётчик количество тегов в строке
		for(char count : array){
			if(count == '<') TagCounter++;
		}
		
		//Распеределение
		switch (TagCounter) {
		case 1: //В строке 1 тег
			line = oneTag(array);
			break;
		case 2: // В строке 2 тега
			line = twoTag(array);
			break;
		default: // В строке ни одного или 3 и более тегов
			//Exception
			break;
		}
		return line;
	}

	@Override
	public void setDataToFile(ArrayList<String> list) {
   	try(FileWriter writer = new FileWriter("src/resource/XMLReader.txt", false)){
			for(int i = 0; i < list.size(); i++){
				writer.write(list.get(i) + "\n");
			}
			writer.flush();
		}catch(IOException ex){
        	ex.printStackTrace();
        } 
	}
	
}
