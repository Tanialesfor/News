package by.htp.ex.dao.impl.connectionpool;

public class ConnectionPoolException extends Exception {
private static final long serialVersionUID = 1L;
	
	public ConnectionPoolException(String message, Exception e){
	 super(message, e);
	 } 
}
