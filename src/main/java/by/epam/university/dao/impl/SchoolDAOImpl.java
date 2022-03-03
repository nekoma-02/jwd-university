package by.epam.university.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.university.dao.SQLSchoolDao;
import by.epam.university.dao.connectionpool.ConnectionPool;
import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;
import by.epam.university.dao.exception.DAOConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.dao.exception.DAOSQLException;
import by.epam.university.entity.School;

public class SchoolDAOImpl implements SQLSchoolDao {
	
	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();
	
	private static final String SELECT_ALL_SCHOOLS = "select * from schools";
	private static final String SELECT_SCHOOL_BY_NAME = "select * from schools where name = ?";
	private static final String SELECT_SCHOOL_BY_ID = "select * from schools where idschool = ?";
	private static final String INSERT_SCHOOL = "insert into schools(name,level,institution) values (?,?,?)";
	private static final String UPDATE_SCHOOL = "update schools set name = ?, level = ?, institution = ? where idschool = ?";
	private static final String REMOVE_SCHOOL = "delete from schools where idschool = ?";
	
	@Override
	public List<School> getAll() throws DAOException {
		List<School> listSchools = new ArrayList<School>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;
		int levelInt = 3;
		int institutionInt = 4;


		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_SCHOOLS);

			while (rs.next()) {
				listSchools.add(new School(rs.getInt(idInt), rs.getString(nameInt),rs.getString(levelInt),rs.getString(institutionInt)));
			}

			return listSchools;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	@Override
	public School getByName(String name) throws DAOException {
		School school = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;
		int levelInt = 3;
		int institutionInt = 4;


		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SCHOOL_BY_NAME);
			ps.setString(1,name);
			rs = ps.executeQuery();

			if (rs.next()) {
				school = new School(rs.getInt(idInt), rs.getString(nameInt),rs.getString(levelInt),rs.getString(institutionInt));
			}

			return school;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}
	
	private void closeConnection(Connection con, Statement st, ResultSet rs) {
		try {
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, "Connection isn't closed.");
		}
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "ResultSet isn't closed.");
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Statement isn't closed.");
		}

	}

	@Override
	public School getById(int id) throws DAOException {
		School school = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;
		int levelInt = 3;
		int institutionInt = 4;


		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SCHOOL_BY_ID);
			ps.setInt(1,id);
			rs = ps.executeQuery();

			if (rs.next()) {
				school = new School(rs.getInt(idInt), rs.getString(nameInt),rs.getString(levelInt),rs.getString(institutionInt));
			}

			return school;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}
	
	private void closeConnection(Connection con, Statement st) {
		try {
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
			logger.log(Level.ERROR, "Connection isn't closed.");
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			logger.log(Level.ERROR, "Statement isn't closed.");
		}

	}

	@Override
	public boolean insert(School school) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;


		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_SCHOOL);

			ps.setString(1, school.getName());
			ps.setString(2, school.getLevel());
			ps.setString(3, school.getInstitution());

			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
	}

	@Override
	public boolean update(School school) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_SCHOOL);

			ps.setString(1, school.getName());
			ps.setString(2, school.getLevel());
			ps.setString(3, school.getInstitution());
			ps.setInt(4, school.getId());
			

			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
	}

	@Override
	public boolean remove(int idSchool) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_SCHOOL);

			ps.setInt(1, idSchool);
			
			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
	}

}
