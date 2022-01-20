/**
 * MovieDVD.java - A class used to create MovieDVD Media Objects for the Media
 * Rental System.
 */

public class MovieDVD extends Media {

	private Double size;

	/**
	 * MovieDVD Constructor
	 * 
	 * @param id - A variable type of String.
	 * @param title - A variable type of String.
	 * @param year - A variable type of Integer.
	 * @param size - A variable type of Double.
	 * @param available - A variable type of Boolean.
	 */
	public MovieDVD(String id, String title, Integer year, Double size, Boolean available) {
		super(id, title, year, available);
		this.size = size;
	}

	/**
	 * Retrieve MovieDVD size.
	 * 
	 * @return An Double data type.
	 */
	public Double getSize() {
		return size;
	}

	/**
	 * Set MovieDVD size.
	 * 
	 * @param size - A variable type of Double.
	 */
	public void setSize(Double size) {
		this.size = size;
	}

	/**
	 * Override toString().
	 * 
	 * @return A String data type.
	 */
	@Override
	public String toString() {
		return "MovieDVD [ id=" + getId() + ", title=" + getTitle() + ", year=" + getYear() + ", size=" + getSize()
				+ " MB, available=" + getAvailable() + "]";
	}

}
