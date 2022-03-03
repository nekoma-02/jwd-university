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

import by.epam.university.dao.SQLApplicationDao;
import by.epam.university.dao.connectionpool.ConnectionPool;
import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;
import by.epam.university.dao.exception.DAOConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.dao.exception.DAOSQLException;
import by.epam.university.entity.Application;
import by.epam.university.entity.ExamMark;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.TypeStudy;
import by.epam.university.entity.User;

public class ApplicationDAOImpl implements SQLApplicationDao {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

	private static final String SELECT_APPLICATION_BY_USERID = "select applications.idapplication, applications.adress, "
			+ "applications.certificate, stud_privileges.idprivilege, users.id_user, schools.idschool, specialties.idspecialty,"
			+ "applications.confirmation,applications.type_document,applications.id_document,applications.series_passport,"
			+ "applications.number_passport,applications.issued_by,applications.end_study_date "
			+ "from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege "
			+ "inner join users on applications.users_id_user = users.id_user "
			+ "inner join schools on applications.schools_idschool=schools.idschool "
			+ "inner join specialties on applications.specialties_idspecialty = specialties.idspecialty "
			+ "where users.id_user = ?";
	
	private static final String INSERT_APPLICATION = "insert into applications(adress,certificate,privileges_idprivilege,"
			+ "users_id_user,schools_idschool,specialties_idspecialty,confirmation,type_document,id_document,series_passport,"
			+ "number_passport,issued_by,end_study_date) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String UPDATE_APPLICATION = "update applications set adress = ?,certificate = ?,privileges_idprivilege = ?,"
			+ "users_id_user = ?,schools_idschool = ?,specialties_idspecialty = ?,confirmation = ?,type_document = ?,"
			+ "id_document = ?,series_passport = ?,number_passport = ?,issued_by = ?,end_study_date = ? "
			+ "where idapplication = ?";
	
	private static final String SELECT_APPLICATION_BY_UNCONFIRMED = "select applications.idapplication, applications.adress, "
			+ "applications.certificate, applications.confirmation, applications.type_document, applications.id_document,"
			+ "applications.series_passport,applications.number_passport,applications.issued_by,applications.end_study_date,"
			+ "stud_privileges.idprivilege, stud_privileges.name,users.id_user, users.name, users.secondname,"
			+ "users.lastname,schools.idschool,specialties.idspecialty, specialties.specialty_name,faculties.idfaculty, "
			+ "faculties.faculty_name,types_study.idtype_study, types_study.type_name "
			+ "from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege "
			+ "inner join users on applications.users_id_user = users.id_user "
			+ "inner join schools on applications.schools_idschool=schools.idschool "
			+ "inner join specialties on applications.specialties_idspecialty = specialties.idspecialty "
			+ "inner join types_study on specialties.idtype_study = types_study.idtype_study "
			+ "inner join faculties on specialties.faculties_idfaculty = faculties.idfaculty "
			+ "where confirmation = 0";
	
	private static final String SELECT_APPLICATION_BY_CONFIRMED = "select applications.idapplication, "
			+ "applications.adress, applications.certificate, applications.confirmation, applications.type_document, "
			+ "applications.id_document,applications.series_passport,applications.number_passport,applications.issued_by,"
			+ "applications.end_study_date,stud_privileges.idprivilege, stud_privileges.name,users.id_user, users.name,"
			+ " users.secondname,users.lastname,schools.idschool,specialties.idspecialty, specialties.specialty_name,"
			+ "faculties.idfaculty, faculties.faculty_name,types_study.idtype_study, types_study.type_name "
			+ "from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege "
			+ "inner join users on applications.users_id_user = users.id_user "
			+ "inner join schools on applications.schools_idschool=schools.idschool "
			+ "inner join specialties on applications.specialties_idspecialty = specialties.idspecialty "
			+ "inner join types_study on specialties.idtype_study = types_study.idtype_study "
			+ "inner join faculties on specialties.faculties_idfaculty = faculties.idfaculty"
			+ " where confirmation = 1";
	
	private static final String SELECT_APPLICATION = "select applications.idapplication, applications.adress, "
			+ "applications.certificate, applications.confirmation, applications.type_document, "
			+ "applications.id_document,applications.series_passport,applications.number_passport,"
			+ "applications.issued_by,applications.end_study_date,stud_privileges.idprivilege, "
			+ "stud_privileges.name,users.id_user, users.name, users.secondname,users.lastname,schools.idschool,"
			+ "specialties.idspecialty, specialties.specialty_name,faculties.idfaculty, faculties.faculty_name,"
			+ "types_study.idtype_study, types_study.type_name"
			+ " from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege "
			+ "inner join users on applications.users_id_user = users.id_user"
			+ " inner join schools on applications.schools_idschool=schools.idschool"
			+ " inner join specialties on applications.specialties_idspecialty = specialties.idspecialty "
			+ "inner join types_study on specialties.idtype_study = types_study.idtype_study"
			+ " inner join faculties on specialties.faculties_idfaculty = faculties.idfaculty";
	
