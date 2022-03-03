package by.epam.university.service.validator;

import java.text.ParseException;
import java.util.List;

import by.epam.university.entity.Application;

public interface ApplicationValidator {

	List<String> validate(String adres, int certificate, String typeDocument, String idDocument, String seriesPassport, int numberPassport, String issuedBy, String endStudyDate) throws ParseException;

	boolean validateApplication(Application application) throws ParseException;
}
