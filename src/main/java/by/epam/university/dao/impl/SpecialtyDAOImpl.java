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

import by.epam.university.dao.SQLSpecialtyDao;
import by.epam.university.dao.connectionpool.ConnectionPool;
import by.epam.university.dao.connectionpool.ConnectionPoolException;
import by.epam.university.dao.connectionpool.ConnectionPoolManager;
import by.epam.university.dao.exception.DAOConnectionPoolException;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.dao.exception.DAOSQLException;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.Subject;
import by.epam.university.entity.TypeStudy;

public class SpecialtyDAOImpl implements SQLSpecialtyDao {

	private static Logger logger = LogManager.getLogger();
	private ConnectionPool connectionPool = ConnectionPoolManager.getInstance().getConnectionPool();

	private static final String SELECT_ALL_SPECIALTY = "select specialties.idspecialty,specialties.specialty_name,specialties.plan,"
			+ "specialties.year,types_study.type_name,faculties.faculty_name,types_study.idtype_study,faculties.idfaculty "
			+ "from specialties inner join types_study on specialties.idtype_study = types_study.idtype_study "
			+ "inner join faculties on faculties.idfaculty=specialties.faculties_idfaculty";
	
	private static final String SELECT_SPECIALTY_BY_ID = "select specialties.idspecialty,specialties.specialty_name,"
			+ "specialties.plan,specialties.year,types_study.type_name,faculties.faculty_name,types_study.idtype_study,"
			+ "faculties.idfaculty from specialties "
			+ "inner join types_study on specialties.idtype_study = types_study.idtype_study "
			+ "inner join faculties on faculties.idfaculty=specialties.faculties_idfaculty"
			+ " where specialties.idspecialty = ?";
	
	private static final String SELECT_SPECIALTY_BY_NAME = "select specialties.idspecialty,specialties.specialty_name,specialties.plan,"
			+ "specialties.year,types_study.type_name,faculties.faculty_name,types_study.idtype_study,faculties.idfaculty "
			+ "from specialties inner join types_study on specialties.idtype_study = types_study.idtype_study "
			+ "inner join faculties on faculties.idfaculty=specialties.faculties_idfaculty "
			+ "where specialties.specialty_name = ?";
	
	private static final String INSERT_SPECIALTY = "insert into specialties(specialty_name,plan,year,idtype_study,faculties_idfaculty) "
			+ "values (?,?,?,?,?)";
	
	private static final String SELECT_SPECIALTY_BY_TYPESTUDY_FACULTY = "select specialties.idspecialty,specialties.specialty_name,"
			+ "specialties.plan,specialties.year,types_study.type_name,faculties.faculty_name,types_study.idtype_study,"
			+ "faculties.idfaculty from specialties inner join types_study on specialties.idtype_study = types_study.idtype_study "
			+ "inner join faculties on faculties.idfaculty=specialties.faculties_idfaculty "
			+ "where types_study.idtype_study = ? and faculties.idfaculty = ? ";
	
	private static final String SELECT_ALL_FACULTY = "select idfaculty,faculty_name from faculties";
	private static final String INSERT_FACULTY = "insert into faculties(faculty_name) values (?)";
	private static final String UPDATE_FACULTY = "update faculties set faculty_name = ? where idfaculty = ?";
	private static final String INSERT_TYPE_STUDY = "insert into types_study(type_name) values (?)";
	private static final String UPDATE_TYPE_SYUDY = "update types_study set type_name = ? where idtype_study = ?";
	private static final String SELECT_ALL_TYPE_STUDY = "select * from types_study";
	private static final String SELECT_FACULTY_BY_ID = "select * from faculties where idfaculty = ?";
	private static final String SELECT_TYPE_STUDY_BY_ID = "select * from types_study where idtype_study = ?";
	
	private static final String UPDATE_SPECIALTY = "update specialties set specialty_name = ?,plan = ?,year = ?,idtype_study = ?,"
			+ "faculties_idfaculty = ? where idspecialty = ?";
	
