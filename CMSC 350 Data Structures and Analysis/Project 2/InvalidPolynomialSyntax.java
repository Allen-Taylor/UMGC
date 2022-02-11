/**
 * This class generates a custom InvalidPolynomialSyntax Exception and extends the Exception class.
 */
public class InvalidPolynomialSyntax extends Exception {

	private static final long serialVersionUID = 1L;
	
	/**
	 * InvalidPolynomialSyntax Constructor
	 * 
	 * @param message A variable type of String
	 */

   public InvalidPolynomialSyntax(String msg) {
        super(msg);
   }
}