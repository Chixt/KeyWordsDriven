

package com;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

public abstract class AbstractRestServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {


  
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * The name of an HTTP POST attribute or GET query variable whose
     * value is a JSON string that contains the input for various services.
     * 
     */
    public final static String PARAM_ID_SERIALIZED = "serializedObject";


    
    public enum RestVerb 
    { 
        /** Use POST to update objects or perform operations against them */
        POST,

        /** 
         * Use GET to retrieve information about objects and perform other "safe" operations.
         *  A GET request must be a free of side-effects 
         */
        GET,

        /** Use DELETE to delete an object */
        DELETE, 

        /** Use PUT to create an object */
        PUT

        // OPTIONS (returns a list of supported verbs in the 'Accept' header)?
        // HEAD (a GET that returns headers only)?
    }

    /**
     * The name of an attribute that we add to the request object which will be a Map of the
     * path variables name and value pairs that a particular handler knows about.
     */
    private static final String PATH_VARIABLE_MAP_REQUEST_ATTRIBUTE_NAME = "com.testCase.pathVariableMap";

    public AbstractRestServlet() {
        super();
    }  

   
    public abstract IRestResourceHandler[] getRestResourceHandlers();


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
        processTransaction(RestVerb.GET, request, response);
    }  	

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTransaction(RestVerb.POST, request, response);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTransaction(RestVerb.DELETE, request, response);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processTransaction(RestVerb.PUT, request, response);
    }

    /**
     * Services a RESTful request by locating and calling the appropriate handler.
     * 
     * @param restVerb
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    private void processTransaction (RestVerb restVerb, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        IRestResourceHandler[] restHandlersForServlet = getRestResourceHandlers();

        IRestResourceHandler handlerForRequest = null;
        Matcher uriMatcher = null;

        /* 
         * Attempt to locate the handler which accepts this URI and REST verb. 
         */
        if (restHandlersForServlet!=null)
        {
            String pathInfo = (request.getPathInfo()==null ? "" : request.getPathInfo());

            for (IRestResourceHandler candidateHandler : restHandlersForServlet) 
            {    			
                uriMatcher = candidateHandler.getRestUriPattern().matcher(pathInfo);

                if (uriMatcher.matches())
                {
                    // We found the proper handler for this request.
                    // Note that the handle may not support this REST verb.
                    // If not, this is an error and the handler will throw
                    // an exception.
                    handlerForRequest = candidateHandler;
                    break;
                }
            }
        }

        /*
         * Forward the request to the handler or send an error if a handler 
         * was not found.
         */
        if(handlerForRequest != null)
        {
            // We found the proper handler for the request.  For convenience, inject
            // the attributes into the request.  The attributes were indicated as capturing
            // groups in the regular expression.            
            String[] attribNames = handlerForRequest.getRestPathVariableNames();
            if (attribNames!=null && attribNames.length>0 && uriMatcher.groupCount()==attribNames.length)
            {
                // Inject attributes
                // Assuming the parms come in the order specified by the RE (need to document)
                // Optional parameters are not supported
                Map<String, String> pathVariableMap = new HashMap<String,String>(attribNames.length);

                for (int i=0; i<attribNames.length; i++)
                {
                    pathVariableMap.put(attribNames[i], uriMatcher.group(i+1));
                }                           

                if(pathVariableMap.size() > 0)
                {
                    // Servlet Javadoc suggests package-qualifed name here.  So we will put
                    // a map in the request with such a name, which will free the handlers to
                    // use shorter names.
                    request.setAttribute(PATH_VARIABLE_MAP_REQUEST_ATTRIBUTE_NAME, pathVariableMap);
                }
            }

            try
            {
                // Call Resource Handler
                switch (restVerb)
                {
                    case DELETE:
                        handlerForRequest.handleDelete(request, response);
                        break;
                    case GET:
                        handlerForRequest.handleGet(request, response);
                        break;
                    case POST:
                        handlerForRequest.handlePost(request, response);
                        break;
                    case PUT:
                        handlerForRequest.handlePut(request, response);
                        break;
                    default:
                        // Should never happen
                        response.sendError(HttpServletResponse.SC_NOT_FOUND);
                    break;
                }
            }
            
            catch (Exception ex)
            {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        }
        else
        {
            // If it gets here, the specified resource path is invalid, or
            // a regular expression could have rejected the format of a particular
            // attribute.
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    /**
     * We created a Map object as a way to give the handler easy access to the
     * path variables in the request URI.  The handler can use this to access that object.
     * 
     * @param request We stashed the map in the request object with a package-qualified name.
     * 
     * @return A Map object or null if the handler has no path variables.
     */
    @SuppressWarnings("unchecked")
	public static Map<String, String> getRestPathVariableMap(HttpServletRequest request)
    {
        return (Map<String, String>)request.getAttribute(PATH_VARIABLE_MAP_REQUEST_ATTRIBUTE_NAME);
    }

    /**
     * Retrieve the "serializedObject" parameter from the request.
     * The serialized object is optional, so null may be returned.
     * 
     * @param request
     * @return
     * @throws IOException
     */
    public static JSONObject getOptionalSerializedObjectParameter(HttpServletRequest request) throws IOException
    {
        String value = request.getParameter(PARAM_ID_SERIALIZED);

        if(value != null)
            return JSONObject.fromString(value);
        else
            return null;       
    }

    /**
     * Retrieve the "serializedObject" parameter from the request.
     * The parameter is required: IllegalArgumentException is thrown if
     * it was not specified or is an empty object.  
     * 
     * @param request
     * @return
     * @throws IOException
     */
    public static JSONObject getRequiredSerializedObjectParameter(HttpServletRequest request) throws IOException
    {
        String value = request.getParameter(PARAM_ID_SERIALIZED);

        if(value != null)
        {
            JSONObject serialObj =  JSONObject.fromString(value);
            if(serialObj.isEmpty())
                throw new IllegalArgumentException(PARAM_ID_SERIALIZED);
            
            return serialObj;
        }
        else
            throw new IllegalArgumentException(PARAM_ID_SERIALIZED);       
    }

    /**
     * Helper method to deal with the drudgery of putting information in the response to the client.
     * 
     * @param result The JSONObject to send to the client.  If null, an empty JSON object is returned.
     */
    public static void writeResponse(JSONObject result, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        String serialized;
        if(result != null)
            serialized = result.toString();
        else
            serialized = "{}";

        // Noticed this in Junit.  Setting the content length may make for more efficient processing
        // on the client.  Need to do this before the data is written to the stream or else WAS considers
        // the response locked.
        response.setContentLength(serialized.length());

        out.write(serialized);
        out.close();
    }
    
    public static void writeStringResponse(String result, HttpServletResponse response) throws IOException
    {
        response.setContentType("text/plain");
        PrintWriter out = response.getWriter();
        
        // Noticed this in Junit.  Setting the content length may make for more efficient processing
        // on the client.  Need to do this before the data is written to the stream or else WAS considers
        // the response locked.
        response.setContentLength(result.length());

        out.write(result);
        out.close();    	
    }
}