	private static final String SELECT_APPLICATION_BY_ID = "select applications.idapplication, applications.adress, "
			+ "applications.certificate, applications.confirmation, applications.type_document, "
			+ "applications.id_document,applications.series_passport,applications.number_passport,"
			+ "applications.issued_by,applications.end_study_date,stud_privileges.idprivilege, "
			+ "stud_privileges.name,users.id_user, users.name, users.secondname,users.lastname,schools.idschool,"
			+ "specialties.idspecialty, specialties.specialty_name,faculties.idfaculty, faculties.faculty_name,"
			+ "types_study.idtype_study, types_study.type_name"
			+ " from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege "
			+ "inner join users on applications.users_id_user = users.id_user"
			+ " inner join schools on applications.schools_idschool=schools.idschool"
			+ " inner join specialties on applications.specialties_idspecialty = specialties.idspecialty "
			+ "inner join types_study on specialties.idtype_study = types_study.idtype_study"
			+ " inner join faculties on specialties.faculties_idfaculty = faculties.idfaculty where applications.idapplication = ?";
	
	private static final String INSER_MARK = "insert into exams(applications_idapplication,specialties_has_subjects_id,mark) value (?,?,?)";
	private static final String CONFIRM_APPLICATION = "update applications set confirmation = ? where idapplication = ?";
	
	private static final String SELECT_EXAMS = "select subjects_idsubject, mark "
			+ "from exams inner join specialties_has_subjects on exams.specialties_has_subjects_id=specialties_has_subjects.id "
			+ "where applications_idapplication = ?";
	
	private static final String UPDATE_MARK = "update exams set mark = ? where applications_idapplication = ? and "
			+ "specialties_has_subjects_id = ?";
	
	private static final String SELECT_CONFIRMATION = "select confirmation from applications where confirmation = ?";
	
	private static final String UPDATE_USER_BY_ID = "update users set name = ?,secondName = ?,lastName = ?,email = ?,gender = ?,"
			+ "marital_status = ?,place_of_birth = ?,date_of_birth = ? where id_user = ?";
	
	private static final String SELECT_APPLICATION_BY_SPECIALTYID = "select applications.idapplication, applications.adress, "
			+ "applications.certificate, applications.confirmation, applications.type_document, applications.id_document,"
			+ "applications.series_passport,applications.number_passport,applications.issued_by,applications.end_study_date,"
			+ "stud_privileges.idprivilege, stud_privileges.name,users.id_user, users.name, users.secondname,users.lastname,"
			+ "schools.idschool,specialties.idspecialty, specialties.specialty_name,faculties.idfaculty, faculties.faculty_name,"
			+ "types_study.idtype_study, types_study.type_name"
			+ " from applications inner join stud_privileges on applications.privileges_idprivilege = stud_privileges.idprivilege "
			+ "inner join users on applications.users_id_user = users.id_user "
			+ "inner join schools on applications.schools_idschool=schools.idschool "
			+ "inner join specialties on applications.specialties_idspecialty = specialties.idspecialty "
			+ "inner join types_study on specialties.idtype_study = types_study.idtype_study "
			+ "inner join faculties on specialties.faculties_idfaculty = faculties.idfaculty "
			+ "where applications.specialties_idspecialty = ?";
	
	private static final String SELECT_FREE_PLACES = "select (s.plan-"
			+ "(select count(*) from applications a inner join results r on a.idapplication=r.applications_idapplication "
			+ "where a.specialties_idspecialty = ? and r.is_accepted = true)) as diff "
			+ "from specialties s where s.idspecialty = ?";
	
	private static final String INSERT_RESULTS = "insert into results (total_score,is_accepted,applications_idapplication) value (?,?,?)";
	private static final String UPDATE_RESULTS = "update results set total_score = ?, is_accepted = ? where applications_idapplication = ?";
	private static final String SELECT_ACCEPT_STUDENT = "select is_accepted from results where applications_idapplication = ?";


