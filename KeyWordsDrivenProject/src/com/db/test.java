package com.db;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.SQLException; 

public class test {
	 public Connection conn = null;  
	 public PreparedStatement pst = null;
	
	 public static void main(String[] args) {
		// TODO Auto-generated method stub

			final String url = "jdbc:mysql://localhost:3306/TestAutomationDB";  
		    final String name = "com.mysql.jdbc.Driver";  
		    final String user = "root";  
		    final String password = "Oracdoc2";  
		    
		    
		   
		    
		    try {  
	            Class.forName(name);//指定连接类型  
	            System.out.println(DriverManager.getConnection(url, user, password));//获取连接
	            
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
		
		    
		    
		    
		
		
	}

}
