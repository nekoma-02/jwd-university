package by.epam.university.service.exception;

import java.util.List;

public class InvalidInputDataException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4531327515489693758L;

	private List<String> invalidData;

	public List<String> getInvalidData() {
		return invalidData;
	}

	public InvalidInputDataException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InvalidInputDataException(String message, List<String> invalidData) {
		super(message);
		if (invalidData == null) {
			this.invalidData = invalidData;
		} else {
			this.invalidData.addAll(invalidData);
		}

	}

	public InvalidInputDataException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InvalidInputDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InvalidInputDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InvalidInputDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
