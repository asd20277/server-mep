/*
 * Created on 2005/10/16
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package framework.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import framework.bean.RuntimeRequest;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DefaultAction extends AbstractAction {

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#doUpdate(framework.bean.RuntimeRequest)
	 */
	protected void doUpdate(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#doDelete(framework.bean.RuntimeRequest)
	 */
	protected void doDelete(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#doInsert(framework.bean.RuntimeRequest)
	 */
	protected void doInsert(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#getModel(framework.bean.RuntimeRequest)
	 */
	protected Collection getModel(RuntimeRequest request) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#doView(framework.bean.RuntimeRequest)
	 */
	protected void doView(RuntimeRequest request)
		throws SQLException, IOException {
		// if come here, that means add new member succeeded
		// so, go and see the shopping card summary
		//javax.servlet.RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/defaultAction.html");
		try {
			request.getResponse().sendRedirect("/ReqApp/defaultAction.html"); 
		}catch(Exception e){
			System.out.println("Error: Can not doView() in DefaultAction");
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#getModuleName()
	 */
	protected String getModuleName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#updateModel(framework.bean.RuntimeRequest)
	 */
	public void updateModel(RuntimeRequest request) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#process(framework.bean.RuntimeRequest)
	 */
	public void process(RuntimeRequest request)
		throws SQLException, IOException {
			doView(request);
	}

	/* (non-Javadoc)
	 * @see framework.action.AbstractAction#syncModelWithGUI(framework.bean.RuntimeRequest)
	 */
	public void syncModelWithGUI(RuntimeRequest request) throws IOException {
		// TODO Auto-generated method stub

	}

}
