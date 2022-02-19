
/**  
* PostToPre.java - Class used to convert Postfix expressions to Prefix Expressions.    
* 
* @author  Allen Taylor
* @course CMIS 350 6382 
* @date 1/15/2022
*/

import java.util.Stack;
import java.util.StringTokenizer;

public class PostToPre {
	private String conversion;

	/**
	 * PostToPre Constructor
	 * 
	 * @param input A variable type of String
	 * @throws PostFixException 
	 */
	public PostToPre(String input) throws PostFixException {
		String formattedStr = format(input);
		this.conversion = postToPre(formattedStr);
	}

	/**
	 * This method is used to convert an expression from Postfix to Prefix.
	 * 
	 * @param exp A variable type of String
	 * @return String Returns a Prefix expression.
	 * @throws PostFixException 
	 */
	static String postToPre(String exp) throws PostFixException {
		Stack<String> operandStack = new Stack<String>();
		// tokenize the string containing the prefix expression
		StringTokenizer strTokenizer = new StringTokenizer(exp, " ");

		// while there are more tokens
		while (strTokenizer.hasMoreTokens()) {
			// get the next token
			String temp = strTokenizer.nextToken();
			// if it is an operand
			if (isOperand(temp)) {
				// push it onto the operand stack
				operandStack.push(temp);
				// else it is an operator
			} else {
				// pop two operands off of the operand stack
				String op1 = operandStack.pop();
				if (operandStack.isEmpty())
					throw new PostFixException("Empty Stack popped");
				String op2 = operandStack.pop();
				// create a string with the operator followed by two operands
				String tempStr = temp + " " + op2 + " " + op1;
				// push that string onto the operand stack
				operandStack.push(tempStr);
			}

		}
		// Obtain final result from stack.
		if (operandStack.isEmpty())
			throw new PostFixException("Empty Stack popped");
		String result = operandStack.pop();
		
		// Stack should now be empty.
		if (!operandStack.isEmpty())
			throw new PostFixException("Stack is not empty");

		// Return the final.
		return result;
	}

	/**
	 * This method is used to format the expression string and add spaces between
	 * the tokens.
	 * 
	 * @param exp A variable type of String
	 * @return String Returns a formatted string.
	 */
	static String format(String exp) {
		Character currentChar, nextChar;
		int i = 0;
		String formattedExp = "";
		while (i < exp.length()) {
			currentChar = exp.charAt(i);
			if (Character.isDigit(currentChar) || Character.isAlphabetic(currentChar)) {
				try {
					nextChar = exp.charAt(i + 1);
					if (nextChar.equals(' ')) {
						formattedExp = formattedExp + currentChar + nextChar;
					}
					if (Character.isDigit(nextChar) || Character.isAlphabetic(nextChar)) {
						formattedExp = formattedExp + currentChar;
					}
					if (isOperator(nextChar)) {
						formattedExp = formattedExp + currentChar + ' ';
					}
				} catch (StringIndexOutOfBoundsException e) {
					formattedExp = formattedExp + currentChar;
				}

			}

			if (isOperator(currentChar)) {
				formattedExp = formattedExp + currentChar + ' ';
			}
			i++;
		}
		return formattedExp;
	}

	/**
	 * This method checks for an Operand.
	 * 
	 * @param temp A variable type of String
	 * @return Boolean Returns True or False
	 */
	static boolean isOperand(String temp) {
		switch (temp) {
		case "+":
		case "-":
		case "/":
		case "*":
			return false;
		}
		return true;
	}

	/**
	 * This method checks for an Operator.
	 * 
	 * @param currentChar A variable type of Character
	 * @return Boolean Returns True or False
	 */
	static boolean isOperator(Character currentChar) {
		switch (currentChar) {
		case '+':
		case '-':
		case '/':
		case '*':
			return true;
		}
		return false;
	}

	/**
	 * Get method for conversion variable
	 * 
	 * @return String Returns Postfix to Prefix conversion string
	 */
	public String getConversion() {
		return conversion;
	}

}