/**
 * SweetsBasket.java - Sub Class of Gift that is used in the Gift Basket Menu.
 * 
 * @author Allen Taylor
 * @course CMIS 242 7384
 * @date 11/15/2021
 */

public class SweetsBasket extends Gift {

	private Boolean nutsBool;

	/**
	 * SweetsBasket Constructor
	 * 
	 * @param id       A variable of type String.
	 * @param size     A variable of type String.
	 * @param nutsBool A variable of type Boolean.
	 */
	public SweetsBasket(String id, String size, Boolean nutsBool) {
		super(id, size);
		this.nutsBool = nutsBool;

		if (this.getSize().equals("S")) {
			this.setPrice(this.SMALL);
		}

		if (this.getSize().equals("M")) {
			this.setPrice(this.MEDIUM);
		}

		if (this.getSize().equals("L")) {
			this.setPrice(this.LARGE);
		}
	}

	/**
	 * Retrieve the value of nutsBool.
	 * 
	 * @return A Boolean data type.
	 */
	public Boolean getNutsBool() {
		return nutsBool;
	}

	/**
	 * Set the value of nutsBool.
	 * 
	 * @param nutsBool A variable of type Boolean.
	 */
	public void setNutsBool(Boolean nutsBool) {
		this.nutsBool = nutsBool;
	}

	/**
	 * Method used to change all values of Sweets Basket.
	 * 
	 * @param size A variable of type String.
	 * @param nutsBool A variable of type Boolean.
	 */
	public void changeSweetsBasket(String size, Boolean nutsBool) {
		this.setSize(size);
		this.setNutsBool(nutsBool);

		if (this.getSize().equals("S")) {
			this.setPrice(this.SMALL);
		}

		if (this.getSize().equals("M")) {
			this.setPrice(this.MEDIUM);
		}

		if (this.getSize().equals("L")) {
			this.setPrice(this.LARGE);
		}
	}

	/**
	 * toString Override
	 */
	@Override
	public String toString() {
		return "SweetsBasket [id =" + getId() + ", size =" + getSize() + ", price= $" + getPrice() + ", nutsBool ="
				+ getNutsBool() + "]";
	}

}