	@Override
	public Application applicationByUserId(int userId) throws DAOException {
		Application application = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(SELECT_APPLICATION_BY_USERID);
			ps.setInt(1, userId);

			rs = ps.executeQuery();

			if (rs.next()) {
				application = new Application(rs.getInt(1), rs.getString(2), rs.getInt(3), new Privilege(rs.getInt(4)),
						new User(rs.getInt(5)), new School(rs.getInt(6)), new Specialty(rs.getInt(7)), rs.getBoolean(8),
						rs.getString(9), rs.getString(10), rs.getString(11), rs.getInt(12), rs.getString(13),
						rs.getDate(14));
			}

			return application;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	/** 
	 * Ñreating a new application and updating user data.
	 * @param application - ready object with valid data.
	 * @param user - ready object with valid data.
	 * 
	 * */
	@Override
	public void insertApplication(Application application, User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);

			ps = connection.prepareStatement(INSERT_APPLICATION);

			ps.setString(1, application.getAdress());
			ps.setInt(2, application.getCertificate());
			ps.setInt(3, application.getPrivilege().getId());
			ps.setInt(4, application.getUser().getId());
			ps.setInt(5, application.getSchool().getId());
			ps.setInt(6, application.getSpecialties().getId());
			ps.setBoolean(7, application.isConfirmation());
			ps.setString(8, application.getTypeDocument());
			ps.setString(9, application.getIdDocument());
			ps.setString(10, application.getSeriesPassport());
			ps.setInt(11, application.getNumberPassport());
			ps.setString(12, application.getIssuedBy());
			ps.setDate(13, application.getEndStudyDate());

			ps.executeUpdate();

			ps = connection.prepareStatement(UPDATE_USER_BY_ID);

			ps.setString(1, user.getName());
			ps.setString(2, user.getSecondName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getGender());
			ps.setString(6, user.getMaritalStatus());
			ps.setString(7, user.getPlaceOfBirth());
			ps.setDate(8, user.getDateOfBirth());
			ps.setInt(9, user.getId());

			ps.executeUpdate();
			

			connection.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException ex) {
				logger.log(Level.ERROR, "Rollback ApplicationDAOImpl method insertApplication", e);
				throw new DAOSQLException(ex);
			}
			
