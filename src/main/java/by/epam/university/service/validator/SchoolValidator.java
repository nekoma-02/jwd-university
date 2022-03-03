package by.epam.university.service.validator;

import java.util.List;

import by.epam.university.entity.School;

public interface SchoolValidator {
	List<String> validate(String id);

	List<String> validate(String level, String institution);

	List<String> validate(String nameSchool, String level, String institution);

	boolean validateSchool(School school);

}
