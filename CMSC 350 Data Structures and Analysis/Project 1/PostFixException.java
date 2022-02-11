/**
 * This class generates a custom Postfix Exception. Extends the Exception class.
 */
class PostFixException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * SyntaxError Constructor
	 * 
	 * @param message A variable type of String
	 */
	PostFixException(String message) {
		super(message);
	}
}