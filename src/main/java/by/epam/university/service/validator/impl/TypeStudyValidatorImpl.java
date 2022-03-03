package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.entity.TypeStudy;
import by.epam.university.service.validator.TypeStudyValidator;
import by.epam.university.service.validator.util.ValidatorParameters;

public class TypeStudyValidatorImpl implements TypeStudyValidator {

	@Override
	public List<String> validate(String typeName) {
		List<String> validation = new ArrayList<String>();

		if (typeName == null) {
			validation.add(ValidatorParameters.INVALID_TYPE_NAME);
		}

		
		return validation;
	}

	@Override
	public boolean validateTypeStudy(TypeStudy typeStudy) {

		List<String> validation = validate(typeStudy.getTypeName());
		boolean isValid = true;
		if (validation != null || validation.size() != 0) {
			return false;
		}
		return isValid;
	}

}
