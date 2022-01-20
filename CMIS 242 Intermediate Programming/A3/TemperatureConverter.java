/**  
* TemperatureConverter.java - Sub-Class used in Temperature and Distance Converter.    
* 
* @author  Allen Taylor
* @course CMIS 242 7384 
* @date 11/27/2021
*/
public class TemperatureConverter extends Converter {

	/**
	 * Default TemperatureConverter Constructor with super() from Converter.java.
	 */
	public TemperatureConverter() {
		super();
	}

	/**
	 * Overloaded TemperatureConverter Constructor with super() from Converter.java.
	 * 
	 * @param input A variable of type Double.
	 */
	public TemperatureConverter(Double input) {
		super(input);
	}
	
	/**
	 * Overridden convert() method to convert input (Fahrenheit temperature) to Celsius and returns the value.  
	 * If the instance has no input value, return Double.NaN.
	 * Use the following formula for conversion: C = ((F-32)*5)/9.
	 * 
	 * @return A Double data type.
	 */
	@Override
	public Double convert() {
		Double fahrenheit = getInput();
		if (fahrenheit != Double.NaN) {
			Double celsius;
			celsius = ((fahrenheit - 32) * 5) / 9;
			return celsius;
		} else {
			return Double.NaN;
		}

	}
}
