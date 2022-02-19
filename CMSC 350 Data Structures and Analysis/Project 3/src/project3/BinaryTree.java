/**
 * This class determines defines a BinaryTree object and its methods
 */
package project3;

import java.util.Stack;

public class BinaryTree {
	private static String outputString = "";
	private static boolean PRINT_BRACKETS = true;
	private Node root;

	/**
	 * BinaryTree Constructor
	 * 
	 * @param inputStr A variable type of String
	 * @throws InvalidTreeSyntax
	 */
	public BinaryTree(String inputStr) throws InvalidTreeSyntax {
		stringCheck(inputStr);
		int len = inputStr.length();
		int start = 1;
		int end = len - 2;
		this.root = constructTree(inputStr, start, end);
	}

	/**
	 * BinaryTree Constructor
	 */
	public BinaryTree() {
	}

	/**
	 * Node Class
	 */
	static private class Node {
		char data;
		Node left;
		Node right;
	}

	/**
	 * Creates a new Node.
	 * 
	 * @param data A variable type of char
	 * @return Node Returns a Node
	 */
	static private Node newNode(char data) {
		Node node = new Node();
		node.data = data;
		node.left = null;
		node.right = null;
		return node;
	}

	/**
	 * Checks the input string for Syntax errors
	 * 
	 * @param inputStr A variable type of string
	 * @throws InvalidTreeSyntax
	 */
	private static void stringCheck(String inputStr) throws InvalidTreeSyntax {

		if (inputStr == "" || inputStr == null || inputStr.equals("")) {
			throw new InvalidTreeSyntax("The supplied string is empty.");
		}

		int leftP = countChars(inputStr, '(');
		int rightP = countChars(inputStr, ')');
		if (leftP != rightP) {
			throw new InvalidTreeSyntax("The supplied string is improperly formatted.");
		}

		for (int i = 0; i < inputStr.length(); i++) {
			Character currChar = inputStr.charAt(i);
			if (Character.isAlphabetic(currChar) || Character.isDigit(currChar) || currChar == '(' || currChar == ')') {
				// pass
			} else {
				throw new InvalidTreeSyntax("The supplied string contains invalid characters.");
			}

		}

	}

	/**
	 * Helper method used by stringCheck, used to count the occurrence of a
	 * character
	 * 
	 * @param str A variable type of string
	 * @param c   A variable type of char
	 * @return int Returns the char count
	 */
	private static int countChars(String str, char c) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char currChar = str.charAt(i);
			if (currChar == c)
				count += 1;
		}
		return count;
	}

	/**
	 * Get root node of binary tree
	 * 
	 * @return Node Returns a Node
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * Get string representation of binary tree inOrder traversal
	 * 
	 * @return String Returns a string
	 */
	public String getOutputString() {
		return outputString;
	}

	/**
	 * Set string representation of binary tree inOrder traversal
	 * 
	 * @param outputString A variable type of string
	 */
	public void setOutputString(String outputString) {
		this.outputString = outputString;
	}

	/**
	 * inOrder traversal of binary tree
	 * 
	 * @param node An object type of node
	 */
	private static void inOrder(Node node) {
		if (node == null)
			return;
		outputString = PRINT_BRACKETS ? outputString + "(" : outputString + "";
		inOrder(node.left);
		outputString = outputString + node.data + " ";
		inOrder(node.right);
		outputString = PRINT_BRACKETS ? outputString + ")" : outputString + "";
	}

	/**
	 * inOrder traversal of binary tree method, called by GUI
	 */
	public void inOrder() {
		inOrder(getRoot());
	}

	/**
	 * Helper method used by constructTree
	 * 
	 * @param str   A variable type of string
	 * @param start A variable type of int
	 * @param end   A variable type of int
	 * @return int Return the index
	 */
	private static int findIndex(String str, int start, int end) {
		// Base case
		if (start > end)
			return -1;

		Stack<Character> bracketStack = new Stack<>();

		for (int i = start; i <= end; i++) {
			char c = str.charAt(i);
			if (c == '(')
				bracketStack.add(c);
			else if (c == ')') {
				if (bracketStack.peek() == '(') {
					bracketStack.pop();
					if (bracketStack.isEmpty())
						return i;
				}
			}
		}
		return -1;
	}

	/**
	 * Constructs a binary tree from the string representation
	 * 
	 * @param str   A variable type of string
	 * @param start A variable type of int
	 * @param end   A variable type of int
	 * @return Node Returns a complete binary tree
	 */
	private static Node constructTree(String str, int start, int end) {
		// Base case
		if (start > end)
			return null;

		Node root = newNode(str.charAt(start));
		int endBracketIndex = -1;

		if (start + 1 <= end && str.charAt(start + 1) == '(') {
			endBracketIndex = findIndex(str, start + 1, end);
		}

		if (endBracketIndex != -1) {
			root.left = constructTree(str, start + 2, endBracketIndex - 1);
			root.right = constructTree(str, endBracketIndex + 2, end - 1);
		}

		return root;
	}

	/**
	 * Determines the height of the binary tree
	 * 
	 * @param node An object type of node
	 * @return int Returns the height of the binary tree
	 */
	private static int height(Node node) {
		if (node == null)
			return -1;
		int leftHeight = height(node.left);
		int rightHeight = height(node.right);
		return Math.max(leftHeight, rightHeight) + 1;
	}

	/**
	 * Determines the height of the binary tree, called by GUI
	 */
	public int height() {
		return height(getRoot());
	}

	/**
	 * Determines the number of Nodes in the binary tree
	 * 
	 * @param node An object type of node
	 * @return int Returns the count nodes within the binary tree
	 */
	private static int numNodes(Node node) {
		if (node == null)
			return 0;
		int leftNodes = numNodes(node.left);
		int rightNodes = numNodes(node.right);
		return leftNodes + rightNodes + 1;
	}

	/**
	 * Determines the number of Nodes in the binary tree, called by GUI
	 */
	public int numNodes() {
		return numNodes(getRoot());
	}

	/**
	 * Determines if the binary tree is balanced
	 * 
	 * @param node An object type of node
	 * @return boolean Returns true/false
	 */
	private static boolean isBalanced(Node node) {

		if (node == null)
			return true;

		int leftHeight = height(node.left);
		int rightHeight = height(node.right);

		if (Math.abs(leftHeight - rightHeight) >= 2)
			return false;

		if (isBalanced(node.left) && isBalanced(node.right))
			return true;

		return false;
	}

	/**
	 * Determines if the binary tree is balanced, called by GUI
	 */
	public boolean isBalanced() {
		return isBalanced(getRoot());
	}

	/**
	 * Determines if the binary tree is full
	 * 
	 * @param root An object type of node
	 * @return boolean Returns true/false
	 */
	private static boolean isFull(Node root) {

		int h = height(root);

		return numNodes(root) == (Math.pow(2, h + 1) - 1);
	}

	/**
	 * Determines if the binary tree is full, called by GUI
	 */
	public boolean isFull() {
		return isFull(getRoot());
	}

	/**
	 * Determines if the binary tree is proper
	 * 
	 * @param node An object type of node
	 * @return boolean Returns true/false
	 */
	private static boolean isProper(Node node) {

		if (node == null)
			return true;

		if (node.left == null && node.right == null)
			return true;

		if ((node.left == null) ^ (node.right == null))
			return false;

		if (isProper(node.left) && isProper(node.right))
			return true;

		return false;
	}

	/**
	 * Determines if the binary tree is proper, called by GUI
	 */
	public boolean isProper() {
		return isProper(getRoot());
	}

}
