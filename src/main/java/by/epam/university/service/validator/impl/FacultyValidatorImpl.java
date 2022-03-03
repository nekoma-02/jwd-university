package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.entity.Faculty;
import by.epam.university.service.validator.FacultyValidator;
import by.epam.university.service.validator.util.ValidatorParameters;

public class FacultyValidatorImpl implements FacultyValidator {

	@Override
	public List<String> validate(String facultyName) {
		List<String> validation = new ArrayList<String>();
		
		if (facultyName == null ) {
			validation.add(ValidatorParameters.INVALID_FACULTY_NAME);
		}
		
		return validation;
	}

	@Override
	public boolean validateFaculty(Faculty faculty) {

		List<String> validation = validate(faculty.getName());
		boolean isValid = true;
		if (validation != null || validation.size() != 0) {
			return false;
		}
		return isValid;
	}

}
