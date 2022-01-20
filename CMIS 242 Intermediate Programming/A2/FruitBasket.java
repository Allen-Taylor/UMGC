/**
 * FruitBasket.java - Sub Class of Gift used in the Gift Basket Menu.
 * 
 * @author Allen Taylor
 * @course CMIS 242 7384
 * @date 11/15/2021
 */

public class FruitBasket extends Gift {

	private Integer numFruits;
	private Boolean haveCitrus;
	final Double CITRUSPRICE = 5.99;
	final Integer SMALLFRUIT = 6;
	final Integer MEDIUMFRUIT = 9;
	final Integer LARGEFRUIT = 15;

	/**
	 * FruitBasket Constructor
	 * 
	 * @param id         A variable of type String.
	 * @param size       A variable of type String.
	 * @param haveCitrus A variable of type Boolean.
	 */
	public FruitBasket(String id, String size, Boolean haveCitrus) {
		super(id, size);
		this.haveCitrus = haveCitrus;
		this.setFruitBasketValues();
	}

	/**
	 * Retrieve the value of numFruits.
	 * 
	 * @return An Integer data type.
	 */
	public Integer getNumFruits() {
		return numFruits;
	}

	/**
	 * Retrieve the value of haveCitrus.
	 * 
	 * @return A Boolean data type.
	 */
	public Boolean getHaveCitrus() {
		return haveCitrus;
	}

	/**
	 * Set the value of numFruits.
	 * 
	 * @param numFruits A variable of type Integer.
	 */
	public void setNumFruits(Integer numFruits) {
		this.numFruits = numFruits;
	}

	/**
	 * Set the value of haveCitrus.
	 * 
	 * @param haveCitrus A variable of type Boolean.
	 */
	public void setHaveCitrus(Boolean haveCitrus) {
		this.haveCitrus = haveCitrus;
	}

	/**
	 * Method used to set numFruits and Price based on Size, then check haveCitrus
	 * Boolean for additional cost.
	 */
	private void setFruitBasketValues() {

		if (this.getSize().equals("S")) {
			this.setNumFruits(SMALLFRUIT);
			this.setPrice(this.SMALL);
		}

		if (this.getSize().equals("M")) {
			this.setNumFruits(MEDIUMFRUIT);
			this.setPrice(this.MEDIUM);
		}

		if (this.getSize().equals("L")) {
			this.setNumFruits(LARGEFRUIT);
			this.setPrice(this.LARGE);
		}

		if (haveCitrus) {
			this.addCitrusPrice();
		}
	}

	/**
	 * Method used to change the values of a Fruit Basket.
	 */
	public void changeFruitBasketValues(String size, Boolean haveCitrus) {
		this.setSize(size);
		this.setHaveCitrus(haveCitrus);
		this.setFruitBasketValues();
	}

	/**
	 * Method that adds additional cost when Citrus Fruits are included in a Fruit
	 * Basket.
	 */
	private void addCitrusPrice() {
		this.setPrice(this.getPrice() + this.CITRUSPRICE);
	}

	/**
	 * toString Override
	 */
	@Override
	public String toString() {
		return "FruitBasket [id= " + getId() + ", size= " + getSize() + ", price= $" + getPrice() + ", numFruits= "
				+ getNumFruits() + ", haveCitrus= " + getHaveCitrus() + "]";
	}

}
