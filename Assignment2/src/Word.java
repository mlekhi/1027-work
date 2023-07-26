/**
 * This class represents a word in the game comprised of letters represented by letter objects
 * 
 * @author Maya Lekhi
 */

public class Word {
	/**
	 * Reference to first node of type Letter in the linked list representing this word
	 */
	private LinearNode<Letter> firstLetter = null;
	
	/**
	 * Constructor creating the word object from letter object array
	 * @param letters	array of letter objects comprising the word
	 */
	public Word(Letter[] letters) {
		// initializing firstLetter to the first letter in the array
		firstLetter = new LinearNode<Letter>(letters[0]);
		// creating a linear node 'current' to indicate the current node
		LinearNode<Letter> current = firstLetter;
		
		// iterating over remaining letters in the word
		for (int i = 1; i < letters.length; i++) {
			// creating linked list storing letters in order on each node
			LinearNode<Letter> node = new LinearNode<Letter>(letters[i]);
			// creating the next node and updating current accordingly
			current.setNext(node);
			current = node;
		}
	}
	
	/**
	 * Formats and returns string based on the word object
	 * @return formatted string displaying word letters and their label status
	 */
	public String toString() {
		// creating word string 
		String word = "Word: ";
		// creating a linear node 'current' to indicate the current node
		LinearNode<Letter> current = firstLetter;
		
		// looping through the node until we reach the end, indicated by when current is null
		while (current != null) {
			// adding the letter information to the word string 
			word = word + current.getElement().toString() + " ";
			// updating current to the next node
			current = current.getNext();
		}
		// returning the formatted word string
		return word;
	}
	
	/**
	 * Private helper method to get the head of the linear node
	 * @return the head or first letter object of the word
	 */
    private LinearNode<Letter> getHead() {
        return firstLetter;
    }
	
	/**
	 * Method to label word letters based on the mystery word given
	 * @param mystery	the mystery word that the word object will be labeled based on
	 * @return boolean indicating if the whole word matches the mystery word or not
	 */
	public boolean labelWord(Word mystery) {
		// initializing current variables as linked list heads of their respective words
		LinearNode<Letter> currentMystery = mystery.getHead();
		LinearNode<Letter> currentLetter = firstLetter;
		// boolean to store whether the whole word matches or not
		boolean allCorrect = true;
		
		// checking if letter is unused
	    while (currentLetter != null) {
	    	// setting all letters as unused as default
	        currentLetter.getElement().setUnused();
			// updating current letter to the next node
	    	currentLetter = currentLetter.getNext();
	    }
		
	    // resetting current letter to be the first letter of the word
		currentLetter = firstLetter;
	    
		// checking if letter is used
	    while (currentMystery != null) {
	    	// resetting current to first letter 
	    	currentLetter = firstLetter;
			// looping through the linked list nodes until we reach the end/null
		    while (currentLetter != null) {
		    	// seeing if the current letter matches any letter in the mystery word
		    	if (currentLetter.getElement().equals(currentMystery.getElement())) {
		    		// setting current letter value to used
		            currentLetter.getElement().setUsed();
		        }
		    	// moving to examine the next letter in the word
		    	currentLetter = currentLetter.getNext();
		    }
		    // moving to cross reference with the next letter in the mystery word
	        currentMystery = currentMystery.getNext();
	    }
		
	    // resetting current letter to be the first letter of the word
		currentLetter = firstLetter;
	    // resetting current mystery to be the first letter of the mystery word
		currentMystery = mystery.getHead();
	    
		// checking if letter is correct
	    while (currentMystery != null && currentLetter != null) {
	    	// seeing if the current letter matches the letter in the mystery word at that exact location
	    	if (currentLetter.getElement().equals(currentMystery.getElement())) {
	    		// setting current letter value to correct
	            currentLetter.getElement().setCorrect();
	        // updating the allCorrect variable to false if any letters are not correct
	        } else {
                allCorrect = false;
	        }
	    	// moving to examine the next letters in the word and mystery word
	    	currentLetter = currentLetter.getNext();
	        currentMystery = currentMystery.getNext();
	    }
		
		// returning whether the whole word matches or not
		return allCorrect;
	}
}
