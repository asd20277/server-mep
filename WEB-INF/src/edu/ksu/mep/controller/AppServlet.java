package edu.ksu.mep.controller;

import javax.servlet.*;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Enumeration;

import framework.controller.ControllerServlet;
import framework.service.*;
import edu.ksu.mep.utility.AppConstants;
import edu.ksu.mep.utility.*;


/**
 * Entry point for this web application, implemented as servlet.
 * 
 * @author FengShuo Yu
 */
public class AppServlet extends ControllerServlet {

	/**
	 * Setting up applicaiton root and  set up application properties path.
	 */
	protected void initParameters(ServletConfig config) throws ServletException{
		if (config != null) {
			// set up applicaiton root
			AppConstants.APP_ROOT = config.getInitParameter(AppConstants.APP_ROOT_TAG);
			AppConstants.ADS_ROOT = config.getInitParameter(AppConstants.ADS_ROOT_TAG);
			AppConstants.images_ROOT = config.getInitParameter(AppConstants.images_ROOT_TAG);
			// set up application properties path
			String relAppProperties = config.getInitParameter(AppConstants.APPLICATION_PROPERTIES_TAG);
			String path = AppConstants.APP_ROOT + relAppProperties;
			AppConstants.APPLICATION_PROPERTIES_PATH = path;
			
			AppConstants.DB_URL = config.getInitParameter(AppConstants.DB_URL_TAG);
			AppConstants.DB_DRIVER = config.getInitParameter(AppConstants.DB_DRIVER_TAG);
			AppConstants.DB_USER = config.getInitParameter(AppConstants.DB_USER_TAG);
			AppConstants.DB_PASSWORD = config.getInitParameter(AppConstants.DB_PASSWORD_TAG);
		} else {
			throw new ServletException("ServletConfig object is null.");
		}
	}	
	
	/**
	 * Load applicaiton specific properties.
	 */
	protected void loadAppProperties()throws ServletException{
		try {
			File propsFile = new File(AppConstants.APPLICATION_PROPERTIES_PATH);
			FileInputStream fis = new FileInputStream(propsFile);
			Configuration.getProperties().load(fis);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	protected void initAppComponent(){
		Enumeration enum2 = Configuration.getProperties().keys();
		while(enum2.hasMoreElements()) {
			String masterKey = (String) enum2.nextElement();
			// initialize application components
			if(masterKey.substring(0, 3).equals("app")){
				String clazName = (String) Configuration.getProperties().get(masterKey);
				try {
					Class claz = Class.forName(clazName);
					Method initMethod = claz.getMethod("init", new Class[0]); // invoke init() method
					initMethod.invoke(claz, new Object[0]);
				}catch(ClassNotFoundException e){
					e.printStackTrace();
				}catch(NoSuchMethodException e){
					e.printStackTrace();
				}catch(IllegalAccessException e){
					e.printStackTrace();
				}catch(InvocationTargetException e){
					e.printStackTrace();
				}
			}
		}	
	}
}
