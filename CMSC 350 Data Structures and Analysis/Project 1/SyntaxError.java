/**
 * This class generates a SyntaxError. Extends the Exception class.
 */
class SyntaxError extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * SyntaxError Constructor
	 * 
	 * @param message A variable type of String
	 */
	SyntaxError(String message) {
		super(message);
	}
}