	private static final String SUBJECT_BY_SPECIALTY_ID = "select s.idsubject, s.subject_name from specialties_has_subjects shs "
			+ "inner join subjects s on shs.subjects_idsubject = s.idsubject where specialties_idspecialty = ?";
	
	private static final String SELECT_ID_SPEC_HAS_SUBJ = "select id from specialties_has_subjects where subjects_idsubject = ? "
			+ "and specialties_idspecialty = ?";
	
	private static final String SELECT_ALL_SUBJECT = "select * from subjects";
	private static final String UPDATE_SUBJECT = "update subjects set subject_name = ? where idsubject = ?";
	private static final String INSERT_SUBJECT = "insert into subjects(subject_name) value (?)";
	
	private static final String INSERT_SUBJECT_BY_SPECIALTY = "insert into specialties_has_subjects(subjects_idsubject,"
			+ "specialties_idspecialty) value (?,?)";
	
	private static final String UPDATE_SUBJECT_BY_SPECIALTY = "update specialties_has_subjects set subjects_idsubject = ? "
			+ "where specialties_idspecialty = ?";
	
	private static final String SELECT_SUBJECT_BY_ID = "select * from subjects where idsubject = ?";
	
	private static final String REMOVE_SUBJECT_BY_SPECIALTY = "delete from specialties_has_subjects where subjects_idsubject = ? "
			+ "and specialties_idspecialty = ?";
	
	private static final String REMOVE_SPECIALTY = "delete from specialties where idspecialty = ?";
	private static final String REMOVE_SUBJECT = "delete from subjects where idsubject = ?";
	private static final String REMOVE_TYPE_STUDY = "delete from types_study where idtype_study = ?";
	private static final String REMOVE_FACULTY = "delete from faculties where idfaculty = ?";
	
	
	
	@Override
	public boolean insert(Specialty specialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_SPECIALTY);

			ps.setString(1, specialty.getName());
			ps.setInt(2, specialty.getPlan());
			ps.setInt(3, specialty.getYear());
			ps.setInt(4, specialty.getTypeStudy().getId());
			ps.setInt(5, specialty.getFaculty().getId());

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
	public Specialty getSpecialtyByName(String name) throws DAOException {
		Specialty specialty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SPECIALTY_BY_NAME);
			ps.setString(1,name);

			rs = ps.executeQuery();

			if (rs.next()) {
				specialty = buildSpecialty(rs);
			}

