package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.entity.Specialty;
import by.epam.university.service.validator.SpecialtyValidator;
import by.epam.university.service.validator.util.ValidatorParameters;

public class SpecialtyValidatorImpl implements SpecialtyValidator {

	@Override
	public List<String> validate(String specialtyNane, int plan, int year) {
		List<String> validation = new ArrayList<String>();

		if (specialtyNane == null) {
			validation.add(ValidatorParameters.INVALID_SPECIALTY_NAME);
		}
		
		if (plan < 0) {
			validation.add(ValidatorParameters.INVALID_PLAN);
		}
		
		if (year < 2018) {
			validation.add(ValidatorParameters.INVALID_YEAR);
		}
		
		return validation;
	}

	@Override
	public List<String> validate(String id) {
		List<String> validation = new ArrayList<String>();

		if (id == null || id.isEmpty() || Integer.parseInt(id) < 0) {
			validation.add(ValidatorParameters.INVALID_SPECIALTY);
		}

		return validation;
	}

	@Override
	public boolean validateSpecialty(Specialty specialty) {

		List<String> validation = validate(specialty.getName(), specialty.getPlan(), specialty.getYear());
		boolean isValid = true;
		if (validation != null || validation.size() != 0) {
			return false;
		}
		return isValid;
	}
	

}
