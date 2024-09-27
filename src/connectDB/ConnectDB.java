package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
	public static Connection con = null;
	private static ConnectDB instance = new ConnectDB();
	
	public static ConnectDB getInstance() {
		return instance;
	
	}
	
	public static void connect() throws SQLException {
		String url = "jdbc:sqlserver://localhost:1433;databasename=Java_QuanCoffe";
		String user = "sa";
		String password = "123";
		con = DriverManager.getConnection(url, user, password);
	}
	
	public static void disconnect() {
		if(con != null) {
			try {
				con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Connection getConnection() {
		return con;
	}
	public static void main(String[] args) {
		try {
			new ConnectDB().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
