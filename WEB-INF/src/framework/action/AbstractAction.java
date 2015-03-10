package framework.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import framework.service.*;
import framework.bean.*;

/**
 * Abstract parent class for all the action classes in elis project. This class applies the 
 * Command design pattern and Template design pattern.
 *  
 * @author FengShuo Yu
 */
public abstract class AbstractAction implements Action{
	/**
	 * Action name.
	 */
	private String actionName = null;
	/**
	 * Hash storage for putting model.
	 */
	private HashMap hashStore = new HashMap();
	/**
	 * Constructor.
	 * 
	 * @see java.lang.Object#Object()
	 */
	public AbstractAction() {
	}
	
	public Service getService(String serviceName){
		return ServiceLocator.getService(serviceName);
	}
	
	public AbstractAction(String actionName) {
		this.actionName = actionName;
	}
	
	/**
	 * Execute user request. This method contains work flow algothrim for action. Subclass
	 * needs to provide the implementation if needed.
	 * 
	 * @param request
	 * @throws IOException
	 */
	public void execute(RuntimeRequest request) throws SQLException, IOException {
		// synchronize the form bean with user input data
		syncModelWithGUI(request); 
 
		updateModel(request/* model */);
		// calling specific action class to perform the processing
		process(request);
	}
	protected void addAttributeToSession(RuntimeRequest request, String name, Object obj){
		request.getReq().getSession().setAttribute(name, obj);
	}

	protected void removeAttributeToSession(RuntimeRequest request, String name, Object obj){
		request.getReq().getSession().removeAttribute(name);
	}
	
	protected void addAttributeToRequest(RuntimeRequest request, String name, Object obj){
		request.getReq().setAttribute(name, obj);
	}
	
	protected void removeAttributeFromRequest(RuntimeRequest request, String name){
		request.getReq().removeAttribute(name);
	}

	protected Object getAttributeFromSession(RuntimeRequest request, String name){
		return request.getReq().getSession().getAttribute(name);
	}
	
	protected Object getAttributeFromRequest(RuntimeRequest request, String name){
		return request.getReq().getAttribute(name);
	}
	
	protected void setDestination(RuntimeRequest request, String dest){
		addAttributeToRequest(request, "destination", dest);	
	}
	
	protected void setPreviousDestination(RuntimeRequest request, String dest){
		addAttributeToSession(request, "previousDestination", dest);		
	}	
	
	protected Object getPreviousDestination(RuntimeRequest request){
		return request.getReq().getSession().getAttribute("previousDestination");
	}
	
	protected String getDestination(RuntimeRequest request){
		return (String) getAttributeFromRequest(request, "destination");	
	}
	/**
	 * Perform update.
	 * @param request
	 */
	protected abstract void doUpdate(RuntimeRequest request);
	/**
	 * Perform delete.
	 * @param request
	 */
	protected abstract void doDelete(RuntimeRequest request);
	
	/**
	 * Perfrom insert.
	 * @param request
	 */
	protected abstract void doInsert(RuntimeRequest request);
	/**
	 * Retrieve model data for next view.
	 * 
	 * @param request
	 * @return Collection
	 * @throws SQLException
	 */
	protected abstract Collection getModel(RuntimeRequest request) throws SQLException;
	
	/**
	 * Perform view display.
	 * 
	 * @param request
	 * @throws IOException
	 */
	protected abstract void doView(RuntimeRequest request) throws SQLException, IOException;
	
	/**
	 * Subclass needs to override this method to return appropriate service. The default 
	 * is return null.
	 * 
	 * @return ElisService
	 */
	protected Service getService() {
		return null;
	}
	
	/**
	 * Return name for access right checking.
	 * 
	 * @return String
	 */
	protected abstract String getModuleName();
	
	
	/**
	 * Update form bean data to database.
	 * @param request
	 */
	public abstract void updateModel(RuntimeRequest request);
	
	/**
	 * Check to see if the user has access right for this module.
	 * 
	 * @param request
	 * @return boolean
	 */
	protected boolean hasPermission(RuntimeRequest request){
		boolean result = true;
		HttpSession session = request.getReq().getSession();
		Boolean access = (Boolean)session.getAttribute(getModuleName());
		
		if (access != null) {
			return access.booleanValue();
		} else {
			// for module that doesn't have access, it means no access is enforce on this module
			return result;
		}
	}
	
	/**
	 * Process user request.
	 * 
	 * @param request
	 * @throws IOException
	 */
	public abstract void process(RuntimeRequest request) throws SQLException, IOException;
	
	/**
	 * Synchronize the form bean data with the user input from front end.
	 * 
	 * @param request
	 * @throws IOException
	 */
	public abstract void syncModelWithGUI(RuntimeRequest request) throws IOException;
	
	/**
	 * Return action name.
	 * 
	 * @return String
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * Sets the actionName.
	 * @param actionName The actionName to set
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return HashMap
	 */
	public HashMap getHashStore() {
		return hashStore;
	}

	/**
	 * Sets the hashStore.
	 * @param hashStore The hashStore to set
	 */
	public void setHashStore(HashMap hashStore) {
		this.hashStore = hashStore;
	}

	/**
	 * Clean up attributes in http session object.
	 * 
	 * @param request
	 */
	protected void cleanupSession(RuntimeRequest request){
		HttpSession session = request.getReq().getSession();
	}
	
	protected String upperCase(String org){
		String result = null;
		if(org != null) 
			result = org.toUpperCase();		
		return result;
	}
	
	protected String lowerCase(String org){
		String result = null;
		if(org != null) 
			result = org.toLowerCase();		
		return result;
	}	
}
