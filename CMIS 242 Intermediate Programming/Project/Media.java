/**
 * Media.java - An abstract class for the Media Rental System.
 */

public abstract class Media {

	private String id;
	private String title;
	private Integer year;
	private Boolean available;

	/**
	 * Media Constructor
	 * 
	 * @param id - A variable type of String.
	 * @param title - A variable type of String.
	 * @param year - A variable type of Integer.
	 * @param available - A variable type of Boolean.
	 */
	public Media(String id, String title, Integer year, Boolean available) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.available = available;
	}

	/**
	 * Retrieve Media ID.
	 * 
	 * @return A String data type.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Retrieve Media Title.
	 * 
	 * @return A String data type.
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Retrieve Media Year.
	 * 
	 * @return An Integer data type.
	 */
	public Integer getYear() {
		return year;
	}

	/**
	 * Retrieve Media Availability.
	 * 
	 * @return A Boolean data type.
	 */
	public Boolean getAvailable() {
		return available;
	}

	/**
	 * Set Media Title.
	 * 
	 * @param title - A variable type of String.
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Set Media Title.
	 * 
	 * @param year - A variable type of Integer.
	 */
	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * Set Media Availability.
	 * 
	 * @param available - A variable type of Boolean.
	 */
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	/**
	 * Calculate Media Rental Fee.
	 * 
	 * @return A Double data type.
	 */
	public Double calculateRentalFee() {
		return 3.50;
	}

}