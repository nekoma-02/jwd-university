package by.epam.university.service.impl;

import java.util.List;

import by.epam.university.dao.DAOFactory;
import by.epam.university.dao.SQLApplicationDao;
import by.epam.university.dao.SQLPrivilegeDao;
import by.epam.university.dao.SQLSchoolDao;
import by.epam.university.dao.SQLSpecialtyDao;
import by.epam.university.dao.exception.DAOException;
import by.epam.university.entity.Application;
import by.epam.university.entity.ExamMark;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.Subject;
import by.epam.university.entity.TypeStudy;
import by.epam.university.service.AdminService;
import by.epam.university.service.exception.FacultyExistsException;
import by.epam.university.service.exception.PrivilegeExistsException;
import by.epam.university.service.exception.SchoolExistsException;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.SpecialtyExistsException;
import by.epam.university.service.exception.SubjectExistsException;
import by.epam.university.service.exception.TypeStudyExistsException;
import by.epam.university.service.validator.FacultyValidator;
import by.epam.university.service.validator.PrivilegeValidator;
import by.epam.university.service.validator.SchoolValidator;
import by.epam.university.service.validator.SpecialtyValidator;
import by.epam.university.service.validator.SubjectValidator;
import by.epam.university.service.validator.TypeStudyValidator;
import by.epam.university.service.validator.factory.ValidatorFactory;

public class AdminServiceImpl implements AdminService {

	@Override
	public List<Application> getAllUnconfirmedApplication() throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAllUnconfirmedApplication();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Application> getAllConfirmedApplication() throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAllConfirmedApplication();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Application> getAllApplication() throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAllApplication();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertFaculty(Faculty faculty) throws ServiceException, FacultyExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		FacultyValidator validator = ValidatorFactory.getInstance().getFacultyValidator();

