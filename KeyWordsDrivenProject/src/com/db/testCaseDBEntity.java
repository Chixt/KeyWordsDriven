package com.db;

import java.util.UUID;

public class testCaseDBEntity {
	
	
	/*
	 * insert data into TestCase table.
	 */
	public void insertTestCase(String apiFunction,String apiParameters,String keyWordsDescription,String caseSuiteID){
		
	 String uuid = UUID.randomUUID().toString(); 
	 //remove "-" 
	 String caseID = uuid.substring(0,8)+uuid.substring(9,13)+uuid.substring(14,18)+uuid.substring(19,23)+uuid.substring(24); 	
		
	 String sql = "insert into TestCase (caseID,apiFunction,apiParameters,keyWordsDescription,caseSuiteID) VALUES (?,?,?,?,?)";  
     String[] parameters = { caseID, apiFunction, apiParameters, keyWordsDescription,caseSuiteID };  
     SqlHelper.executeUpdate(sql, parameters); 	
    }
	
	
	
	
	
	

}
