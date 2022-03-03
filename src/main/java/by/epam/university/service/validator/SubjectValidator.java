package by.epam.university.service.validator;

import java.util.List;

import by.epam.university.entity.Subject;

public interface SubjectValidator {

	List<String> validate(String name);

	boolean validateSubject(Subject subject);

	List<String> validate(String... name);

}
