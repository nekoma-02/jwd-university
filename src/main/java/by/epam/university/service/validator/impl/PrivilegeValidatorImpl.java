package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.entity.Privilege;
import by.epam.university.service.validator.PrivilegeValidator;
import by.epam.university.service.validator.util.ValidatorParameters;

public class PrivilegeValidatorImpl implements PrivilegeValidator {

	@Override
	public List<String> validate(String privilegeName) {
		List<String> validation = new ArrayList<String>();

		if (privilegeName == null) {
			validation.add(ValidatorParameters.INVALID_PRIVILEGE_NAME);
		}

		return validation;
	}

	@Override
	public List<String> validate(int id) {
		List<String> validation = new ArrayList<String>();

		if (id < 0) {
			validation.add(ValidatorParameters.INVALID_PRIVILEGE);
		}

		return validation;
	}

	@Override
	public boolean validatePrivilege(Privilege privilege) {
		
		List<String> validation = validate(privilege.getName());
		boolean isValid = true;
		if (validation != null || validation.size() != 0) {
			return false;
		}
		return isValid;
		
	}

}
