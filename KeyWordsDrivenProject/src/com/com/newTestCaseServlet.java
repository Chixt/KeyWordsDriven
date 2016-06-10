
/**
 * 
 *
 * 
 * 
 * 
 */
package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.db.testCaseDBEntity;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 *
 */
public class newTestCaseServlet extends AbstractRestServlet {

    // Something to make an AbstractRestServlet warning go away
    private static final long serialVersionUID = 1L;


    /**
     * Return resource handlers.  restReources defined at bottom of this file.
     */
    @Override
    public IRestResourceHandler[] getRestResourceHandlers() {
    	return restResources.clone();
    }

    /**
     * 
     *
     * 
     * 
     */
    private transient IRestResourceHandler addNewTestCaseHandler = new IRestResourceHandler() {
       

        private static final String PATH_VARIABLE_KEY = "com.testCase.pathVariableMap";
    	public String[] getRestPathVariableNames()
        {
        	return new String[] { PATH_VARIABLE_KEY };
        }
    	
        private Pattern pathPattern = Pattern.compile("/Add(.*)$");

        @Override
		public Pattern getRestUriPattern() {
			// TODO Auto-generated method stub
			 return pathPattern;
		}
        

        /**
         * handleGet: Process HTTP GET request
         * 
         * 
         */
        public void handleGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String METH = "handleGet()";
          
            JSONObject result = new JSONObject();
            
            JSONArray items = null;
            JSONObject item = null;

            try {

                                                      

             
               } catch (Exception ex) {
                
            }

            // Encode the result into the response
            AbstractRestServlet.writeResponse(result, response);
         
        }

        @Override
		public void handlePost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
        
        	
        	StringBuffer sb = new StringBuffer();
            BufferedReader bufferedReader = null;
            try {
                
                bufferedReader =  request.getReader();
                char[] charBuffer = new char[128];
                int bytesRead;
                while ( (bytesRead = bufferedReader.read(charBuffer)) != -1 ) {
                    sb.append(charBuffer, 0, bytesRead);
                }
                
                JSONObject obj =  JSONObject.fromString(sb.toString());
                String apiFunction = (String) obj.get("apiFunction");
                String apiParameters = (String)obj.get("apiParameters");
                String keyWordsDescription = (String)obj.get("keyWordsDescription");
                String caseSuiteID = (String)obj.get("caseSuiteID");
                testCaseDBEntity testEntity = new testCaseDBEntity();
            	testEntity.insertTestCase(apiFunction, apiParameters, keyWordsDescription, caseSuiteID);
                
                

            } catch (IOException ex) {
                throw ex;
            } finally {
                if (bufferedReader != null) {
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        throw ex;
                    }
                }
            }

        	
		}


		@Override
		public void handlePut(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		}


		@Override
		public void handleDelete(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			// TODO Auto-generated method stub
			
		}
		

		
    };
  

    // This is the list of resources with their handler classes
    private transient IRestResourceHandler[] restResources = new IRestResourceHandler[] {
    		addNewTestCaseHandler
    };

}
