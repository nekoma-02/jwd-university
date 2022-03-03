package by.epam.university.service.validator;

import java.util.List;

import by.epam.university.entity.User;
import by.epam.university.service.validator.util.TypeUserValidate;



public interface UserValidator {

	List<String> validate(String login, String password);
	
	List<String> validate(String name,String secondName,String lastName, String email);
	
	List<String> validate(String name,String secondName,String lastName, String email,String gender, String maritalStatus, String placeOfBirth);
	
	boolean validate(User user, TypeUserValidate type);

}
