package custom.servlets;

import java.io.*;
import java.net.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.text.*;
import custom.beans.*;
import com.ora.jsp.sql.*;
import com.ora.jsp.util.*;
import com.ora.jsp.sql.value.*;


public class PControllerServlet extends HttpServlet{

	/**
	 * Creates an UserRegistryBean and saves it as servlet 
	 * context attributes.
	 */
    public void init() throws ServletException {
		 		 
	}
	
	
	/**
	 * Removes the UserRegistryBean servlet context attribute.
	 */
	public void destroy(){
	}
	 
	 
	 
	
	/**
	 * Changes GET requests to POST requests.
	 */
	public void doGet(HttpServletRequest request,
	    HttpServletResponse response)
		throws IOException,ServletException {
		doPost(request,response);			
	}
	 
	 
	/**
     * Performs authentication, if needed, and access control.
     * If the user is authorized, performs the action specified by
     * the "action" request parameter.
     */
	public void doPost(HttpServletRequest request,
		HttpServletResponse response)
		throws IOException,ServletException{
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		
		// Check if the user is authenticated
		if(false){
			
		}
		else{
			 if ("showSchedule".equals(action)) {
                doShowSchedule(request, response);
            }
            else if ("showClass".equals(action)) {
                doShowClass(request, response);
            }        
			else {
				response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
			}
		}				
	}
	
	
	private void doShowSchedule(HttpServletRequest request, 
        HttpServletResponse response) {
		forward("schedule.jsp", request, response);
		}
	
	
	private void doShowClass(HttpServletRequest request, 
        HttpServletResponse response) {
		String CID = request.getParameter("CID");
		String URL = CID+".jsp";
		forward(URL, request, response);
		}
	
	 /**
     * Forwards the request to the login JSP page, with an
     * "errorMsg" parameter containing text to be displayed on
     * the login page.
     */
    private void doForwardToLogin(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
        String origURL = HttpUtils.getRequestURL(request).toString();
        String queryString = request.getQueryString();
        if (queryString != null) {
            origURL += "?" + queryString;
        }
        String loginURL = "login.jsp" + "?origURL=" + 
            URLEncoder.encode(origURL) +
            "&errorMsg=" + URLEncoder.encode("Please log in first");
        forward(loginURL, request, response);
    }
	
	
	
	
	/**
	 * Delete the event with the specified EID
	 */	
	
	private void doDeleteEvent(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
		String EID = request.getParameter("EID");
		
		HttpSession session = request.getSession();
		
		UserRegistryBean userReg = (UserRegistryBean) 
            getServletContext().getAttribute("userReg");
		
		try{
			userReg.deleteEvent(EID);
			UserBean user = userReg.getUser(((UserBean)session
				.getAttribute("validUser")).getUserName());
			session.setAttribute("validUser", user);
			String next = getShowPageURL(request) + "detail.jsp" + "&date=" + request.getParameter("date");
			response.sendRedirect(next);		
		}		
		catch (SQLException e) {
            throw new ServletException("Database error", e);		
		}
	}
	
	/**
	 * Show the specified page with the parameter "page".
	 */	
    private void doShowPage(HttpServletRequest request, 
        HttpServletResponse response) 
        throws IOException, ServletException {
        String url = request.getParameter("page");
        if (url == null) {
            throw new ServletException("Missing page info");
        }
        forward(url, request, response);
    }
	 
	 /**
     * Returns an absolute URL, suitable for redirecting a request 
     * back to this servlet, with an "action" parameter set to
     * "showPage" and a "page" parameter set to the specified
     * (relative) page URL. 
     */
    private String getShowPageURL(HttpServletRequest request) {
        return request.getContextPath() + request.getServletPath() + 
            "?action=showPage&page=";
    }
	 
	/**
     * Forwards the request to the specified relative URL.
     */
    private void forward(String url, HttpServletRequest request,
        HttpServletResponse response) 
        throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(url);
        rd.forward(request, response);
		
		
    }
	 
	 
	 
	 

}