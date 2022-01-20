/**  
* Converter.java - Abstract Class used in Temperature and Distance Converter.    
* 
* @author  Allen Taylor
* @course CMIS 242 7384 
* @date 11/27/2021
*/
public abstract class Converter {

	private Double input;

	/**
	 * Default Converter Constructor
	 */
	public Converter() {
		this.input = Double.NaN;
	}

	/**
	 * Overload Converter Constructor
	 * 
	 * @param input A variable of type Double.
	 */
	public Converter(Double input) {
		this.input = input;
	}

	/**
	 * Retrieve the value of Id.
	 * 
	 * @return A Double data type.
	 */
	public Double getInput() {
		return input;
	}

	/**
	 * Set the value of Input.
	 * 
	 * @param input A variable of type Double.
	 */
	public void setInput(Double input) {
		this.input = input;
	}

	/**
	 * Convert method
	 * 
	 * @return A Double data type.
	 */
	public Double convert() {
		return input;
	};

}
