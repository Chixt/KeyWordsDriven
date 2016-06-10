package com;



import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public interface IRestResourceHandler
{

 
	 public Pattern getRestUriPattern();
        
   
	public String[] getRestPathVariableNames();
	
    /**
     * POST handler.  In REST, used to perform changes to a resource.
     * <p>
     * Implementor should set the context type to something appropriate 
     * (<code>response.setContentType("text/plain");</code> in the response,
     * and write JSON data to the response. 
     * 
     * @param request
     * @param response  
     * @throws ServletException
     * @throws IOException
     */
	public void handlePost(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException;

    /**
     * PUT handler.  In REST, used to create a resource.
     * <p>
     * Implementor should set the context type to something appropriate 
     * (<code>response.setContentType("text/plain");</code> in the response,
     * and write JSON data to the response. 
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
	public void handlePut(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException;
	
    /**
     * GET handler.  In REST, used to retrieve resources.
     * <p>
     * Implementor should set the context type to something appropriate 
     * (<code>response.setContentType("text/plain");</code> in the response,
     * and write JSON data to the response. 
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void handleGet(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException;

    /**
     * DELETE handler.  In REST, used to delete a resource.
     * <p>
     * Implementor should set the context type to something appropriate 
     * (<code>response.setContentType("text/plain");</code> in the response,
     * and write JSON data to the response. 
     * 
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void handleDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
