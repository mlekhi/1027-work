import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class contains the code needed to compute a path from the entrance 
 * of the park to all the treasure chambers.
 * 
 * My approach to designing code involved spending time understanding pre-written code files like Chamber.java and [Map.java](http://Map.java) before I started going method by method to write the code.
 * I also decided to start with StackDL.java since understanding it’s doubly linked stack structure directly supported me while working on PathFinder.java. 
 * The primary challenge that I faced was difficulties in running [Pyramid.java](http://Pyramid.java) as I did not understand how the run configurations were supposed to work and what type of pop-up to expect Eclipse to show me. 
 * My issue was that when I ran the program the pop-up would either not appear or freeze due to my path code being incorrect and causing the program to immediately terminate.
 * To troubleshoot, I joined the TA sessions with Anemily, who assisted me in resolving my Eclipse configuration issues and keep everything in the right folders. 
 * Rather than designing test maps or cases, I instead asked to examine the TAs' game layout with each map to verify my movement logic. 
 * I also spent extra time understanding bestChamber and path functions to calculate if the logic displayed properly during my program’s execution.
 * 
 * @author Maya Lekhi
 */

public class PathFinder {
	
	/**
	 * represents the chambers of the Pyramid Falls National Park
	 */ 
	private Map pyramidMap;
	
	/**
	 * Constructor creates a Map object with description of park chambers
	 * @param len length of the rectangle
	 */
	public PathFinder(String fileName) {
		// using constructor input to create new map object pyramidMap
		try {
			pyramidMap = new Map(fileName);
		// handling errors that may arise with creating a map object
		} catch (FileNotFoundException e1) {
			// displaying the error message and type on the console
	        System.err.println("File not found error: " + e1.getMessage());
		} catch (IOException e2) {
			// displaying the error message and type on the console
	        System.err.println("IO error: " + e2.getMessage());
		} catch (InvalidMapCharacterException e3) {
			// displaying the error message and type on the console
	        System.err.println("Invalid map character error: " + e3.getMessage());
		}
	}
	
	/**
	 * Finds a path from the entrance to all the treasure chambers 
	 * that can be reached by satisfying the constraints
	 * @return stack of path traveled 
	 */
	public DLStack<Chamber> path() {
		// creating an empty stack
		DLStack<Chamber> pathTraveled = new DLStack<>();
		// initializing variables for the starting chamber and number of treasure items
		Chamber entrance;
		int N;
		// assigning entrance to starting chamber 
		entrance = pyramidMap.getEntrance();
		// assigning N to number of treasure items
		N = pyramidMap.getNumTreasures();
		// pushing the starting chamber into the stack
		pathTraveled.push(entrance);
		// marking the chamber as pushed
		entrance.markPushed();

		// initializing variable to keep track of number of treasures found
		int treasuresFound = 0;
		// creating loop to create path once starting chamber is given
		while (!pathTraveled.isEmpty()) {
			// peeking at the top of the stack to get the current chamber.
			Chamber current = pathTraveled.peek();
			// checking if the current chamber is a treasure chamber
			if (current.isTreasure()) {
				// updating variable to reflect that a treasure has been found
				treasuresFound++;
				// checking if the treasures found has reached the total number of treasures
				if (N == treasuresFound) {
					// exiting the while loop if all of the treasures have been found
					break;
				}
			}
			// finding the best neighboring chamber to move to using bestChamber method
			Chamber c = this.bestChamber(current);
			
			// pushing best chamber to the stack if a best chamber is found
			if (c != null) {
				pathTraveled.push(c);
				// marking the chamber as pushed
				c.markPushed();
			// popping the top chamber and marking it as popped if there is no best chamber found
			} else {
				Chamber popped = pathTraveled.pop();
				popped.markPopped();
			}
		}
		// returning the path traveled stack
		return pathTraveled;
	}
	
	/**
	 * Accessor method to get the pyramid map value
	 * @return pyramid map value
	 */
	public Map getMap() {
		return pyramidMap;
	}
	
	/**
	 * Checks if the current chamber is dim
	 * @param currentChamber	chamber being evaluated to determine if dim
	 * @return boolean representing whether the chamber is dim or not
	 */
	public boolean isDim(Chamber currentChamber) {
		// assigning the currentChamber to a temporary variable
		Chamber temp = currentChamber;
		// creating a neighbour variable to represent the 6 neighbours of the current chamber
		Chamber neighbour;
		
		// verifying if currentChamber is unlit, unsealed, and not null
		if (currentChamber != null && !currentChamber.isLighted() && !currentChamber.isSealed()) {
			// cycling through the  neighbours of the current chamber
			for (int i = 0; i < 6; i++) {
				// finding the according neighbour to the current chamber
				neighbour = temp.getNeighbour(i);
				// checking if the neighbour is lit if not null
				if (neighbour != null) {
					if (neighbour.isLighted()) {
						// returning true if at least one neighbour is lit as the current chamber will be dim
						return true;
					}
				}
				// re-updating temp to the current chamber to re-check its neighbours value
				temp = currentChamber;
			}
		}
		// returning false if no neighbouring chambers are lit
		return false;
	}
	
	/**
	 * Selects the best chamber to move to from the current chamber
	 * @param currentChamber	the current chamber that the user is hoping to determine the best path based on
	 * @return the best neighbouring chamber from the current to move to
	 */
	public Chamber bestChamber(Chamber currentChamber) {		
		// assigning the currentChamber to a temporary variable
		Chamber temp = currentChamber;
		// creating a neighbour variable to represent the 6 neighbours of the current chamber
		Chamber neighbour;
		
		// cycling through the neighbours of the current chamber to find unmarked treasure chamber
		for (int i = 0; i < 6; i++) {
			// finding the according neighbour to the current chamber
			neighbour = temp.getNeighbour(i);
			// verifying that neighbour is not null
			if (neighbour != null) {
				// checking if the neighbour is unmarked treasure
				if (!neighbour.isMarked()) {
					if (neighbour.isTreasure()) {
						// returning the neighbouring unmarked treasure chamber with the smallest index
						return neighbour;
					}
				} 
			}
			// re-updating temp to the current chamber to re-check its neighbours value
			temp = currentChamber;
		}
		
		// cycling through the neighbours of the current chamber to find unmarked lit chamber
		for (int i = 0; i < 6; i++) {
			// finding the according neighbour to the current chamber
			neighbour = temp.getNeighbour(i);
			// verifying that neighbour is not null
			if (neighbour != null) {
				// checking if the neighbour is unmarked lit
				if (!neighbour.isMarked()) {
					if (neighbour.isLighted()) {
						// returning the neighbouring unmarked lit chamber with the smallest index
						return neighbour;
					}
				} 
			}
			// re-updating temp to the current chamber to re-check its neighbours value
			temp = currentChamber;
		}
		
		// cycling through the neighbours of the current chamber to find unmarked dim chamber
		for (int i = 0; i < 6; i++) {
			// finding the according neighbour to the current chamber
			neighbour = temp.getNeighbour(i);
			// verifying that neighbour is not null
			if (neighbour != null) {
				// checking if the neighbour is unmarked dim
				if (!neighbour.isMarked()) {
					if (this.isDim(neighbour)) {
						// returning the neighbouring unmarked dim chamber with the smallest index
						return neighbour;
					}
				} 
			}
			// re-updating temp to the current chamber to re-check its neighbours value
			temp = currentChamber;
		}
		
		// returning null if there's no unmarked treasure, lighted or dim chamber
		return null;
	}
}
