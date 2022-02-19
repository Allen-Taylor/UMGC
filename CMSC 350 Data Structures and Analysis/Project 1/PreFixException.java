/**
 * This class generates a custom Prefix Exception. Extends the Exception class.
 */
class PreFixException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * SyntaxError Constructor
	 * 
	 * @param message A variable type of String
	 */
	PreFixException(String message) {
		super(message);
	}
}