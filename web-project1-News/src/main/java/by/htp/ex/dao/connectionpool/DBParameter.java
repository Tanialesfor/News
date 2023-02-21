package by.htp.ex.dao.connectionpool;

public class DBParameter {
	private DBParameter(){}

	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1/news?useSSL=false";
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "123456";
	public static final String DB_POLL_SIZE = "5"; 
}
