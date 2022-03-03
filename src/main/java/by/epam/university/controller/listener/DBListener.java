package by.epam.university.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;

public class DBListener implements ServletContextListener {

	private Logger logger = LogManager.getLogger();
	
    public DBListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
			ConnectionPoolManager.getInstance().getConnectionPool().dispose();
		
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         try {
			ConnectionPoolManager.getInstance().getConnectionPool().initPoolData();
		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR,e);
		}
    }
	
}
