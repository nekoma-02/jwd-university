package by.epam.university.service.validator;

import java.util.List;

import by.epam.university.entity.TypeStudy;

public interface TypeStudyValidator {

	List<String> validate(String typeName);
	
	boolean validateTypeStudy(TypeStudy typeStudy);
	
	
}
