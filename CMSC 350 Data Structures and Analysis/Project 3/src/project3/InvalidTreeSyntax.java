/**
 * This class generates a custom InvalidTreeSyntax Exception and extends the Exception class.
 */
package project3;

class InvalidTreeSyntax extends Exception
{	String message;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTreeSyntax(String message)
	{
		super(message);
		this.message = message;
	}
	
	public String toString(){
		return "Invalid Tree Syntax: "+message;
	}
}