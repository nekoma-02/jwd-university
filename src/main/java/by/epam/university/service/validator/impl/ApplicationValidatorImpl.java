package by.epam.university.service.validator.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import by.epam.university.entity.Application;
import by.epam.university.service.validator.ApplicationValidator;
import by.epam.university.service.validator.util.ValidatorParameters;

public class ApplicationValidatorImpl implements ApplicationValidator {

	private static final String DATE_FORMAT = "yyyy-mm-dd";
	private static final String NUMBER_PASSPORT_PATTERN = "^\\d{7}$";
	private static final String SERIES_PASSPORT_PATTERN = "^\\D{2}$";
	private static final String ID_DOCUMENT_PATTERN = "^[A-Z0-9]{14}$";
	
	
	private boolean validateNumberPassport(String numberPassport) {
		return numberPassport.matches(NUMBER_PASSPORT_PATTERN);
	}
	
	private boolean validateSeriesPassport(String series) {
		return series.matches(SERIES_PASSPORT_PATTERN);
	}
	
	private boolean validateIdDocument(String idDocument) {
		return idDocument.matches(ID_DOCUMENT_PATTERN);
	}
	
	@Override
	public List<String> validate(String adress, int certificate, String typeDocument, String idDocument,
			String seriesPassport, int numberPassport, String issuedBy, String endStudyDate) throws ParseException {
		List<String> validation = new ArrayList<String>();
		
		if (adress == null ) {
			validation.add(ValidatorParameters.INVALID_ADRESS);
		}
		
		if (certificate < 0 && certificate > 100) {
			validation.add(ValidatorParameters.INVALID_CERTIFICATE);
		}
		
		if (typeDocument == null ) {
			validation.add(ValidatorParameters.INVALID_TYPE_DOCUMENT);
		}
		
		if (idDocument == null || !validateIdDocument(idDocument)) {
			validation.add(ValidatorParameters.INVALID_ID_DOCUMENT);
		}
		
		if (seriesPassport == null || !validateSeriesPassport(seriesPassport) ) {
			validation.add(ValidatorParameters.INVALID_SERIES_PASSPORT);
		}
		
		if (!validateNumberPassport(Integer.toString(numberPassport))) {
			validation.add(ValidatorParameters.INVALID_NUMBER_PASSPORT);
		}
		
		if (issuedBy == null ) {
			validation.add(ValidatorParameters.INVALID_ISSUED_BY);
		}
		
		if (endStudyDate == null || !isValidFormat(endStudyDate)) {
			validation.add(ValidatorParameters.INVALID_END_STUDY_DATE);
		}
		
		return validation;
	}
	
	
	private static boolean isValidFormat(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
		java.util.Date udate = formatter.parse(date);
		if (!date.equals(formatter.format(udate))) {
			return false;
		}
		return true;
	}


	@Override
	public boolean validateApplication(Application application) throws ParseException {
		List<String> validation = validate(application.getAdress(), application.getCertificate(), application.getTypeDocument(), application.getIdDocument(), application.getSeriesPassport(), application.getNumberPassport(), application.getIssuedBy(), application.getEndStudyDate().toString());
		boolean isValid = true;
		if (validation != null || validation.size() != 0) {
			return false;
		}
		return isValid;
	}

}
