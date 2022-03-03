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

import by.epam.university.dao.SQLPrivilegeDao;
import by.epam.university.dao.connectionpool.ConnectionPool;
import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;
import by.epam.university.dao.exception.DAOConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.dao.exception.DAOSQLException;
import by.epam.university.entity.Privilege;

public class PrivilegeDAOImpl implements SQLPrivilegeDao {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();
	
	private static final String SELECT_ALL_PRIVILEGES = "select * from stud_privileges";
	private static final String SELECT_PRIVILEGE_BY_NAME = "select * from stud_privileges where name = ?";
	private static final String SELECT_PRIVILEGE_BY_ID = "select * from stud_privileges where idprivilege = ?";
	private static final String INSERT_PRIVILEGE = "insert into stud_privileges(name) values (?)";
	private static final String UPDATE_PRIVILEGE = "update stud_privileges set name = ? where idprivilege = ? ";
	private static final String REMOVE_PRIVILEGE = "delete from stud_privileges where idprivilege = ?";
	
	
	@Override
	public List<Privilege> getAll() throws DAOException {
		List<Privilege> listPrivileges = new ArrayList<Privilege>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;


		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_PRIVILEGES);

			while (rs.next()) {
				listPrivileges.add(new Privilege(rs.getInt(idInt), rs.getString(nameInt)));
			}

			return listPrivileges;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	@Override
	public Privilege getByName(String name) throws DAOException {
		Privilege privilege = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;


		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_PRIVILEGE_BY_NAME);
			ps.setString(1,name);
			rs = ps.executeQuery();

			if (rs.next()) {
				privilege = new Privilege(rs.getInt(idInt), rs.getString(nameInt));
			}

			return privilege;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
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
	public Privilege getById(int id) throws DAOException {
		Privilege privilege = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int idInt = 1;
		int nameInt = 2;


		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_PRIVILEGE_BY_ID);
			ps.setInt(1,id);
			rs = ps.executeQuery();

			if (rs.next()) {
				privilege = new Privilege(rs.getInt(idInt), rs.getString(nameInt));
			}

			return privilege;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			logger.log(Level.ERROR, e);
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
	public boolean insert(Privilege privilege) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;


		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_PRIVILEGE);

			ps.setString(1, privilege.getName());

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
	public boolean update(Privilege privilege) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_PRIVILEGE);

			ps.setString(1, privilege.getName());
			ps.setInt(2, privilege.getId());
			

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
	public boolean remove(int idPrivilege) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_PRIVILEGE);

			ps.setInt(1, idPrivilege);
			
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
