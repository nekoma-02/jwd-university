package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.entity.School;
import by.epam.university.service.validator.SchoolValidator;
import by.epam.university.service.validator.util.ValidatorParameters;

public class SchoolValidatorImpl implements SchoolValidator {

	@Override
	public List<String> validate(String nameSchool, String level, String institution) {
		List<String> validation = validate(level, institution);

		if (nameSchool == null) {
			validation.add(ValidatorParameters.INVALID_SCHOOL_NAME);
		}

		
		return validation;
	}

	@Override
	public List<String> validate(String level, String institution) {
		List<String> validation = new ArrayList<String>();

		if (level == null) {
			validation.add(ValidatorParameters.INVALID_LEVEL);
		}
		
		if (institution == null) {
			validation.add(ValidatorParameters.INVALID_INSTITUTION);
		}
		
		return validation;
	}

	@Override
	public List<String> validate(String id) {
		List<String> validation = new ArrayList<String>();

		if (id == null || id.isEmpty() || Integer.parseInt(id) < 0) {
			validation.add(ValidatorParameters.INVALID_SCHOOL);
		}

		return validation;
	}

	@Override
	public boolean validateSchool(School school) {
		List<String> validation = validate(school.getName(), school.getLevel(), school.getInstitution());
		boolean isValid = true;
		if (validation != null || validation.size() != 0) {
			return false;
		}
		return isValid;
	}

}
