package by.epam.university.service;

import java.util.List;

import by.epam.university.entity.Application;
import by.epam.university.entity.ExamMark;
import by.epam.university.entity.Faculty;
import by.epam.university.entity.Privilege;
import by.epam.university.entity.School;
import by.epam.university.entity.Specialty;
import by.epam.university.entity.Subject;
import by.epam.university.entity.TypeStudy;
import by.epam.university.service.exception.FacultyExistsException;
import by.epam.university.service.exception.PrivilegeExistsException;
import by.epam.university.service.exception.SchoolExistsException;
import by.epam.university.service.exception.ServiceException;
import by.epam.university.service.exception.SpecialtyExistsException;
import by.epam.university.service.exception.SubjectExistsException;
import by.epam.university.service.exception.TypeStudyExistsException;

public interface AdminService {

	List<Application> getAllUnconfirmedApplication() throws ServiceException;

	List<Application> getAllConfirmedApplication() throws ServiceException;

	List<Application> getAllApplication() throws ServiceException;

	List<Subject> getSubjectBySpecialtyId(int id) throws ServiceException;

	List<ExamMark> getAllMarksByApplication(int idApplication) throws ServiceException;

	List<Subject> getAllSubject() throws ServiceException;

	List<Application> getAllAplicationBySpecialty(int idSpecialty) throws ServiceException;

	boolean insertFaculty(Faculty faculty) throws ServiceException, FacultyExistsException;

	boolean insertTypeStudy(TypeStudy typeStudy) throws ServiceException, TypeStudyExistsException;

	boolean insertSchool(School school) throws ServiceException, SchoolExistsException;

	boolean insertPrivilege(Privilege privilege) throws ServiceException, PrivilegeExistsException;

	boolean insertSpecialty(Specialty specialty) throws ServiceException, SpecialtyExistsException;

	boolean updateFaculty(Faculty faculty) throws ServiceException, FacultyExistsException;

	boolean updateTypeStudy(TypeStudy typeStudy) throws ServiceException, TypeStudyExistsException;

	boolean updateSchool(School school) throws ServiceException, SchoolExistsException;

	boolean updatePrivilege(Privilege privilege) throws ServiceException, PrivilegeExistsException;

	boolean updateSpecialty(Specialty specialty) throws ServiceException, SpecialtyExistsException;

	boolean confirmApplication(int idApplication) throws ServiceException;

	boolean addMark(int idApplication, int mark, int idSubject) throws ServiceException;

	boolean updateMark(int idApplication, int mark, int idSubject) throws ServiceException;

	boolean updateSubject(Subject subject) throws ServiceException, SubjectExistsException;

	boolean addSubject(Subject subject) throws ServiceException, SubjectExistsException;

	boolean addSubjectBySpecialty(int idSpecialty, int idSubject) throws ServiceException;

	boolean updateSubjectBySpecialty(int idSpecialty, int idSubject) throws ServiceException;

	boolean removeSubjectBySpecialty(int idSpecialty, int idSubject) throws ServiceException;

	boolean insertSpecialtyAndSubject(Specialty specialty, int... idSubject)
			throws ServiceException, SpecialtyExistsException;

	boolean updateSpecialtyAndSubject(Specialty specialty, int... idSubject)
			throws ServiceException, SpecialtyExistsException;

	boolean acceptStudent(int totalScore, boolean isAccepted, int idApplication) throws ServiceException;

	boolean getAcceptStudent(int idApplication) throws ServiceException;

	boolean removeSpecialty(int idSpecialty) throws ServiceException;

	boolean removeSchool(int idSchool) throws ServiceException;

	boolean removePrivilege(int idPrivilege) throws ServiceException;

	boolean removeFaculty(int idFaculty) throws ServiceException;

	boolean removeTypeStudy(int idTypeStudy) throws ServiceException;
	
	boolean removeSubject(int idSubject) throws ServiceException;

	void createResult(int idApplication) throws ServiceException;

	Faculty getFacultyById(int id) throws ServiceException;

	TypeStudy getTypeStudyById(int id) throws ServiceException;

	Specialty getSpecialtyByName(String name) throws ServiceException;

	Subject getSubjectById(int idSubject) throws ServiceException;

	int getIdbySubjectAndSpecialty(int idSubject, int idSpecialty) throws ServiceException;

}