			return specialty;

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
	public Specialty getSpecialtyById(int id) throws DAOException {
		Specialty specialty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SPECIALTY_BY_ID);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				specialty = buildSpecialty(rs);
			}

			return specialty;

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
	public List<Specialty> getAll() throws DAOException {
		List<Specialty> specialties = new ArrayList<Specialty>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_SPECIALTY);

			while (rs.next()) {
				specialties.add(buildSpecialty(rs));
			}

			return specialties;

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
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
	
	
	private Specialty buildSpecialty(ResultSet rs) throws SQLException {
		Specialty spec = null;

		int id = rs.getInt(1);
		String name = rs.getString(2);
		int plan = rs.getInt(3);
		int year = rs.getInt(4);
		TypeStudy ts = new TypeStudy(rs.getInt(7),rs.getString(5));
		Faculty faculty = new Faculty(rs.getInt(8), rs.getString(6));
		
		spec = new Specialty(id, name, plan, year, ts, faculty);
		
		return spec;
		
	}

	@Override
	public List<Specialty> getSpecialty(int idTypeStudy, int idFaculty) throws DAOException {
		List<Specialty> specialty = new ArrayList<Specialty>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SPECIALTY_BY_TYPESTUDY_FACULTY);
			ps.setInt(1,idTypeStudy);
			ps.setInt(2,idFaculty);
			

			rs = ps.executeQuery();

			while (rs.next()) {
				specialty.add(buildSpecialty(rs));
			}

			return specialty;

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
	public List<Faculty> getAllFaculty() throws DAOException {
		List<Faculty> faculties = new ArrayList<Faculty>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_FACULTY);

			while (rs.next()) {
				faculties.add(new Faculty(rs.getInt(1), rs.getString(2)));
			}

			return faculties;

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
	public List<TypeStudy> getAllTypeStudy() throws DAOException {
		List<TypeStudy> typeStudies = new ArrayList<TypeStudy>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_TYPE_STUDY);

			while (rs.next()) {
				typeStudies.add(new TypeStudy(rs.getInt(1), rs.getString(2)));
			}

			return typeStudies;

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
	public boolean insertFaculty(Faculty faculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;


		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_FACULTY);

			ps.setString(1, faculty.getName());

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
	public boolean insertTypeStudy(TypeStudy typeStudy) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;


		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_TYPE_STUDY);

			ps.setString(1, typeStudy.getTypeName());

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
	public boolean updateFaculty(Faculty faculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_FACULTY);

			ps.setString(1, faculty.getName());
			ps.setInt(2, faculty.getId());
			

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
	public boolean updateTypeStudy(TypeStudy typeStudy) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_TYPE_SYUDY);

			ps.setString(1, typeStudy.getTypeName());
			ps.setInt(2, typeStudy.getId());
			

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
	public Faculty getFacultyById(int id) throws DAOException {
		Faculty faculty = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_FACULTY_BY_ID);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				faculty = new Faculty(rs.getInt(1), rs.getString(2));
			}

			return faculty;

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
	public TypeStudy getTypeStudyById(int id) throws DAOException {
		TypeStudy typeStudy = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_TYPE_STUDY_BY_ID);
			ps.setInt(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				typeStudy = new TypeStudy(rs.getInt(1), rs.getString(2));
			}

			return typeStudy;

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
	public boolean updateSpecialty(Specialty specialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_SPECIALTY);

			ps.setString(1, specialty.getName());
			ps.setInt(2, specialty.getPlan());
			ps.setInt(3, specialty.getYear());
			ps.setInt(4, specialty.getTypeStudy().getId());
			ps.setInt(5, specialty.getFaculty().getId());
			ps.setInt(6, specialty.getId());
			

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
	public List<Subject> subjectBySpecialtyID(int id) throws DAOException {
		List<Subject> subjects = new ArrayList<Subject>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(SUBJECT_BY_SPECIALTY_ID);
			ps.setInt(1, id);
			
			rs = ps.executeQuery();

			while (rs.next()) {
				subjects.add(new Subject(rs.getInt(1),rs.getString(2)));
			}

			return subjects;

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
	public int getIdbySubjectAndSpecialty(int idSubject, int idSpecialty) throws DAOException {
		int id = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_ID_SPEC_HAS_SUBJ);
			ps.setInt(1,idSubject);
			ps.setInt(2,idSpecialty);
			

			rs = ps.executeQuery();

			if (rs.next()) {
				id =rs.getInt(1);
			}

			return id;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public List<Subject> getAllSubject() throws DAOException {
		List<Subject> subjects = new ArrayList<Subject>();
		Connection connection = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			st = connection.createStatement();

			rs = st.executeQuery(SELECT_ALL_SUBJECT);

			while (rs.next()) {
				subjects.add(new Subject(rs.getInt(1), rs.getString(2)));
			}

			return subjects;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, st, rs);
		}
	}

	@Override
	public boolean updateSubject(Subject subject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_SUBJECT);

			ps.setString(1, subject.getName());
			ps.setInt(2, subject.getId());
			
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
	public boolean addSubject(Subject subject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_SUBJECT);

			ps.setString(1, subject.getName());
			
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
	public boolean addSubjectBySpecialty(int idSpecialty, int idSubject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(INSERT_SUBJECT_BY_SPECIALTY);

			ps.setInt(1, idSubject);
			ps.setInt(2, idSpecialty);
			
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
	public boolean updateSubjectBySpecialty(int idSpecialty, int idSubject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(UPDATE_SUBJECT_BY_SPECIALTY);

			ps.setInt(1, idSubject);
			ps.setInt(2, idSpecialty);
			
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
	public Subject getSubjectById(int idSubject) throws DAOException {
		Subject subject = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			connection = connectionPool.takeConnection();
			
			ps = connection.prepareStatement(SELECT_SUBJECT_BY_ID);
			ps.setInt(1, idSubject);

			rs = ps.executeQuery();

			if (rs.next()) {
				subject = new Subject(rs.getInt(1),rs.getString(2));
			}

			return subject;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps, rs);
		}
	}

	@Override
	public boolean removeSubjectBySpecialty(int idSpecialty, int idSubject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_SUBJECT_BY_SPECIALTY);

			ps.setInt(1, idSubject);
			ps.setInt(2, idSpecialty);
			
			return ps.executeUpdate() == 1;

		} catch (ConnectionPoolException e) {
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
	}

	/**
	 * Adding a specialty and a set of subjects tied to this specialty
	 * @param specialty - ready object with valid specialty data
	 * @param idSubject - set of subjects tied to this specialty
	 */
	@Override
	public void insertSpecialtyAndSubject(Specialty specialty, int... idSubject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(INSERT_SPECIALTY);

			ps.setString(1, specialty.getName());
			ps.setInt(2, specialty.getPlan());
			ps.setInt(3, specialty.getYear());
			ps.setInt(4, specialty.getTypeStudy().getId());
			ps.setInt(5, specialty.getFaculty().getId());

			ps.executeUpdate();
			
			

			for (int i : idSubject) {
				ps = connection.prepareStatement(INSERT_SUBJECT_BY_SPECIALTY);
				ps.setInt(1, i);
				ps.setInt(2, specialty.getId());
				ps.executeUpdate();
			}
			
			
			connection.commit();

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				logger.log(Level.ERROR, "Rollback SpecialtyDAOImpl method insertSpecialtyAndSubject", e);
				throw new DAOSQLException(ex);
			}

			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
		
	}

	/**
	 * Updating specialty and a set of subjects tied to this specialty
	 * @param specialty - ready object with valid specialty data
	 * @param idSubject - set of subjects tied to this specialty
	 */
	@Override
	public void updateSpecialtyAndSubject(Specialty specialty, int... idSubject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			ps = connection.prepareStatement(UPDATE_SPECIALTY);

			ps.setString(1, specialty.getName());
			ps.setInt(2, specialty.getPlan());
			ps.setInt(3, specialty.getYear());
			ps.setInt(4, specialty.getTypeStudy().getId());
			ps.setInt(5, specialty.getFaculty().getId());
			ps.setInt(6, specialty.getId());

			ps.executeUpdate();
			
			for (int i : idSubject) {
				ps = connection.prepareStatement(INSERT_SUBJECT_BY_SPECIALTY);
				ps.setInt(1, i);
				ps.setInt(2, specialty.getId());
				ps.executeUpdate();
			}
			
			connection.commit();

		} catch (ConnectionPoolException e) {
			logger.log(Level.ERROR, e);
			throw new DAOConnectionPoolException(e);
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				logger.log(Level.ERROR, "Rollback SpecialtyDAOImpl method updateSpecialtyAndSubject", e);
				throw new DAOSQLException(ex);
			}

			throw new DAOSQLException(e);
		} finally {
			closeConnection(connection, ps);
		}
		
	}

	@Override
	public boolean remove(int idSpecialty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_SPECIALTY);

			ps.setInt(1, idSpecialty);
			
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
	public boolean removeSubject(int idSubject) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_SUBJECT);

			ps.setInt(1, idSubject);
			
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
	public boolean removeTypeStudy(int idTypeStudy) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_TYPE_STUDY);

			ps.setInt(1, idTypeStudy);
			
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
	public boolean removeFaculty(int idFaculty) throws DAOException {
		Connection connection = null;
		PreparedStatement ps = null;

		try {
			connection = connectionPool.takeConnection();
			ps = connection.prepareStatement(REMOVE_FACULTY);

			ps.setInt(1, idFaculty);
			
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
