/* Name: Taylor, Allen 	
 * CMIS 242 7384  	
 * Date: (10/16/2021) 
 * */

public class Book {
	private String id, title; 
	private Double price;

	public Book(String id, String title, Double price) {
		this.id = id;
		this.title = title;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
