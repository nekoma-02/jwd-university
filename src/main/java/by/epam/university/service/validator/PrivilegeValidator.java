package by.epam.university.service.validator;

import java.util.List;

import by.epam.university.entity.Privilege;

public interface PrivilegeValidator {

	List<String> validate(int id);
	
	List<String> validate(String privilegeName);
	
	boolean validatePrivilege(Privilege privilege);
}
