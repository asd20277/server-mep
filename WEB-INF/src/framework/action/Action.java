package framework.action;

import java.io.IOException;
import java.sql.SQLException;
import framework.bean.*;


/**
 * The parent class for all Action subclass.
 * 
 * @author FengShuo Yu
 */
public interface Action { 

	/**
	 * Execute action.
	 * 
	 * @param elisRequest
	 * @throws SQLException
	 * @throws IOException
	 */
	public abstract void execute(RuntimeRequest request) throws SQLException, IOException;

}
