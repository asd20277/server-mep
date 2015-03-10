package framework.service;
/**
 * Abstract service. This class define common functionality any service should provide.
 * 
 * @author FengShuo Yu
 */
public abstract class Service {
	public String serviceName = null;

	public Service(){
	}
	
	/**
	 * @return
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * @param string
	 */
	public void setServiceName(String string) {
		serviceName = string;
	}
	
	public Service(String serviceName){
		this.serviceName = serviceName;
	}

}
