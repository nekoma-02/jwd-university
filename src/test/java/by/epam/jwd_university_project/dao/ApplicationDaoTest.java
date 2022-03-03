package by.epam.jwd_university_project.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SQLApplicationDao;
import by.epam.university.dao.connectionpool.ConnectionPool;
import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Application;

public class ApplicationDaoTest  {
	
	private  ConnectionPool pool;
	private  Connection connection;
	
	@Before
	public  void initConnectionPool() throws ConnectionPoolException, SQLException {
		ConnectionPoolManager.getInstance().getConnectionPool().initPoolData();
		pool = ConnectionPoolManager.getInstance().getConnectionPool();
		connection = pool.takeConnection();
		
		
	}
	
	@After
	public  void disposeConnectionPool() throws SQLException {
		pool.releaseConnection(connection);
		pool.dispose();
	}
	
	
	@Test
	public void acceptStudent_whenIdApplicationDoesntExists_thenFalse() throws DAOException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		boolean actual = appDao.acceptStudent(0, false, 15);
		boolean expected = false;
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void acceptStudent_whenIdApplicationExist_thenTrue() throws DAOException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		boolean actual = appDao.acceptStudent(0, false, 1);
		boolean expected = true;
		
		Assert.assertEquals(expected, actual);
	}

	@Test
	public void applicationByUserId_whenIdApplicationDoesntExistx_thenNull() throws DAOException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		Application actual = appDao.applicationByUserId(15);
		Application expected = null;
		
		Assert.assertEquals(expected, actual);
	}
	
	@Test
	public void applicationById_whenIdApplicationDoesntExistx_thenNull() throws DAOException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		Application actual = appDao.applicationById(15);
		Application expected = null;
		
		Assert.assertEquals(expected, actual);
	}
}
