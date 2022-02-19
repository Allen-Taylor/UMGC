/**
 * This class determines defines a Polynomial object and its methods
 */
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class Polynomial implements Comparable<Polynomial>, Iterable<Object> {

	private SinglyLinkedList<Term> polynominal;
	
	/**
	 * Polynomial Constructor
	 * @param poly A variable type of String
	 */
	public Polynomial(String poly) throws InvalidPolynomialSyntax {

		this.polynominal = Tokenize(poly);
	}

	/**
	 * Tokenize input string into a SinglyLinkedList of Terms
	 * @param poly A variable type of String
	 * @return SinglyLinkedList<Term> Returns a SinglyLinkedList<Term>
	 */
	public SinglyLinkedList<Term> Tokenize(String poly) throws InvalidPolynomialSyntax {

		StringTokenizer strTokenizer = new StringTokenizer(poly, " "); // Tokenize String
		SinglyLinkedList<Term> SSL = new SinglyLinkedList<>(); // Create temp SSL

		Double coefficient;
		Integer exponent;
		Term term;

		while (strTokenizer.hasMoreTokens()) { // loop
			coefficient = Double.valueOf(strTokenizer.nextToken()); // cast string to double to get coefficient
			exponent = Integer.valueOf(strTokenizer.nextToken()); // cast string to double to get exponent
			if (coefficient < 0 || exponent < 0) { // check for negative numbers
				throw new InvalidPolynomialSyntax("The supplied string contains coefficients or exponents of an improper type. ");
			} else {
				term = new Term(coefficient, exponent); // create temp term
				Node<Term> node = new Node<Term>(term); // create node for SSL
				SSL.add(node); // add node to SSL
			}
		}

		if (checkDescendingTerms(SSL) == true) { // check if terms are descending as required
			throw new InvalidPolynomialSyntax("Exponents fail to be listed in strictly descending order.");
		} else {
			return SSL;
		}
	}

	/**
	 * Tokenize input string into a SinglyLinkedList of Terms
	 * @param SSL A variable type of SinglyLinkedList<Term>
	 * @return boolean Returns true/false>
	 */
	private boolean checkDescendingTerms(SinglyLinkedList<Term> SSL) {

		List<Integer> polyExps = new ArrayList<Integer>();

		Term term;
		Iterator<Term> Iterator = SSL.iterator();
		while (Iterator.hasNext()) {
			term = (Term) Iterator.next();
			polyExps.add(term.getExponent()); // Integer
		}

		Integer firstTermExp = polyExps.get(0);
		Integer secondTermExp = polyExps.get(1);
		Integer thirdTermExp = polyExps.get(2);

		if (thirdTermExp > firstTermExp || thirdTermExp > secondTermExp) { // if third term is larger than first OR second, return true
			return true;
		} else if (secondTermExp > firstTermExp) { // if second term is larger than first , return true
			return true;
		} else { // terms are descending
			return false;
		}
	}

	/**
	 * Get Polynominal 
	 * @return SinglyLinkedList<Term> Returns a SinglyLinkedList<Term>
	 */
	public SinglyLinkedList<Term> getPolynominal() {
		return polynominal;
	}

	
	/**
	 * Compare two Polynomials objects
	 * @param otherPoly An object type of Polynomial
	 * @return int Returns 0, 1 or -1
	 */
	@Override
	public int compareTo(Polynomial otherPoly) {

		int firstTerm, secondTerm, thirdTerm;

		// Polynomial 1
		List<Integer> polyExps1 = new ArrayList<Integer>();
		List<Double> polyCoeffs1 = new ArrayList<Double>();
		Term term1;
		Iterator<?> firstIterator = iterator();
		while (firstIterator.hasNext()) {
			term1 = (Term) firstIterator.next();
			polyExps1.add(term1.getExponent()); // Integer
			polyCoeffs1.add(term1.getCoefficient()); // Double
		}

		// Polynomial 2
		Polynomial polynomail2 = otherPoly;
		List<Integer> polyExps2 = new ArrayList<Integer>();
		List<Double> polyCoeffs2 = new ArrayList<Double>();
		Term term2;
		Iterator<?> secondIterator = polynomail2.iterator();
		while (secondIterator.hasNext()) {
			term2 = (Term) secondIterator.next();
			polyExps2.add(term2.getExponent()); // Integer
			polyCoeffs2.add(term2.getCoefficient()); // Double
		}

		firstTerm = compareTerms(polyCoeffs1.get(0), polyCoeffs2.get(0), polyExps1.get(0), polyExps2.get(0));
		secondTerm = compareTerms(polyCoeffs1.get(1), polyCoeffs2.get(1), polyExps1.get(1), polyExps2.get(1));
		thirdTerm = compareTerms(polyCoeffs1.get(2), polyCoeffs2.get(2), polyExps1.get(2), polyExps2.get(2));

		if (firstTerm != 0) {
			return firstTerm;
		}

		if (secondTerm != 0) {
			return secondTerm;
		}

		if (thirdTerm != 0) {
			return thirdTerm;
		}

		return 0;
	}

	/**
	 * Returns all exponents for a Polynomial
	 * @param otherPoly An object type of Polynomial
	 * @return List<Integer> Returns a List of Integers
	 */
	public List<Integer> getExponents() throws InvalidPolynomialSyntax {

		// Polynomial 1
		List<Integer> polyExps1 = new ArrayList<Integer>();
		Term term1;
		Iterator<?> firstIterator = iterator();
		while (firstIterator.hasNext()) {
			term1 = (Term) firstIterator.next();
			polyExps1.add(term1.getExponent()); // Integer
		}

		return polyExps1;
	}

	/**
	 * Compare two Polynomials terms
	 * @param Coeff A variable type of Double
	 * @param otherCoeff A variable type of Double
	 * @param Exp A variable type of Integer
	 * @param otherExp A variable type of Integer
	 * @return int Returns 0, 1 or -1
	 */
	private int compareTerms(Double Coeff, Double otherCoeff, Integer Exp, Integer otherExp) {
		if (Exp != otherExp) {
			if (otherExp > Exp) {
				return -1;
			} else if (otherExp < Exp) {
				return +1;
			}
		} else if (Coeff != otherCoeff) {
			if (otherCoeff > Coeff) {
				return -1;
			} else if (otherCoeff < Coeff) {
				return +1;
			}
		}
		return 0;
	}

	
	/**
	 * Converts Polynomial to String
	 * @return String A formatted string
	 */
	@Override
	public String toString() {

		// Polynomial 1
		List<Integer> polyExps = new ArrayList<Integer>();
		List<Double> polyCoeffs = new ArrayList<Double>();
		Term term;
		Iterator<?> Iterator = iterator();
		while (Iterator.hasNext()) {
			term = (Term) Iterator.next();
			polyExps.add(term.getExponent()); // Integer
			polyCoeffs.add(term.getCoefficient()); // Double
		}

		Double firstTermCoeff = polyCoeffs.get(0);
		Double secondTermCoeff = polyCoeffs.get(1);
		Double thirdTermCoeff = polyCoeffs.get(2);

		Integer firstTermExp = polyExps.get(0);
		Integer secondTermExp = polyExps.get(1);
		Integer thirdTermExp = polyExps.get(2);

		String first = termToString(firstTermCoeff, firstTermExp);
		String second = termToString(secondTermCoeff, secondTermExp);
		String third = termToString(thirdTermCoeff, thirdTermExp);
		String fullStr;

		if (second == "") {
			fullStr = first + " + " + third;
		} else if (third == "") {
			fullStr = first + " + " + second;
		} else {
			fullStr = first + " + " + second + " + " + third;
		}

		return fullStr;
	}

	/**
	 * Helper method for toString
	 * @return String A term as a string
	 */
	private String termToString(Double coeff, Integer exp) {
		DecimalFormat format = new DecimalFormat();
		format.setDecimalSeparatorAlwaysShown(false);

		String result;
		if (coeff == 0) {
			result = "";
		} else {
			if (exp == 1) {
				result = format.format(coeff) + "x";
			} else if (exp == 0) {
				result = format.format(coeff) + "";
			} else {
				result = format.format(coeff) + "x^" + exp;
			}
		}
		return result;
	}

	/**
	 * Iterator
	 * @return Iterator An iterator object that implements Iterable
	 */
	@Override
	public Iterator iterator() {
		return this.polynominal.iterator();
	}

	/**
	 * Term Class
	 */
	private class Term {
		private Double coefficient;
		private Integer exponent;

		public Term(Double coefficient, Integer exponent) {
			this.coefficient = coefficient;
			this.exponent = exponent;
		}

		public Double getCoefficient() {
			return coefficient;
		}

		public Integer getExponent() {
			return exponent;
		}

	}
	
	/**
	 * Node Class
	 */
	private class Node<T> {
		T data;
		Node<T> next;

		Node(T data) {
			this.data = data;
			this.next = null;
		}
	}

	/**
	 * SinglyLinkedList Class
	 */
	public class SinglyLinkedList<T> implements Iterable<T> {

		Node<Term> head;
		private int length = 0;

		SinglyLinkedList() {
			this.head = null;
		}

		void add(Node<Term> node) {
			Node<Term> temp = node;
			if (this.head == null) {
				head = temp;
			} else {
				Node<Term> X = head;
				while (X.next != null) {
					X = X.next;
				}
				X.next = temp;
			}
			length++;
		}

		@Override
		public Iterator<T> iterator() {
			return new SSLIterator();
		}

		private class SSLIterator implements Iterator<T> {
			private Node<?> curr = head;

			public boolean hasNext() {
				return curr != null;
			}

			public T next() {
				T data = (T) curr.data;
				curr = curr.next;
				return data;
			}
		}
	}

}