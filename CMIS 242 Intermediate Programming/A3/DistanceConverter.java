/**  
* DistanceConverter.java - Sub-Class used in Temperature and Distance Converter.    
* 
* @author  Allen Taylor
* @course CMIS 242 7384 
* @date 11/27/2021
*/
public class DistanceConverter extends Converter{
	
	/**
	 * Default DistanceConverter Constructor with super() from Converter.java.
	 */
	public DistanceConverter() {
		super();
	}
	
	/**
	 * Overloaded DistanceConverter Constructor with super() from Converter.java.
	 * 
	 * @param input A variable of type Double.
	 */
	public DistanceConverter(Double input) {
		super(input);
	}

	/**
	 * Overridden convert() method to convert input (distance in miles) to distance in kilometers and returns the value.  
	 * If the instance has no input value, return Double.NaN.
	 * Uses the following formula for conversion: KM = M * 1.609.
	 * 
	 * @return A Double data type.
	 */
	@Override
	public Double convert() {
		Double miles = getInput();
		if(miles != Double.NaN) {
			Double km;
			km = miles * 1.609;
			return km;
		} else {
			return Double.NaN;
		}
	}

}
