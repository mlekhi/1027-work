/**
 * This class is a central repository for information about a WordLL game
 * It stores a mystery word and all word guesses tried so far
 * It keeps a history of the past word guesses in a linked list structure
 * 
 * I designed my code using a methodical approach involving identifying all the necessary methods and classes and fulfilling the functionality for each of them. 
 * I found it easiest to go through the methods in the order outlined in the assignment and taking breaks if I got stuck.
 * However, while testing the code, I faced the challenge of struggling to pass the 12th test case. 
 * After using print statements to finalize what methods the word was being run through, I found that my labelWord function did not have the correct values run through the ExtendedLetter equals() method, so their related value was not correctly updated. 
 * After consulting with the course TAs, I discovered that I was running the mystery word as a parameter in the equals function instead of the guess word, which led to the issue.
 * To test the code, I created my own test cases based on the TestWordLL.java file, as well as playing the game using the WordLLExamples.java and words.txt functionality.
 * 
 * @author Maya Lekhi
 */

public class WordLL {
	/**
	 * Word object the user tries to guess
	 */
	private Word mysteryWord;
	/**
	 * Linked list of Word objects displaying the user's previous guesses
	 */
	private LinearNode<Word> history;
	
	/**
	 * Constructor creating the WordLL object from mystery Word object
	 * @param mystery	Word object the user tries to guess
	 */
	public WordLL(Word mystery) {
		history = null;
		mysteryWord = mystery;
	}
	
	/**
	 * Method taking a Word argument to test against the games’ mystery word and update labels accordingly
	 * @param guess	the guess for determining the mystery word
	 * @return boolean indicating if the whole guess word matches the mystery word or not
	 */
	public boolean tryWord(Word guess) {
		// creating a new linear node with the guess information 
		LinearNode<Word> temp = new LinearNode<Word>(guess);
		// updating history linked list with the new guess node
		temp.setNext(history);
		history = temp;
		
		// updates labels of letters in guess and returns if they're the same as the mystery word
		return guess.labelWord(mysteryWord);
	}
	
	/**
	 * Method creating string representation of past guesses
	 * @return formatted string of previous guesses with most recent guess first
	 */
	public String toString() {
		// creating a string to update for the method to return
		String toPrint = "";
		// setting the current node to the first node in history
		LinearNode<Word> current = history;
		
		// looping through history linked list nodes until we reach the end/null
		while (current != null) {
			// updating toPrint with the element string output and fetching the next node
			toPrint = toPrint + current.getElement().toString() + "\n";
			current = current.getNext();
		}
		return toPrint;
	}
}
