package framework.service;

import java.util.*;


/**
 * Simple service locator pattern. A singleton.
 * 
 * @author FengShuo Yu
 */
public class ServiceLocator {
	/**
	 * Lookup table.
	 */
	private static Hashtable lookupTable = new Hashtable();
	/**
	 * 
	 */
	private static HashMap map = new HashMap();
	/**
	 * Get lookup table
	 * @return
	 */
	public static Hashtable getLookupTable() {
		return lookupTable;
	}

	/**
	 * Set lookup table.
	 * 
	 * @param hashtable
	 */
	public static void setLookupTable(Hashtable hashtable) {
		lookupTable = hashtable;
	}
	
	public static Service getService(String serviceName){
		return (Service)getLookupTable().get(serviceName);
	}
	
	/**
	 * Initialize service related tasks.
	 *
	 */
	public static void init(){
		Properties appProp = Configuration.getProperties();
		Enumeration services = appProp.keys();
		while(services.hasMoreElements()) {
			try{
				String masterKey = (String) services.nextElement();	
				if(masterKey.length() > 8 && masterKey.substring(0, 7).equals("service")){
					String serviceName = masterKey.substring(8);
					String className = (String) appProp.get(masterKey);
					
					map.put(serviceName, className);
					try {
						// catch exception and keep going
						lookupTable.put(serviceName, Class.forName(className).newInstance());
						//log.info(serviceName +  " is initialized");
					}catch(ClassNotFoundException e) {
						//log.error(e.getMessage(), e);
					}catch(InstantiationException e){
						//log.error(e.getMessage(), e);
					}catch(IllegalAccessException e){
						//log.error(e.getMessage(), e);
					}
				}
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}	

}
