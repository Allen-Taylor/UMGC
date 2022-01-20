/**
 * EBook.java - A class used to create EBook Media Objects for the Media Rental
 * System
 */

import java.util.Calendar;

public class EBook extends Media {

	private Integer numChapters;

	/**
	 * EBook Constructor
	 * 
	 * @param id - A variable type of String.
	 * @param title - A variable type of String.
	 * @param year - A variable type of Integer.
	 * @param chapters - A variable type of Integer.
	 * @param available - A variable type of Boolean.
	 */
	public EBook(String id, String title, Integer year, Integer chapters, Boolean available) {
		super(id, title, year, available);
		numChapters = chapters;
	}

	/**
	 * Retrieve number of chapters for an EBook.
	 * 
	 * @return An Integer data type.
	 */
	public Integer getNumChapters() {
		return numChapters;
	}

	/**
	 * Set the number of chapters for an EBook.
	 * 
	 * @param numChapters - A variable type of Integer.
	 */
	public void setNumChapters(Integer numChapters) {
		this.numChapters = numChapters;
	}

	/**
	 * Calculate Media Rental Fee. Overrides Media Class.
	 * 
	 * @return A Double data type.
	 */
	@Override
	public Double calculateRentalFee() {
		Double fee = numChapters * 0.10; // basic fee
		Integer currYear = Calendar.getInstance().get(Calendar.YEAR);
		if (this.getYear() == currYear)
			fee += 1; // add $1.00 fee
		return fee;
	}

	/**
	 * Override toString().
	 * 
	 * @return A String data type.
	 */
	@Override
	public String toString() {
		return "EBook [ id=" + getId() + ", title=" + getTitle() + ", year=" + getYear() + ", chapters="
				+ getNumChapters() + ", available=" + getAvailable() + "]";
	}
}
