package customExceptions;


/**
 * Custom exception thrown when an invalid userID
 * is inserted into the Db.
 * @author Lowell Gilbertson
 *
 */
public class InvalidUserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidUserException(String message) {
		super(message);
	}
	
	

}
