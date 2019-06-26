package customExceptions;

/**
 * Custom exception when a fragment record is inserted
 * and the video id does not exist.
 * @author Lowell Gilbertson
 *
 */
public class InvalidVideoException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public InvalidVideoException(String message) {
		super(message);
	}
	
	

}
