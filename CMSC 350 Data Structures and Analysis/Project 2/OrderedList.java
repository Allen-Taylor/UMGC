/**
 * This class determines if a List of Polynomials is Strong Ordered. 
 */
import java.util.List;


public class OrderedList {
	
	/**
	 * Checks for Strong Ordering within a list of Polynomials
	 * @param list A variable type of List<T>
	 * @return boolean Returns true/false
	 */
    public static <T extends Comparable<? super T>> boolean checkSorted(List<T> list) {
        boolean isSorted = true;

        for (int i = list.size() - 1; i > 0; i--) {
            T current = list.get(i);
            if (!checkSorted(list, current)) {
                isSorted = false;
            }
        }
        return isSorted;
    }

	/**
	 * Checks for Strong Ordering within a list of Polynomials
	 * @param list A variable type of List<T>
	 * @param T A variable type of T
	 * @return boolean Returns true/false
	 */
    private static <T extends Comparable<? super T>> boolean checkSorted(List<T> list, T current) {
        T currentValue = list.get(list.indexOf(current));
        T nextValue = list.get(list.indexOf(current) - 1);

        if (nextValue != null) {
            return currentValue.compareTo(nextValue) >= 0;
        }
        return true;
    }
}
