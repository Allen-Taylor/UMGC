
/**
 * Week_1.java - Big O Notation Assignment
 * 
 * @author Allen Taylor
 * @course CMSC 350 6382
 * @date 1/9/2022
 */
public class Week_1 {
	/**
	 * First formula: 450n2 + 10n + 500
	 * 
	 * @return A Double data type.
	 */
	public static double functionOne(int n) {
		double output = 450 * Math.pow(n, 2) + 10 * n + 500;
		return output;

	}

	/**
	 * Second formula: 2n3
	 * 
	 * @return A Double data type.
	 */
	public static double functionTwo(int n) {
		double output = 2 * Math.pow(n, 3);
		return output;

	}

	public static void main(String[] args) {
		double funcOne;
		double funcTwo;
		int num = 10;

		System.out.println("==============================");
		System.out.printf("%-5s %-12s %s \n", "n", "Function 1", "Function 2");
		System.out.println("==============================");

		while (num <= 300) {
			funcOne = functionOne(num);
			funcTwo = functionTwo(num);

			System.out.printf("%-5s %-12s %s \n", Integer.toString(num), Double.toString(funcOne),
					Double.toString(funcTwo));
			if (funcOne < funcTwo) {
				break;
			}
			num = num + 10;
		}

	}

}
