
/**  
* Gift.java - Abstract Class used in Gift Basket Menu.   
* 
* @author  Allen Taylor
* @course CMIS 242 7384 
* @date 11/15/2021
*/

import java.text.DecimalFormat;

public abstract class Gift {

	private String id;
	private String size;
	private Double price;
	final Double SMALL = 19.99;
	final Double MEDIUM = 29.99;
	final Double LARGE = 49.99;

	/**
	 * Gift Constructor
	 * 
	 * @param id   A variable of type String.
	 * @param size A variable of type String.
	 */
	public Gift(String id, String size) {
		this.id = id;
		this.size = size;
	}

	/**
	 * Retrieve the value of Id.
	 * 
	 * @return A String data type.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Retrieve the value of Size.
	 * 
	 * @return A String data type.
	 */
	public String getSize() {
		return size;
	}

	/**
	 * Retrieve the value of Price.
	 * 
	 * @return A Double data type.
	 */
	public Double getPrice() {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.valueOf(df.format(price));
	}

	/**
	 * Set the value of Size.
	 * 
	 * @param size A variable of type String.
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * Set the value of Price.
	 * 
	 * @param price A variable of type Double.
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
}
