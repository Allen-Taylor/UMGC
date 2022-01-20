/* Name: Taylor, Allen 	
 * CMIS 242 7384  	
 * Date: (10/16/2021) 
 * */

import java.util.Scanner;
public class Assignment1 {
	
	// simple print statement
	public static void print(String string) {
		System.out.println(string);
	}
	
	// Main Program 
	public static void main(String[] args) {
		
		// Create Inventory Object
		Inventory bookInventory = new Inventory();

		// Scanners
		Scanner menuScan = new Scanner(System.in);
		Scanner scanInt = new Scanner(System.in);
		Scanner scanString = new Scanner(System.in);

		// Integers, Booleans
		int menuInt = 0;
		boolean mainMenuFlag = false;

		// Start Menu
		while (mainMenuFlag == false) { // Menu Loop

			print("======================");
			print("Book Inventory System");
			print("======================");
			print("1: Add book");
			print("2: Remove book");
			print("3: Find book");
			print("4: Display all books");
			print("5: Exit program");

			while (true) { // Selection Loop

				try {
					print("======================");
					print("Enter your selection: ");
					print("======================");
					menuInt = menuScan.nextInt(); // User enters Menu Selection
					break;
				} catch (Exception e) {
					print("Invalid selection... Try again...");
					menuScan.next();
				}
			}

			switch (menuInt) { // Menu Switch
			case 1:
				while (true) {
					try {
						print("Enter ID, Title, and Price separated by commas (Example: 1, \"Lord of the Rings\", 5.00): ");
						String inputString = scanString.nextLine();
						String[] inputArray = inputString.split(","); 
						String id = inputArray[0];
						String title = inputArray[1]; 
						Double price = Double.parseDouble(inputArray[2]); 
						Book book = new Book(id, title, price); 
						bookInventory.add(book);
						break;			
					} catch (Exception e) {
						print("Invalid input... Try again...\n");
					}
				}

				break;

			case 2:
				while (true) {
					try {
						print("Enter the ID of the book to remove: ");
						String inputString = scanString.nextLine();
						bookInventory.remove(inputString);
						break;
						
					} catch (Exception e) {
						print("Invalid input... Try again...\n");
					}
				}
				break;
			case 3:
				while (true) {
					try {
						print("The ID of the book to find: ");
						String inputString = scanString.nextLine();
						bookInventory.find(inputString);
						break;	
					} catch (Exception e) {
						print("Invalid input... Try again...\n");
					}
				}
				break;
			case 4:
				bookInventory.display();
				break;
			case 5:
				// Exit Program
				print("Thank you for using the Book Inventory System. Goodbye!");
				mainMenuFlag = true;
				break;
			default:
				print("Invalid selection. Try again.\n");
			}
		}
		// Close out scanners
		menuScan.close();
		scanInt.close();
		scanString.close();	
	}
}
