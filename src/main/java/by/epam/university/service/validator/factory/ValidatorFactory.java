package by.epam.university.service.validator.factory;

import by.epam.university.service.validator.ApplicationValidator;
import by.epam.university.service.validator.FacultyValidator;
import by.epam.university.service.validator.PrivilegeValidator;
import by.epam.university.service.validator.SchoolValidator;
import by.epam.university.service.validator.SpecialtyValidator;
import by.epam.university.service.validator.SubjectValidator;
import by.epam.university.service.validator.TypeStudyValidator;
import by.epam.university.service.validator.UserValidator;
import by.epam.university.service.validator.impl.ApplicationValidatorImpl;
import by.epam.university.service.validator.impl.FacultyValidatorImpl;
import by.epam.university.service.validator.impl.PrivilegeValidatorImpl;
import by.epam.university.service.validator.impl.SchoolValidatorImpl;
import by.epam.university.service.validator.impl.SpecialtyValidatorImpl;
import by.epam.university.service.validator.impl.SubjectValidatorImpl;
import by.epam.university.service.validator.impl.TypeStudyValidatorImpl;
import by.epam.university.service.validator.impl.UserValidatorImpl;

public class ValidatorFactory {
	private static volatile ValidatorFactory instance;

	public static ValidatorFactory getInstance() {
		ValidatorFactory localInstance = instance;
		if (localInstance == null) {
			synchronized (ValidatorFactory.class) {
				if (localInstance == null) {
					instance = localInstance = new ValidatorFactory();
				}
			}
		}
		return localInstance;
	}
	
	public UserValidator getUserValidator() {
		return new UserValidatorImpl();
	}
	
	public SubjectValidator getSubjectValidator() {
		return new SubjectValidatorImpl();
	}
	
	public ApplicationValidator getApplicationValidator() {
		return new ApplicationValidatorImpl();
	}
	
	public PrivilegeValidator getPrivilegeValidator() {
		return new PrivilegeValidatorImpl();
	}
	
	public SpecialtyValidator getSpecialtyValidator() {
		return new SpecialtyValidatorImpl();
	}
	
	public TypeStudyValidator getTypeStudyValidator() {
		return new TypeStudyValidatorImpl();
	}
	
	public SchoolValidator getSchoolValidator() {
		return new SchoolValidatorImpl();
	}
	
	public FacultyValidator getFacultyValidator() {
		return new FacultyValidatorImpl();
	}

}
