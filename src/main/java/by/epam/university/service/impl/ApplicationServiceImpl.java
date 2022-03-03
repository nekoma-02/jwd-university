package by.epam.university.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SQLApplicationDao;
import by.epam.university.dao.SQLPrivilegeDao;
import by.epam.university.dao.SQLSchoolDao;
import by.epam.university.dao.SQLSpecialtyDao;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Application;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.TypeStudy;
import by.epam.university.entity.User;
import by.epam.university.service.ApplicationService;
import by.epam.university.service.exception.ApplicationExistsException;
import by.epam.university.service.exception.ServiceException;

public class ApplicationServiceImpl implements ApplicationService {

	@Override
	public Application ApplicationByUserId(int id) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();
		
		try {
			Application app = dao.applicationByUserId(id);
			return app;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean createApplication(Application application, User user) throws ServiceException, ApplicationExistsException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();
		
		try {
			
			if (!isExistUserApplication(user.getId())) {
				throw new ApplicationExistsException("that application already exists!");
			}
			
			dao.insertApplication(application, user);
			return true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	public List<Specialty> getAllSpecialties() throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();
		
		List<Specialty> specialties = new ArrayList<Specialty>();
		try {
			specialties = dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return specialties;
		
	}

	@Override
	public List<School> getAllSchools() throws ServiceException {
		SQLSchoolDao dao = DAOFactory.getInstance().getSchoolDAO();
		
		List<School> schools = new ArrayList<School>();
		try {
			schools = dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return schools;
		
	}

	@Override
	public List<Privilege> getAllPrivileges() throws ServiceException {
		SQLPrivilegeDao dao = DAOFactory.getInstance().getPrivilegeDAO();
		
		List<Privilege> privileges = new ArrayList<Privilege>();
		try {
			privileges = dao.getAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return privileges;
	}
	
	@Override
	public boolean isExistUserApplication(int userId) throws ServiceException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		Application app = null;
		try {
			app = appDao.applicationByUserId(userId);
			return app == null ? true : false;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public boolean updateApplication(Application application, User user) throws ServiceException {
		SQLApplicationDao appDao = DAOFactory.getInstance().getApplicationDAO();
		
		try {
			appDao.updateApplication(application,user);
			return true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public School getSchoolById(int id) throws ServiceException {
		SQLSchoolDao schoolDao = DAOFactory.getInstance().getSchoolDAO();
		try {
			return schoolDao.getById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Privilege getPrivilegeById(int id) throws ServiceException {
		SQLPrivilegeDao privDao = DAOFactory.getInstance().getPrivilegeDAO();
		try {
			return privDao.getById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Specialty getSpecialtyById(int id) throws ServiceException {
		SQLSpecialtyDao specDao = DAOFactory.getInstance().getSpecialtyDAO();
		try {
			return specDao.getSpecialtyById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Specialty> getSpecialtyByTypeStudyAndFaculty(int idTypeStudy, int idFaculty) throws ServiceException {
		SQLSpecialtyDao specDao = DAOFactory.getInstance().getSpecialtyDAO();
		try {
			return specDao.getSpecialty(idTypeStudy, idFaculty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Faculty> getAllFaculties() throws ServiceException {
		SQLSpecialtyDao specDao = DAOFactory.getInstance().getSpecialtyDAO();
		try {
			return specDao.getAllFaculty();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<TypeStudy> getAllTypesStudy() throws ServiceException {
		SQLSpecialtyDao specDao = DAOFactory.getInstance().getSpecialtyDAO();
		try {
			return specDao.getAllTypeStudy();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean getConfirmation(int idApplication) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();
		try {
			return dao.getConfirmation(idApplication);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Application applicationById(int id) throws ServiceException {
SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();
		
		try {
			Application app = dao.applicationById(id);
			return app;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}
}