		try {
			Faculty temp = dao.getFacultyById(faculty.getId());
			if (faculty.equals(temp)) {
				throw new FacultyExistsException("that faculty already exists!");
			}

			boolean isValid = validator.validateFaculty(faculty);
			if (isValid) {
				return false;
			}

			return dao.insertFaculty(faculty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertTypeStudy(TypeStudy typeStudy) throws ServiceException, TypeStudyExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();
		TypeStudyValidator validator = ValidatorFactory.getInstance().getTypeStudyValidator();

		try {
			TypeStudy temp = dao.getTypeStudyById(typeStudy.getId());
			if (typeStudy.equals(temp)) {
				throw new TypeStudyExistsException("that type study already exists!");
			}

			boolean isValid = validator.validateTypeStudy(typeStudy);
			if (isValid) {
				return false;
			}
			return dao.insertTypeStudy(typeStudy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertSchool(School school) throws ServiceException, SchoolExistsException {
		SQLSchoolDao dao = DAOFactory.getInstance().getSchoolDAO();

		SchoolValidator validator = ValidatorFactory.getInstance().getSchoolValidator();

		try {
			School temp = dao.getById(school.getId());
			if (school.equals(temp)) {
				throw new SchoolExistsException("that school already exists!");
			}

			boolean isValid = validator.validateSchool(school);
			if (isValid) {
				return false;
			}
			return dao.insert(school);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertPrivilege(Privilege privilege) throws ServiceException, PrivilegeExistsException {
		SQLPrivilegeDao dao = DAOFactory.getInstance().getPrivilegeDAO();
		PrivilegeValidator validator = ValidatorFactory.getInstance().getPrivilegeValidator();

		try {

			Privilege temp = dao.getById(privilege.getId());
			if (privilege.equals(temp)) {
				throw new PrivilegeExistsException("that privilege already exists!");
			}

			boolean isValid = validator.validatePrivilege(privilege);
			if (isValid) {
				return false;
			}
			return dao.insert(privilege);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateFaculty(Faculty faculty) throws ServiceException, FacultyExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		FacultyValidator validator = ValidatorFactory.getInstance().getFacultyValidator();

		try {
			Faculty temp = dao.getFacultyById(faculty.getId());
			if (faculty.equals(temp)) {
				throw new FacultyExistsException("that faculty already exists!");
			}

			boolean isValid = validator.validateFaculty(faculty);
			if (isValid) {
				return false;
			}
			return dao.updateFaculty(faculty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateTypeStudy(TypeStudy typeStudy) throws ServiceException, TypeStudyExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		TypeStudyValidator validator = ValidatorFactory.getInstance().getTypeStudyValidator();

		try {
			TypeStudy temp = dao.getTypeStudyById(typeStudy.getId());
			if (typeStudy.equals(temp)) {
				throw new TypeStudyExistsException("that type study already exists!");
			}

			boolean isValid = validator.validateTypeStudy(typeStudy);
			if (isValid) {
				return false;
			}
			return dao.updateTypeStudy(typeStudy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateSchool(School school) throws ServiceException, SchoolExistsException {
		SQLSchoolDao dao = DAOFactory.getInstance().getSchoolDAO();

		SchoolValidator validator = ValidatorFactory.getInstance().getSchoolValidator();

		try {
			School temp = dao.getById(school.getId());
			if (school.equals(temp)) {
				throw new SchoolExistsException("that school already exists!");
			}

			boolean isValid = validator.validateSchool(school);
			if (isValid) {
				return false;
			}
			return dao.update(school);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updatePrivilege(Privilege privilege) throws ServiceException, PrivilegeExistsException {
		SQLPrivilegeDao dao = DAOFactory.getInstance().getPrivilegeDAO();
		PrivilegeValidator validator = ValidatorFactory.getInstance().getPrivilegeValidator();

		try {
			Privilege temp = dao.getById(privilege.getId());
			if (privilege.equals(temp)) {
				throw new PrivilegeExistsException("that privilege already exists!");
			}

			boolean isValid = validator.validatePrivilege(privilege);
			if (isValid) {
				return false;
			}
			return dao.update(privilege);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Faculty getFacultyById(int id) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.getFacultyById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public TypeStudy getTypeStudyById(int id) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.getTypeStudyById(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertSpecialty(Specialty specialty) throws ServiceException, SpecialtyExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		SpecialtyValidator validator = ValidatorFactory.getInstance().getSpecialtyValidator();

		try {
			Specialty temp = dao.getSpecialtyById(specialty.getId());
			if (specialty.equals(temp)) {
				throw new SpecialtyExistsException("that specialty already exists!");
			}

			boolean isValid = validator.validateSpecialty(specialty);
			if (isValid) {
				return false;
			}
			return dao.insert(specialty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateSpecialty(Specialty specialty) throws ServiceException, SpecialtyExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();
		SpecialtyValidator validator = ValidatorFactory.getInstance().getSpecialtyValidator();

		try {

			Specialty temp = dao.getSpecialtyById(specialty.getId());
			if (specialty.equals(temp)) {
				throw new SpecialtyExistsException("that specialty already exists!");
			}

			boolean isValid = validator.validateSpecialty(specialty);
			if (isValid) {
				return false;
			}
			return dao.updateSpecialty(specialty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Subject> getSubjectBySpecialtyId(int id) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.subjectBySpecialtyID(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean confirmApplication(int idApplication) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.confirmApplication(idApplication);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean addMark(int idApplication, int mark, int idSubject) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			if (mark < 0 || mark > 10) {
				return false;
			}
			return dao.addMark(idApplication, mark, idSubject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public int getIdbySubjectAndSpecialty(int idSubject, int idSpecialty) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.getIdbySubjectAndSpecialty(idSubject, idSpecialty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<ExamMark> getAllMarksByApplication(int idApplication) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAllMarksByApplication(idApplication);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateMark(int idApplication, int mark, int idSubject) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			if (mark < 0 || mark > 10) {
				return false;
			}
			return dao.updateMark(idApplication, mark, idSubject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Subject> getAllSubject() throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.getAllSubject();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateSubject(Subject subject) throws ServiceException, SubjectExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();
		SubjectValidator validator = ValidatorFactory.getInstance().getSubjectValidator();
		try {

			boolean isValid = validator.validateSubject(subject);
			if (isValid) {
				return false;
			}

			Subject temp = dao.getSubjectById(subject.getId());
			if (subject.equals(temp)) {
				throw new SubjectExistsException("such subject already exists");
			}

			return dao.updateSubject(subject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addSubject(Subject subject) throws ServiceException, SubjectExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		SubjectValidator validator = ValidatorFactory.getInstance().getSubjectValidator();
		try {

			boolean isValid = validator.validateSubject(subject);
			if (isValid) {
				return false;
			}

			Subject temp = dao.getSubjectById(subject.getId());
			if (subject.equals(temp)) {
				throw new SubjectExistsException("such subject already exists");
			}

			return dao.addSubject(subject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean addSubjectBySpecialty(int idSpecialty, int idSubject) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {

			return dao.addSubjectBySpecialty(idSpecialty, idSubject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateSubjectBySpecialty(int idSpecialty, int idSubject) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.updateSubjectBySpecialty(idSpecialty, idSubject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Specialty getSpecialtyByName(String name) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.getSpecialtyByName(name);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public Subject getSubjectById(int idSubject) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.getSubjectById(idSubject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removeSubjectBySpecialty(int idSpecialty, int idSubject) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.removeSubjectBySpecialty(idSpecialty, idSubject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean insertSpecialtyAndSubject(Specialty specialty, int... idSubject)
			throws ServiceException, SpecialtyExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		SpecialtyValidator validator = ValidatorFactory.getInstance().getSpecialtyValidator();

		try {
			Specialty temp = dao.getSpecialtyById(specialty.getId());
			if (specialty.equals(temp)) {
				throw new SpecialtyExistsException("that specialty already exists!");
			}

			boolean isValid = validator.validateSpecialty(specialty);
			if (isValid) {
				return false;
			}

			dao.insertSpecialtyAndSubject(specialty, idSubject);

			return true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean updateSpecialtyAndSubject(Specialty specialty, int... idSubject)
			throws ServiceException, SpecialtyExistsException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		SpecialtyValidator validator = ValidatorFactory.getInstance().getSpecialtyValidator();

		try {
			Specialty temp = dao.getSpecialtyById(specialty.getId());
			if (specialty.equals(temp)) {
				throw new SpecialtyExistsException("that specialty already exists!");
			}

			boolean isValid = validator.validateSpecialty(specialty);
			if (isValid) {
				return false;
			}

			dao.updateSpecialtyAndSubject(specialty, idSubject);

			return true;
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<Application> getAllAplicationBySpecialty(int idSpecialty) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAllAplicationBySpecialty(idSpecialty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean acceptStudent(int totalScore, boolean isAccepted, int idApplication) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.acceptStudent(totalScore, isAccepted, idApplication);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean getAcceptStudent(int idApplication) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			return dao.getAcceptStudent(idApplication);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void createResult(int idApplication) throws ServiceException {
		SQLApplicationDao dao = DAOFactory.getInstance().getApplicationDAO();

		try {
			dao.createResult(idApplication);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public boolean removeSpecialty(int idSpecialty) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.remove(idSpecialty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removeSchool(int idSchool) throws ServiceException {
		SQLSchoolDao dao = DAOFactory.getInstance().getSchoolDAO();
		
		try {
			return dao.remove(idSchool);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removePrivilege(int idPrivilege) throws ServiceException {
		SQLPrivilegeDao dao = DAOFactory.getInstance().getPrivilegeDAO();
		
		try {
			return dao.remove(idPrivilege);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removeFaculty(int idFaculty) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.removeFaculty(idFaculty);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removeTypeStudy(int idTypeStudy) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.removeTypeStudy(idTypeStudy);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean removeSubject(int idSubject) throws ServiceException {
		SQLSpecialtyDao dao = DAOFactory.getInstance().getSpecialtyDAO();

		try {
			return dao.removeSubject(idSubject);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	}

}
