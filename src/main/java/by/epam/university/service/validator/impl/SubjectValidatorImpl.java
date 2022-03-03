package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.entity.Subject;
import by.epam.university.service.validator.SubjectValidator;
import by.epam.university.service.validator.util.ValidatorParameters;

public class SubjectValidatorImpl implements SubjectValidator {

	@Override
	public List<String> validate(String... name) {
		List<String> validation = new ArrayList<String>();

		for (String string : name) {
			if (name == null) {
				validation.add(ValidatorParameters.INVALID_SUBJECT_NAME);
				break;
			}
		}

		return validation;
	}

	@Override
	public boolean validateSubject(Subject subject) {
		List<String> validation = validate(subject.getName());
		boolean isValid = true;
		if (validation != null || validation.size() != 0) {
			return false;
		}
		return isValid;
	}

	@Override
	public List<String> validate(String name) {
		List<String> validation = new ArrayList<String>();

		if (name == null) {
			validation.add(ValidatorParameters.INVALID_SUBJECT_NAME);
		}

		return validation;
	}

}
