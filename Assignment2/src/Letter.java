/**
 * This class represents a letter and contains its respective label representing it's status
 * @author Maya Lekhi
 */

public class Letter {
	/**
	 * Letter character value
	 */
	private char letter;
	/**
	 * Label of letter indicating it's use
	 */
	private int label;
	
	// constants for label
	/**
	 * Integer value representing if letter is unset
	 */
	private final int UNSET = 1;
	/**
	 * Integer value representing if letter is unused
	 */
	private final int UNUSED = 2;
	/**
	 * Integer value representing if letter is used
	 */
	private final int USED = 3;
	/**
	 * Integer value representing if letter is correct
	 */
	private final int CORRECT = 4;
	
	/**
	 * Constructor creates a letter object with and sets letter and label value
	 * @param c	letter character value
	 */
	public Letter(char c) {
		label = UNSET;
		letter = c;
	}
		
	/**
	 * Verifies if otherObject is of letter class and if attributes are equal
	 * @param otherObject	object that the letter object will be compared to
	 */
	public boolean equals(Object otherObject) {
		// checking whether otherObject is of letter class
		if (otherObject instanceof Letter) {
			// checking whether letter attributes are the same
			if (this.letter == ((Letter) otherObject).letter) {
				return true;
			}
			// returning false if both conditions are not met
			return false;
		} else {
			return false;
		}
	}
	
	/**
	 * Returns decorator indicating the letter's status
	 * @return string indicating the letter label
	 */
	public String decorator() {
		if (label == CORRECT) {
			// returning "!" if the letter is correct
			return "!";
		} else if (label == USED) {
			// returning "+" if the letter is used
			return "+";
		} else if (label == UNUSED) {
			// returning "-" if the letter is unused
			return "-";
		} else {
			// returning " " if the letter is unset
			return " ";
		}
	}
	
	/**
	 * Gives representation of letter and label which using decorator method
	 * @return string in format dCd where C is the letter and d is the decorator
	 */
	@Override
	public String toString() {
		return this.decorator() + letter + this.decorator();
	}
	
	/**
	 * Setter to update label to unused
	 */
	public void setUnused() {
		label = UNUSED;
	}
	
	/**
	 * Setter to update label to used
	 */
	public void setUsed() {
		label = USED;
	}
	
	/**
	 * Setter to update label to correct
	 */
	public void setCorrect() {
		label = CORRECT;
	}
	
	/**
	 * Accessor method to get if the letter is unused
	 * @return boolean indicating if the letter is unused or not
	 */
	public boolean isUnused() {
		// checking if label is set to unused
		if (label == UNUSED) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	* produces array of objects of letter class from the string s
	* @param s	string input for characters to be stored in array
	* @return array of letter objects in the same order as characters in string s
	*/
	public static Letter[] fromString(String s) {
		// converting s' char values into a character array
		char[] charArray = s.toCharArray();
		// creating a new array of letter objects the same length as the character array
		Letter[] letterArray = new Letter[charArray.length];
		// iterating through the character array's length
		for (int i = 0; i < charArray.length; i++) {
			// assigning each value of letter array to be letter objects based on s' character array
			letterArray[i] = new Letter(charArray[i]);
		}
		// returning the array of letter objects in order based on string s
		return letterArray;
	}
}
