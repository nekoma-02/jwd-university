package by.epam.university.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import by.epam.university.dao.SQLUserDao;
import by.epam.university.dao.connectionpool.ConnectionPool;
import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;
import by.epam.university.dao.exception.DAOConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.dao.exception.DAOSQLException;
import by.epam.university.entity.Role;
import by.epam.university.entity.User;

public class UserDAOImpl implements SQLUserDao {

	private static Logger logger = LogManager.getLogger();

	private final static int LOG_ROUNDS = 12;
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

	private static final String INSER_USER = "insert into users(name,secondName,lastName,login,password,email,roles_id_role) "
			+ "values (?,?,?,?,?,?,?)";

	private static final String SELECT_USER_BY_PASSWORD_LOGIN = "select id_user,name,secondName,lastName,login,password,email,"
			+ "roles.role_name,gender,marital_status,place_of_birth,date_of_birth from users "
			+ "inner join roles on users.roles_id_role = roles.id_role where login = ? and password = ?";

	private static final String SELECT_USER_BY_LOGIN = "select id_user,name,secondName,lastName,login,password,email,"
			+ "roles.role_name,gender,marital_status,place_of_birth,date_of_birth from users "
			+ "inner join roles on users.roles_id_role = roles.id_role where login = ?";

	private static final String SELECT_USER_BY_ID = "select id_user,name,secondName,lastName,login,password,email,"
			+ "roles.role_name,gender,marital_status,place_of_birth,date_of_birth from users "
			+ "inner join roles on users.roles_id_role = roles.id_role where id_user = ?";
	private static final String SELECT_ALL_USERS = "select id_user,name,secondName,lastName,login,password,email,"
			+ "roles.role_name,gender,marital_status,place_of_birth,date_of_birth from users "
			+ "inner join roles on users.roles_id_role = roles.id_role";

	private static final String UPDATE_USER_BY_ID = "update users set name = ?,secondName = ?,lastName = ?,email = ?,"
			+ "gender = ?,marital_status = ?,place_of_birth = ?,date_of_birth = ? where id_user = ?";

	@Override
	public boolean insert(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		int nameInt = 1;
		int secondNameInt = 2;
		int lastNameInt = 3;
		int loginInt = 4;
		int passwordInt = 5;
		int emailInt = 6;
		int roleInt = 7;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSER_USER);

			ps.setString(nameInt, user.getName());
			ps.setString(secondNameInt, user.getSecondName());
			ps.setString(lastNameInt, user.getLastName());
			ps.setString(loginInt, user.getLogin());
			ps.setString(passwordInt, hashBCryptPassword(user.getPassword()));
			ps.setString(emailInt, user.getEmail());
			ps.setInt(roleInt, user.getRole().ordinal() + 1);

			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}

	}

	private User buildUser(ResultSet rs) throws SQLException {
		User user = null;

		int id = rs.getInt(1);
		String name = rs.getString(2);
		String secondName = rs.getString(3);
		String lastName = rs.getString(4);
		String login = rs.getString(5);
		String password = rs.getString(6);
		String email = rs.getString(7);
		Role role = Role.valueOf(rs.getString(8).toUpperCase());
		String gender = rs.getString(9);
		String maritalStatus = rs.getString(10);
		String placeOfBirth = rs.getString(11);
		Date birthDate = rs.getDate(12);

		user = new User(id, name, secondName, lastName, login, password, email, role, gender, maritalStatus,
				placeOfBirth, birthDate);
		return user;

	}

	@Override
	public User getUserByLoginPassword(String login, String password) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_PASSWORD_LOGIN);

			ps.setString(1, login);
			ps.setString(2, hashBCryptPassword(password));

			rs = ps.executeQuery();

			if (rs.next()) {
				user = buildUser(rs);
			}

			return user;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e.getMessage());
		} catch (SQLException e) {
			throw new DAOSQLException(e.getMessage());
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public User getUserByLogin(String login) throws DAOException {
		User user = null;

		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_LOGIN);

			ps.setString(1, login);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = buildUser(rs);
			}

			return user;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public User getUserById(int id) throws DAOException {
		User user = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_USER_BY_ID);

			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				user = buildUser(rs);
			}

			return user;

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
	public List<User> getAll() throws DAOException {
		List<User> userList = new ArrayList<User>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_USERS);

			while (rs.next()) {
				userList.add(buildUser(rs));
			}

			return userList;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	@Override
	public boolean updateUser(User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		int nameInt = 1;
		int secondNameInt = 2;
		int lastNameInt = 3;
		int emailInt = 4;
		int genderInt = 5;
		int maritalInt = 6;
		int placeInt = 7;
		int dateInt = 8;
		int idInt = 9;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_USER_BY_ID);

			ps.setString(nameInt, user.getName());
			ps.setString(secondNameInt, user.getSecondName());
			ps.setString(lastNameInt, user.getLastName());
			ps.setString(emailInt, user.getEmail());
			ps.setString(genderInt, user.getGender());
			ps.setString(maritalInt, user.getMaritalStatus());
			ps.setString(placeInt, user.getPlaceOfBirth());
			ps.setDate(dateInt, user.getDateOfBirth());
			ps.setInt(idInt, user.getId());

			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
	}

	private String hashBCryptPassword(String password) {
		String salt = BCrypt.gensalt(LOG_ROUNDS);
		return BCrypt.hashpw(password, salt);
	}

}
