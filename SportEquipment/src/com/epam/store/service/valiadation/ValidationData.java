package com.epam.store.service.valiadation;

public class ValidationData {
	public static boolean currentData(String line){
		boolean status = false;
		if((line == null) || (line.equals(""))){
			status = false;
		}else{
			status = true;
		}
		return status;
	}
}
