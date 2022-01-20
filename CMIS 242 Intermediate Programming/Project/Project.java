
/**  
* Project.java - Media Rental System
* @author  Allen Taylor
* @course CMIS 242 7384
* @date 12/12/2021
*/

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import org.w3c.dom.Document;

public class Project {

	/**
	 * Main Program.
	 */
	public static void main(String[] args) {

		// Scanners
		Scanner menuScan = new Scanner(System.in);
		Scanner scanStr = new Scanner(System.in);

		// Variables
		Integer menuInt = 0;
		Boolean mainMenuFlag = false;
		String directory = null;
		String c2Input, c3Input;
		Document doc = null;
		List<Media> mediaList = new ArrayList<Media>();
		Manager manager = new Manager();

		/**
		 * Main Menu Loop
		 */
		while (mainMenuFlag == false) {
			print("==================================");
			print("Welcome to the Media Rental System");
			print("==================================");
			print("1: Load Media Objects");
			print("2: Find Media Objects");
			print("3: Rent Media Objects");
			print("4: Exit program");
			print("==================================");

			/**
			 * Selection Loop
			 */
			while (true) {
				try {
					print("Enter your selection: ");
					print("==================================");
					menuInt = menuScan.nextInt();
					break;
				} catch (InputMismatchException e) {
					print("Invalid selection... Try again...");
					menuScan.next();
				}
			}

			/**
			 * Media Rental System Menu Switch
			 */
			switch (menuInt) {
			/**
			 * 1: Load Media Objects
			 */
			case 1:
				print("Enter path (directory) where to load from (default: './database/'):"); // ./database/
				directory = scanStr.nextLine();
				doc = manager.loadMediaFile(directory);
				if (doc != null) {
					print("Database loaded...");
					mediaList = manager.xmlToList(doc);
					if (mediaList != null) {
						for (Media media : mediaList) {
							print(media.toString()); // Display Media Objects loaded from the XML Database. 
						}
					} else {
						print("Media Objects failed to load...");
					}
				} else {
					print("Database failed to load...");
				}
				break;
			/**
			 * 2: Find Media Objects
			 */
			case 2:
				Integer c2Size = mediaList.size();
				if (mediaList == null || c2Size == 0) {
					print("No Media Objects were loaded...");
				} else {
					print("Enter the title you wish to find:");
					c2Input = scanStr.nextLine();
					List<String> foundItems = manager.findMedia(c2Input);
					Integer foundItemsSize = foundItems.size();
					if (foundItemsSize == 0) {
						print("No items match the title: " + c2Input);
					} else {
						for (String item : foundItems) {
							print(item);
						}
					}
				}

				break;

			/**
			 * 3: Rent Media Objects
			 */
			case 3:
				Integer c3Size = mediaList.size();
				if (mediaList == null || c3Size == 0) {
					print("No Media Objects were loaded...");
				} else {
					print("Enter the Media Object ID to rent:");
					c3Input = scanStr.nextLine();
					Double fee = manager.rentMedia(doc, c3Input, directory);
					if (fee != 0.0) {
						NumberFormat formatter = NumberFormat.getCurrencyInstance();
						String feeString = formatter.format(fee);
						print("Media was successfully rented. Rental fee = " + feeString);
					}
				}

				break;
			/**
			 * 4: Exit program
			 */
			case 4:
				mainMenuFlag = true;
				print("Thank you for using the Media Rental System. Goodbye!");
				break;
			/**
			 * Default Case
			 */
			default:
				print("Invalid menu selection. Try again.");
			}
		}

		// Close Scanners
		menuScan.close();
		scanStr.close();

	}
		
	/**
	 * Method used to shorten print statement, similar to Python.
	 * 
	 * @param string - A variable type of String.
	 */
	public static void print(String string) {
		System.out.println(string);
	}

}
