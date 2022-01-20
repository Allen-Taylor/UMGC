
/**  
* Assignment2.java - Main Class used to Order/Change/Display Gift Baskets.    
* @author  Allen Taylor
* @course CMIS 242 7384 
* @date 11/15/2021
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment2 {

	/**
	 * Method used to shorten print statement, similar to Python.
	 * 
	 * @param A string data type.
	 */
	public static void print(String string) {
		System.out.println(string);
	}

	/**
	 * Method used to validate user input for size values.
	 * 
	 * @param A string data type.
	 */
	public static Boolean checkSize(String size) {
		if (size.equals("S") || size.equals("M") || size.equals("L")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method used to validate user input for basket selection values.
	 * 
	 * @param A string data type.
	 */
	public static Boolean checkNum(String input) {
		if (input.equals("1") || input.equals("2")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method used to check user input if Citrus or Nuts are included.
	 * 
	 * @param A string data type.
	 */
	public static Boolean checkBoolean(String input) {
		if (input.equals("true") || input.equals("false")) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Main Program
	 */
	public static void main(String[] args) {

		// ArrayList to hold Gift objects (Fruit or Sweets Basket)
		List<Gift> giftArray = new ArrayList<Gift>(1);

		// Scanners
		Scanner menuScan = new Scanner(System.in);
		Scanner scanInt = new Scanner(System.in);
		Scanner scanString = new Scanner(System.in);

		// Global Variables
		int menuInt = 0;
		boolean mainMenuFlag = false;
		String inputString1, inputString2, inputString3;

		// Gift Basket Menu
		while (mainMenuFlag == false) {
			// Menu Loop

			print("=================");
			print("Gift Basket Menu");
			print("=================");
			print("1: Order a Gift Basket");
			print("2: Change Gift Basket");
			print("3: Display Gifts");
			print("4: Exit program");

			while (true) {
				// Enter Selection Loop

				try {
					print("======================");
					print("Enter your selection: ");
					print("======================");
					menuInt = menuScan.nextInt();
					// User enters Menu Selection
					break;

				} catch (Exception e) {
					print("Invalid selection... Try again...");
					menuScan.next();
				}
			}

			switch (menuInt) {
			// Menu Switch
			case 1:
				// Input Validation Loop
				while (true) {
					do {
						// Input Validation Loop
						print("Do you want a Fruit Basket (1) or Sweets Basket (2)?:");
						// User enters basket type
						inputString1 = scanString.nextLine();
					} while (!checkNum(inputString1));

					do {
						// Input Validation Loop
						print("What size do you want? (S, M, or L):");
						// User enters size
						inputString2 = scanString.nextLine();
					} while (!checkSize(inputString2));

					if (inputString1.equals("1")) {

						do {
							// Input Validation Loop
							print("Do you want citrus fruits included? (true or false):");
							// User enters true or false
							inputString3 = scanString.nextLine();
						} while (!checkBoolean(inputString3));

						// Add Fruit Basket to giftArray
						FruitBasket fb = new FruitBasket("FB001", inputString2, Boolean.parseBoolean(inputString3));
						giftArray.add(0, fb);
						print("Fruit Basket ordered successfully!");

						// Break out of loop
						break;

					} else if (inputString1.equals("2")) {

						do {
							// Input Validation Loop
							print("Do you want nuts included? (true or false):");
							// User enters true or false
							inputString3 = scanString.nextLine();
						} while (!checkBoolean(inputString3));

						// Add Sweets Basket to giftArray
						SweetsBasket sb = new SweetsBasket("SB001", inputString2, Boolean.parseBoolean(inputString3));
						giftArray.add(0, sb);
						print("Sweets Basket ordered successfully!");

						// Break out of loop
						break;

					} else {
						print("Invalid input... Try again...");
					}
				}
				// Break out of loop
				break;

			case 2:
				// Input Validation Loop
				while (true) {

					// Check if giftArray is NOT empty
					if (giftArray.size() != 0) {

						// Localize Variables
						String size;
						Boolean haveCitrus;
						Boolean nutsBool;

						// Loop through giftArray
						for (Gift gift : giftArray) {

							// Checks for FruitBasket
							if (gift instanceof FruitBasket) {

								size = ((FruitBasket) gift).getSize();
								print("Current Fruit Basket Size: " + size);

								do {
									// Input Validation Loop
									print("What size do you want? (S, M, or L):");
									// User enters size
									inputString2 = scanString.nextLine();
								} while (!checkSize(inputString2));

								haveCitrus = ((FruitBasket) gift).getHaveCitrus();
								print("Citrus Fruits included: " + haveCitrus);

								do {
									// Input Validation Loop
									print("Do you want citrus fruits included? (true or false):");
									// User true or false
									inputString3 = scanString.nextLine();
								} while (!checkBoolean(inputString3));

								// Change implemented
								((FruitBasket) gift).changeFruitBasketValues(inputString2,
										Boolean.parseBoolean(inputString3));

								print("Fruit Basket modified successfully!");

								// Display changes
								for (Gift g : giftArray) {
									print(g.toString());
								}
							}

							// Checks for SweetsBasket
							if (gift instanceof SweetsBasket) {

								size = ((SweetsBasket) gift).getSize();
								print("Current Sweets Basket Size: " + size);

								do {
									// Input Validation Loop
									print("What size do you want? (S, M, or L):");
									// User enters size
									inputString2 = scanString.nextLine();
								} while (!checkSize(inputString2));

								nutsBool = ((SweetsBasket) gift).getNutsBool();
								print("Nuts included: " + nutsBool);

								do { // Input Validation Loop
									print("Do you want nuts included? (true or false):");
									inputString3 = scanString.nextLine(); // User enters true or false
								} while (!checkBoolean(inputString3));

								// Change implemented
								((SweetsBasket) gift).changeSweetsBasket(inputString2,
										Boolean.parseBoolean(inputString3));

								print("Sweets Basket modified successfully!");

								// Display changes
								for (Gift g : giftArray) {
									print(g.toString());
								}
							}
						}

					} else {
						// ArrayList is empty
						print("No gift has been ordered yet!");
					}

					// Break out of loop
					break;
				}

				// Break out of loop
				break;
			case 3:
				// Check if giftArray is NOT empty
				if (giftArray.size() != 0) {
					for (Gift gift : giftArray) {
						print(gift.toString());
					}
				} else {
					// ArrayList is empty
					print("No gift has been ordered yet!");
				}

				// Break out of loop
				break;
			case 4:
				// Exit Program
				print("Thank you for ordering a Gift Basket. Goodbye!");
				mainMenuFlag = true;
				// Break out of loop
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
