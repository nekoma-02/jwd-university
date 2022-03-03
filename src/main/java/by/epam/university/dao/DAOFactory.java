package by.epam.university.dao;

import by.epam.university.dao.impl.ApplicationDAOImpl;
import by.epam.university.dao.impl.PrivilegeDAOImpl;
import by.epam.university.dao.impl.SchoolDAOImpl;
import by.epam.university.dao.impl.SpecialtyDAOImpl;
import by.epam.university.dao.impl.UserDAOImpl;

public class DAOFactory {

	private static volatile DAOFactory instance;

	public static DAOFactory getInstance() {
		DAOFactory localInstance = instance;
		if (localInstance == null) {
			synchronized (DAOFactory.class) {
				if (localInstance == null) {
					instance = localInstance = new DAOFactory();
				}
			}
		}
		return localInstance;
	}
	
	public SQLUserDao getUserDAO() {
		return new UserDAOImpl();
	}
	
	public SQLSpecialtyDao getSpecialtyDAO() {
		return new SpecialtyDAOImpl();
	}
	
	public SQLApplicationDao getApplicationDAO() {
		return new ApplicationDAOImpl();
	}
	
	public SQLSchoolDao getSchoolDAO() {
		return new SchoolDAOImpl();
	}
	
	public SQLPrivilegeDao getPrivilegeDAO() {
		return new PrivilegeDAOImpl();
	}
	

}
