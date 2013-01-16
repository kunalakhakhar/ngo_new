package business;

import java.sql.Connection;

import util.GetSqlDbConnection;

public class BaseService {

	protected static Connection conn = null;

	public BaseService() {
		if (conn == null) {
			connectToMySql();
		}
	}
	
	
	private void connectToMySql(){
		conn = GetSqlDbConnection.getDbConnection();
	}

}
