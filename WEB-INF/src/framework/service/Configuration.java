package framework.service;

import java.util.*;

/**
 * @author FengShuo Yu
 *
 */
public class Configuration extends Service {
	private static Properties properties = new Properties(); 
	
	public Configuration(){
		super("Configuration Service");
	}

	/**
	 * @return
	 */
	public static Properties getProperties() {
		return properties;
	}
}
