package by.epam.university.service.validator;

import java.util.List;

import by.epam.university.entity.Specialty;

public interface SpecialtyValidator {
	
	List<String> validate(String id);
	
	List<String> validate(String specialtyNane, int plan, int year);
	
	boolean validateSpecialty(Specialty specialty);

}
