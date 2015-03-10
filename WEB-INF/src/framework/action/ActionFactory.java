package framework.action;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;
import framework.service.*;

/**
 * Factory for creating specific action. 
 * 
 * Simple Factory pattern.
 * 
 * @author FengShuo Yu
 */
public class ActionFactory {

	
	/**
	 * Hash storage for action
	 */
	private static HashMap map = new HashMap();
	/**
	 * Application root
	 */
	private static String appRoot = null;

	/**
	 * Constructor
	 */
	public ActionFactory() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Get action instance.
	 * 
	 * @param actionName
	 * @return Action object
	 */
	public static Action getAction(String actionName){	
		Action action = null;
		String className = (String) map.get(actionName);
		try {
			if(className != null && !className.equals(""))
				action = (Action) Class.forName(className).newInstance();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			//log.error("Error: ClassNotFoundException" + e.getMessage(), e);
		}catch(InstantiationException e){
			e.printStackTrace();
			//log.error("Error: InstantiationException" + e.getMessage(), e);
		}catch(IllegalAccessException e){
			e.printStackTrace();
			//log.error("Error: IllegalAccessException" + e.getMessage(), e);
		}
		
		return action;
	}
	
	/**
	 * Initialize action mapping.
	 */
	public static void init() throws IOException{
		Properties appProp = Configuration.getProperties();
		Enumeration enum2 = appProp.keys();
		while(enum2.hasMoreElements()) {
			String masterKey = (String) enum2.nextElement();	
			if(masterKey.substring(0, 6).equals("action")){
				String actionName = masterKey.substring(7);
				String className = (String) appProp.get(masterKey);
				map.put(actionName, className);
			}
		}
		
		
	}
}

 
 

