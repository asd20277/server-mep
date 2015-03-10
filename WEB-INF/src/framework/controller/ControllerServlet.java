package framework.controller;

import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
import java.sql.*;

import framework.action.AbstractAction;
import framework.action.ActionFactory;
import framework.action.DefaultAction;
import framework.bean.*;

/**
 * Entry point for this web application, implemented as servlet.
 * 
 * @author FengShuo Yu
 */
public abstract class ControllerServlet extends HttpServlet {
	
	ServletContext servletContext = null; // will need to use this when calling JSP
	
	/**/
	public void doGet(HttpServletRequest req, HttpServletResponse response) throws IOException {
		try{
			perform(req, response);
		}catch(Exception e){
		}
		
	}
	
	/**/
	public void doPost(HttpServletRequest req, HttpServletResponse response) throws IOException {
		try{
			perform(req, response);
		}catch(Exception e){
		}
	}	
	
	public void init(ServletConfig config) throws ServletException {
		servletContext = config.getServletContext(); 
		// initialize web application parameters from servlet config
		initParameters(config);
		// load application specific properties
		loadAppProperties();
		// Initialize application components
		initAppComponent();
	}	
	

	/**
	 * Initialize application components.
	 *
	 */	
	protected abstract void initAppComponent();

	
	/**
	 * Load applicaiton specific properties.
	 * 
	 * @throws ServletException
	 */
	protected abstract void loadAppProperties()throws ServletException;

	/**
	 * Initialize application specific parameters.
	 * 
	 * @param config
	 * @throws ServletException
	 */
	protected abstract void initParameters(ServletConfig config) throws ServletException;
	
	/**
	 * 
	 * @param req
	 * @param response
	 * @param runtimeReq
	 */
	private void initRequest(HttpServletRequest req, HttpServletResponse response, RuntimeRequest runtimeReq) {
		runtimeReq.setReq(req);
		runtimeReq.setResponse(response);
		runtimeReq.setServletContext(this.servletContext);
	}	
	
	/**
	 * A simple workflow for processing client's request. 
	 * 
	 * Using Template pattern and command pattern.
	 * 
	 * @param req
	 * @param response
	 * @throws IOException
	 * @throws SQLException
	 */
	private void perform(HttpServletRequest req, HttpServletResponse response) throws IOException, SQLException {
		RuntimeRequest request = new RuntimeRequest();
		initRequest(req, response, request);
		AbstractAction ea = (AbstractAction) getAction(request);	
		ea.execute(request);	
	}
	
	private AbstractAction getAction(RuntimeRequest request) {
		String destination = request.getReq().getServletPath();
		destination = destination.substring(1, destination.length());
		AbstractAction action = (AbstractAction) ActionFactory.getAction(destination);
		if(action == null){
			action = new DefaultAction();
		}
		return action;
	}	
	
	protected void service(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		try{
			perform(req, response);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
