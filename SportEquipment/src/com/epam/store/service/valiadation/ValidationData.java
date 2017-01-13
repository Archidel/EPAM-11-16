package com.epam.store.service.valiadation;

public class ValidationData {
	
	public static boolean currentData(String line){
		boolean status = false;
		if((line == null) || (line.isEmpty())){
			status = false;
		}else{
			status = true;
		}
		return status;
	}
	
	public static boolean numberOfVarification(int number){
		boolean status = false;
		if(number == 0 || number < 0){
			status = false;
		}else{
			status = true;
		}
		return status;
	}
	
}
