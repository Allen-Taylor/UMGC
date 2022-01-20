/* Name: Taylor, Allen 	
 * CMIS 242 7384  	
 * Date: (10/16/2021) 
 * */

import java.text.NumberFormat;
import java.util.ArrayList;

public class Inventory {
	
	// ArrayList holds Book Object
	private ArrayList<Book> bookInventory = new ArrayList<Book>();
	
	// Each book added or removed will change the count 
	private int count = 0;
	
	// Add: Prompts user for book data and add to the inventory list. 
	// If the book already exists (based on id value), 
	// the add request will fail and an error message will be printed to the console.
	
	public void add(Book book) {
		Boolean exist = exists(book.getId());
		if (exist == false) {
			bookInventory.add(book);
			count++;
			System.out.println("\nMESSAGE:");
			System.out.println("--------");
			System.out.println("The book was added successfully!\n");
		} else {
			System.out.println("\nWARNING:");
			System.out.println("--------");
			System.out.println("ID already exists. The book was not added.\n");
		}
	}

	// Remove. Prompts user for book id, finds the id in the inventory list and removes it. 
	// If the book matching the id is not in the inventory, 
	// remove request will fail and an error message will be printed to the console.
	
	public void remove(String findID) {
		Boolean found = false;
		for (int i = 0; i < bookInventory.size(); i++) {
			String bookid = bookInventory.get(i).getId();
			if (bookid.equals(findID)) {
				bookInventory.remove(i);
				found = true;
				count--;
				break;
			}
		}
		if (found) {
			System.out.println("\nMESSAGE:");
			System.out.println("--------");
			System.out.println("The book was successfully removed!\n");
		} else {
			System.out.println("\nMESSAGE:");
			System.out.println("--------");
			System.out.println("The book ID provided was not found. Nothing was removed. \n");
		}
	}

	// Find: Prompts user for book id, finds the id in the inventory list and print all the data for the book (id, title, and price). 
	// If the book matching the id is not in the inventory, find request will fail and an error message will be printed to the console.
	
	public void find(String findID) {
		int bookCount = getCount();
		if(bookCount != 0) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		Boolean found = false;
		Book foundBook = null;
		for (Book book : bookInventory) {
			if (book.getId().equals(findID)) {
				foundBook = book;
				found = true;
				break;
			}
		}
		if (found) {
			System.out.println("\nBOOK FOUND:");
			System.out.println("----------");
			System.out.printf("ID: %s \n", foundBook.getId());
			System.out.printf("Title: %s \n", foundBook.getTitle());
			System.out.printf("Price: %s \n", nf.format(foundBook.getPrice()));
			System.out.println("\n");
		} else {
			System.out.println("\nMESSAGE:");
			System.out.println("--------");
			System.out.println("The book ID provided was not found.\n");
		} 
		} else {
			System.out.println("\nMESSAGE:");
			System.out.println("--------");
			System.out.println("The inventory is empty.\n");
		} 

	}
	
	// Display: Print all the book information for each book to the console.
	public void display() {
		int bookCount = getCount();
		if(bookCount != 0) {
		NumberFormat nf = NumberFormat.getCurrencyInstance();
		int count = 1;
		for (Book book : this.bookInventory) {
			System.out.println("\nBOOK " + count + ":");
			System.out.println("-------");
			System.out.printf("ID: %s \n", book.getId());
			System.out.printf("Title: %s \n", book.getTitle());
			System.out.printf("Price: %s \n", nf.format(book.getPrice()));
			System.out.println("\n");
			count++;
		}
		} else {
		System.out.println("\nMESSAGE:");
		System.out.println("--------");
		System.out.println("The inventory is empty.\n");
		}
	}
	
	// Get count of books in the inventory 
	public int getCount() {
		return count;
	}
	
	// Check if book is in the ArrayList and return a Boolean 
	private Boolean exists(String findID) {
		Boolean found = false;
		for (Book book : bookInventory) {
			if (book.getId().equals(findID)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
}