			throw new DAOSQLException(e);

		} finally {

			if (connection != null) {
				try {
					connection.setAutoCommit(true);
				} catch (SQLException e) {
					logger.log(Level.ERROR, "SQLException in ApplicationDAOImpl method insertApplication() in"
							+ "attempt to setAutoCommit(true)", e);
				}
			}
			closeConnection(connection, ps);
		}
	}

	/**
	 * Updating the existing statement and user data.
	 * @param application - ready object with valid data.
	 * @param user - ready object with valid data. 
	 */
	
	@Override
	public void updateApplication(Application application, User user) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			
			ps = connection.prepareStatement(UPDATE_APPLICATION);

			ps.setString(1, application.getAdress());
			ps.setInt(2, application.getCertificate());
			ps.setInt(3, application.getPrivilege().getId());
			ps.setInt(4, application.getUser().getId());
			ps.setInt(5, application.getSchool().getId());
			ps.setInt(6, application.getSpecialties().getId());
			ps.setBoolean(7, application.isConfirmation());
			ps.setString(8, application.getTypeDocument());
			ps.setString(9, application.getIdDocument());
			ps.setString(10, application.getSeriesPassport());
			ps.setInt(11, application.getNumberPassport());
			ps.setString(12, application.getIssuedBy());
			ps.setDate(13, application.getEndStudyDate());
			ps.setInt(14, application.getId());
			ps.executeUpdate();
			
			ps = connection.prepareStatement(UPDATE_USER_BY_ID);

			ps.setString(1, user.getName());
			ps.setString(2, user.getSecondName());
			ps.setString(3, user.getLastName());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getGender());
			ps.setString(6, user.getMaritalStatus());
			ps.setString(7, user.getPlaceOfBirth());
			ps.setDate(8, user.getDateOfBirth());
			ps.setInt(9, user.getId());

			ps.executeUpdate();
			

			connection.commit();

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {

			try {
				connection.rollback();
			} catch (SQLException ex) {
				logger.log(Level.ERROR, "Rollback ApplicationDAOImpl method updateApplication", e);
				throw new DAOSQLException(ex);
			}

			throw new DAOSQLException(e);

		} finally {

			if (connection != null) {
				try {
					connection.setAutoCommit(true);
				} catch (SQLException e) {
					logger.log(Level.ERROR, "SQLException in ApplicationDAOImpl method updateApplication() in"
							+ "attempt to setAutoCommit(true)", e);
				}
			}
			closeConnection(connection, ps);
		}
	}

	
	/**
	 * Receives all inactive applications
	 */
	@Override
	public List<Application> getAllUnconfirmedApplication() throws DAOException {
		List<Application> applications = new ArrayList<Application>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			st = connection.createStatement();

			rs = st.executeQuery(SELECT_APPLICATION_BY_UNCONFIRMED);

			while (rs.next()) {
				applications.add(buildApplication(rs));
			}

			return applications;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	/**
	 * Receives all active applications
	 */
	@Override
	public List<Application> getAllConfirmedApplication() throws DAOException {
		List<Application> applications = new ArrayList<Application>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			st = connection.createStatement();

			rs = st.executeQuery(SELECT_APPLICATION_BY_CONFIRMED);

			while (rs.next()) {
				applications.add(buildApplication(rs));
			}

			return applications;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	/**
	 * Receives all applications
	 */
	@Override
	public List<Application> getAllApplication() throws DAOException {
		List<Application> applications = new ArrayList<Application>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			st = connection.createStatement();

			rs = st.executeQuery(SELECT_APPLICATION);

			while (rs.next()) {
				applications.add(buildApplication(rs));
			}

			return applications;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
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

	private Application buildApplication(ResultSet rs) throws SQLException {
		Application app = null;

		int id = rs.getInt(1);
		String adress = rs.getString(2);
		int certificate = rs.getInt(3);
		boolean confirmation = rs.getBoolean(4);
		String typeDocument = rs.getString(5);
		String idDocument = rs.getString(6);
		String seriesPassport = rs.getString(7);
		int numberPassport = rs.getInt(8);
		String issuedBy = rs.getString(9);
		Date endStudyDate = rs.getDate(10);
		Privilege privilege = new Privilege(rs.getInt(11), rs.getString(12));
		User user = new User(rs.getInt(13), rs.getString(14), rs.getString(15), rs.getString(16));
		School school = new School(rs.getInt(17));
		TypeStudy typeStudy = new TypeStudy(rs.getInt(22), rs.getString(23));
		Faculty faculty = new Faculty(rs.getInt(20), rs.getString(21));
		Specialty specialties = new Specialty(rs.getInt(18), rs.getString(19), typeStudy, faculty);

		app = new Application(id, adress, certificate, privilege, user, school, specialties, confirmation, typeDocument,
				idDocument, seriesPassport, numberPassport, issuedBy, endStudyDate);

		return app;
	}

	@Override
	public boolean confirmApplication(int idApplication) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(CONFIRM_APPLICATION);

			ps.setBoolean(1, true);
			ps.setInt(2, idApplication);

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
	public boolean addMark(int idApplication, int mark, int idSubject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSER_MARK);

			ps.setInt(1, idApplication);
			ps.setInt(2, idSubject);
			ps.setInt(3, mark);

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
	public List<ExamMark> getAllMarksByApplication(int idApplication) throws DAOException {
		List<ExamMark> exams = new ArrayList<ExamMark>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_EXAMS);

			ps.setInt(1, idApplication);
			rs = ps.executeQuery();

			while (rs.next()) {
				exams.add(new ExamMark(rs.getInt(1), rs.getInt(2)));
			}

			return exams;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public boolean updateMark(int idApplication, int mark, int idSubject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_MARK);

			ps.setInt(1, mark);
			ps.setInt(2, idApplication);
			ps.setInt(3, idSubject);

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
	public boolean getConfirmation(int idApplication) throws DAOException {
		boolean confirm = false;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SELECT_CONFIRMATION);

			ps.setInt(1, idApplication);
			rs = ps.executeQuery();

			if (rs.next()) {
				confirm = rs.getBoolean(1);
			}

			return confirm;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public List<Application> getAllAplicationBySpecialty(int idSpecialty) throws DAOException {
		List<Application> applications = new ArrayList<Application>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(SELECT_APPLICATION_BY_SPECIALTYID);
			ps.setInt(1, idSpecialty);

			rs = ps.executeQuery();

			while (rs.next()) {
				applications.add(buildApplication(rs));
			}

			return applications;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	/**
	 * Receives the number of consolidated places per specialty.
	 * @param idSpecialty - ID of the specialty for which you need to get the number of free places
	 */
	@Override
	public int getNumberOfFreePlaces(int idSpecialty) throws DAOException {
		int countPlaces = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(SELECT_FREE_PLACES);
			ps.setInt(1, idSpecialty);

			rs = ps.executeQuery();

			if (rs.next()) {
				countPlaces = rs.getInt(1);
			}

			return countPlaces;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public boolean acceptStudent(int totalScore, boolean isAccepted, int idApplication) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_RESULTS);

			ps.setInt(1, totalScore);
			ps.setBoolean(2, isAccepted);
			ps.setInt(3, idApplication);

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
	public boolean getAcceptStudent(int idApplication) throws DAOException {
		boolean isAccepted = false;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(SELECT_ACCEPT_STUDENT);
			ps.setInt(1, idApplication);

			rs = ps.executeQuery();

			if (rs.next()) {
				isAccepted = rs.getBoolean(1);
			}

			return isAccepted;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public void createResult(int idApplication) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_RESULTS);

			ps.setInt(1, 0);
			ps.setBoolean(2, false);
			ps.setInt(3, idApplication);
			ps.executeUpdate();

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
	}

	@Override
	public Application applicationById(int id) throws DAOException {
		Application application = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();

			ps = connection.prepareStatement(SELECT_APPLICATION_BY_ID);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				application = buildApplication(rs);
			}

			return application;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

}
