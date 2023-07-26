/**
 * This class extends the letter class with additional behaviour and attributes
 * 
 * @author Maya Lekhi
 */

public class ExtendedLetter extends Letter {
	/**
	 * Contains value of extended letter
	 */
	private String content;
	/**
	 * Contains if extended letter object is related to other extended letter objects
	 */
	private boolean related;
	/**
	 * Contains integer representing the family of the extended letter
	 */
	private int family;
	/**
	 * Constant value for values not belonging to a given family
	 */
	private final int SINGLETON = -1;

	/**
	 * Constructor creates an extended letter based on string s and initializes instance variables
	 * @param s	string representing extended letter content
	 */
	public ExtendedLetter(String s) {
		// initializing super value to an arbitrary character
		super(' ');
		content = s;
		related = false;
		family = SINGLETON;
	}
	
	/**
	 * Constructor creates an extended letter based on string s and family value and initializes instance variables
	 * @param s	string representing extended letter content
	 * @param fam	integer representing the family of the extended letter
	 */
	public ExtendedLetter(String s, int fam) {
		// initializing super value to an arbitrary character
		super(' ');
		content = s;
		related = false;
		family = fam;
	}
	
	/**
	 * Method to verify if other is an ExtendedLetter instance and if their content values are the same
	 * @param other	given object of a class
	 * @return boolean indicating whether the classes and content values are equal
	 */
	public boolean equals(Object other) {
		// checking if other is an instance of the ExtendedLetter class
		if (other instanceof ExtendedLetter) {
			
			// checking if the family values are equal and if so, setting this related value to true
			if (((ExtendedLetter) other).family == this.family) {
				this.related = true;
			}
			// returning if content values are equal
			return (((ExtendedLetter) other).content).equals(this.content);
		}
		return false;
	}
	
	/**
	 * Overridden method that gives a string representation of this ExtendedLetter object
	 * @return string representation of ExtendedLetter object
	 */
	@Override
	public String toString() {
		// checking if object is unused and has a true related value
		if (this.isUnused() && this.related) {
			// returning string with dot decorator
			return "." + this.content + ".";
		} else {
			// returning string with decorator from Letter class
			return decorator() + this.content + decorator();
		}
	}
	
	/**
	 * Creates array of ExtendedLetter objects based on array content from parameter
	 * @param content	string array representing letter content
	 * @param codes		integer representing family values for each letter
	 * @return letter array with code and content values
	 */
	public static Letter[] fromStrings(String[] content, int[] codes) {
		// creating new letter array to store content from string array as letter objects
	    Letter[] letters = new Letter[content.length];

	    // looping through content array
		for (int i = 0; i < content.length; i++) {
			// initializing an ExtendedLetter object with only the content value if code is null in letter array
			if (codes == null) {
				letters[i] = new ExtendedLetter(content[i]);
			// initializing an ExtendedLetter object with content and code value in letter array
			} else {
				letters[i] = new ExtendedLetter(content[i], codes[i]);
			}
		}
		
		return letters;
	}
}
