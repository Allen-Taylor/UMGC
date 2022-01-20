/**
 * MusicCD.java - A class used to create MusicCD Media Objects for the Media
 * Rental System
 */

import java.util.Calendar;

public class MusicCD extends Media {

	private Integer length;

	/**
	 * MusicCD Constructor
	 * 
	 * @param id - A variable type of String.
	 * @param title - A variable type of String.
	 * @param year - A variable type of Integer.
	 * @param chapters - A variable type of Integer.
	 * @param available - A variable type of Boolean.
	 */
	public MusicCD(String id, String title, Integer year, Integer length, Boolean available) {
		super(id, title, year, available);
		this.length = length;
	}

	/**
	 * Retrieve the length of a Music CD.
	 * 
	 * @return An Integer data type.
	 */
	public Integer getLength() {
		return length;
	}

	/**
	 * Set the length of a Music CD.
	 * 
	 * @param length - A variable type of Integer.
	 */
	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * Calculate Media Rental Fee. Overrides Media Class.
	 * 
	 * @return A Double data type.
	 */
	@Override
	public Double calculateRentalFee() {
		Double fee = length * 0.02; // basic fee
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
		return "MusicCD [id=" + getId() + ", title=" + getTitle() + ", year=" + getYear() + ", length=" + getLength()
				+ " mins, available=" + getAvailable() + "]";
	}
}