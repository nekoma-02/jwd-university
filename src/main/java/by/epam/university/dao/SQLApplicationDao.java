package by.epam.university.dao;

import java.util.List;

import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Application;
import by.epam.university.entity.ExamMark;
import by.epam.university.entity.User;

public interface SQLApplicationDao {

	Application applicationByUserId(int userId) throws DAOException;
	
	Application applicationById(int id) throws DAOException;

	void insertApplication(Application application, User user) throws DAOException;

	void updateApplication(Application application, User user) throws DAOException;
	
	List<Application> getAllUnconfirmedApplication() throws DAOException;
	
	List<Application> getAllConfirmedApplication() throws DAOException;
	
	List<Application> getAllApplication() throws DAOException;
	
	List<Application> getAllAplicationBySpecialty(int idSpecialty) throws DAOException;
	
	List<ExamMark> getAllMarksByApplication(int idApplication) throws DAOException;
	
	boolean confirmApplication(int idApplication) throws DAOException;
	
	boolean addMark(int idApplication, int mark, int idSubject) throws DAOException;
	
	boolean updateMark(int idApplication, int mark, int idSubject) throws DAOException;
	
	boolean getConfirmation(int idApplication) throws DAOException;
	
	int getNumberOfFreePlaces(int idSpecialty) throws DAOException;
	
	boolean acceptStudent(int totalScore, boolean isAccepted, int idApplication) throws DAOException;
	
	boolean getAcceptStudent(int idApplication) throws DAOException;
	
	void createResult(int idApplication) throws DAOException;
	
}