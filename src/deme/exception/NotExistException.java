package deme.exception;

public class NotExistException extends RuntimeException{
	 private static final long serialVersionUID = 1L;

	    public NotExistException() {
	        super();
	    }

	    public NotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	        super(message, cause, enableSuppression, writableStackTrace);
	    }

	    public NotExistException(String message, Throwable cause) {
	        super(message, cause);
	    }

	    public NotExistException(String message) {
	        super(message);
	    }

	    public NotExistException(Throwable cause) {
	        super(cause);
	    }
}
