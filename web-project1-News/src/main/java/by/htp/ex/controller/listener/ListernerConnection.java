package by.htp.ex.controller.listener;

import by.htp.ex.dao.connectionpool.ConnectionPool;
import by.htp.ex.dao.connectionpool.ConnectionPoolException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class ListernerConnection implements ServletContextListener {

	public void contextInitialized(ServletContextEvent servletContextEvent) {
    	ServletContext ctx = servletContextEvent.getServletContext();
       	ConnectionPool pool= ConnectionPool.getConnectionPool();
    	try {
			pool.initPoolData();
		} catch (ConnectionPoolException e) {
			System.out.println("Pool connection don't init.");
			e.printStackTrace();
		}
    	ctx.setAttribute("Pool", pool);
    	System.out.println("Pool connection initialized for project.");
    	
    }
	
	 public void contextDestroyed(ServletContextEvent servletContextEvent) {
	    	ServletContext ctx = servletContextEvent.getServletContext();
	    	ConnectionPool pool = (ConnectionPool) ctx.getAttribute("Pool");
	    	if (pool!=null){
		    	pool.dispose();
		    	System.out.println("Pool connection closed for project.");
	    		
	    	} else {
	    		System.out.println("Pool connection already closed.");
	    	}
	 }
}