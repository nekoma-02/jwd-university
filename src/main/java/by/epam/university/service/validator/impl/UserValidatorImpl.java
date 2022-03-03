package by.epam.university.service.validator.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.university.entity.User;
import by.epam.university.service.validator.UserValidator;
import by.epam.university.service.validator.util.TypeUserValidate;
import by.epam.university.service.validator.util.ValidatorParameters;

public class UserValidatorImpl implements UserValidator {

	private static final String LOGIN_PATTERN = "^[A-Za-z0-9]{4,13}$";
	private static final String PASSWORD_PATTERN = "^[A-Za-z0-9]{6,15}$";
	private static final String NAME_PATTERN = "^([a-zA-Z-]|[à-ÿÀ-ß-]){2,25}$";
	public static final String EMAIL_PATTERN = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

	public UserValidatorImpl() {
	}

	private boolean validateEmail(String email) {
		return email.matches(EMAIL_PATTERN);
	}

	private boolean validateLogin(String login) {
		return login.matches(LOGIN_PATTERN);
	}

	private boolean validatePassword(String password) {
		return password.matches(PASSWORD_PATTERN);
	}

	private boolean validateName(String name) {
		return name.matches(NAME_PATTERN);
	}

	@Override
	public List<String> validate(String login, String password) {
		List<String> validation = new ArrayList<String>();

		if (login == null || !validateLogin(login)) {
			validation.add(ValidatorParameters.INVALID_LOGIN);
		}

		if (password == null || !validatePassword(password)) {
			validation.add(ValidatorParameters.INVALID_PASSWORD);
		}

		return validation;
	}

	@Override
	public List<String> validate(String name, String secondName, String lastName, String email) {
		List<String> validation = new ArrayList<String>();

		if (name == null || !validateName(name)) {
			validation.add(ValidatorParameters.INVALID_FIRST_NAME);
		}

		if (secondName == null || !validateName(secondName)) {
			validation.add(ValidatorParameters.INVALID_SECOND_NAME);
		}

		if (lastName == null || !validateName(lastName)) {
			validation.add(ValidatorParameters.INVALID_LAST_NAME);
		}

		if (email == null || !validateEmail(email)) {
			validation.add(ValidatorParameters.INVALID_EMAIL);
		}

		return validation;
	}

	@Override
	public List<String> validate(String name, String secondName, String lastName, String email, String gender,
			String maritalStatus, String placeOfBirth) {
		List<String> validation = validate(name, secondName, lastName, email);
		if (gender == null) {
			validation.add(ValidatorParameters.INVALID_GENDER);
		}

		if (maritalStatus == null) {
			validation.add(ValidatorParameters.INVALID_MARITAL_STATUS);
		}

		if (placeOfBirth == null) {
			validation.add(ValidatorParameters.INVALID_PLACE_OF_BIRTH);
		}

		return validation;
	}

	@Override
	public boolean validate(User user, TypeUserValidate type) {

		List<String> validation = null;
		boolean isValid = true;


		switch (type) {
		case SIGN_IN:
			validation = validate(user.getLogin(), user.getPassword());
			if (validation != null || validation.size() != 0) {
				isValid = false;
			}
			break;

		case SIGN_UP:
			validation = validate(user.getLogin(), user.getPassword());
			validation.addAll(validate(user.getName(), user.getSecondName(), user.getLastName(), user.getEmail()));
			if (validation != null || validation.size() != 0) {
				isValid = false;
			}
			break;
		case FULL:
			validation = validate(user.getLogin(), user.getPassword());
			validation.addAll(validate(user.getName(), user.getSecondName(), user.getLastName(),user.getEmail(), user.getGender(), user.getMaritalStatus(), user.getPlaceOfBirth()));
			if (validation != null || validation.size() != 0) {
				isValid = false;
			}
			break;

		default:
			break;
		}

		return isValid;

	}

}
