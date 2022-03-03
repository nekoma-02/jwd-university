package by.epam.university.service.exception;

public class UserExistsException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9131036952654757213L;

	public UserExistsException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserExistsException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
