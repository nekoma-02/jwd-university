package by.epam.university.service.validator;

import java.util.List;

import by.epam.university.entity.Faculty;

public interface FacultyValidator {

	List<String> validate(String facultyName);

	boolean validateFaculty(Faculty faculty);

}
