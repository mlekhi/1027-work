package BridgeBuilderAdv;

/**
 * This class represents a game board for the bridge builder game. 
 * Its methods include displaying the board, placing tokens, retrieving board size, and determining wins/ties.
 * 
 * My approach to designing the code was to follow a methodical process of working on one class at a time, method by method. 
 * This organized my workload into manageable chunks.
 * My primary challenge was setting up the Gameboard and Display Gameboard methods. 
 * Since I am relatively new to working with 2D arrays, I was initially unsure of how to format my board variable declaration, which I resolved by referring to my class notes. 
 * I was also unsure of if I should include the row/column headers in the Gameboard or Display Gameboard method. 
 * I ended up adding the row/column headers only to the display function since they had no relevance to the rest of the gameplay and would complicate the Player and Engineer methods if it was part of the gameboard. 
 * After getting board display working, everything was smooth sailing as I could test other methods easily thanks to the console display.
 * To test my code throughout the process, I would play with the specific functionality I was designing on the console. 
 * I ran through the suggested testcase checklist and taught my friends to play the game for additional perspectives and to confirm the code functionality.
 * 
 * @author Maya Lekhi
 */

public class GameBoard {
	/**
	 * Board Display
	 */
	private char[][] board;
	/**
	 * Size of Board
	 */
	private int size;

	/**
	 * Constructor creates an board with '.' to denote empty spaces
	 * @param size	size of game board
	 */
	public GameBoard(int size) {
		// assigning the board and size variables
		this.size = size;
		board = new char [size][size];
		
		// iterating through the 2D board array to access its elements
		for (int i= 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// assigning each space on the board to the empty '.' symbol
				board[i][j] = '.';
			}
		}
	}
  
	/**
	 * Places the given token at the specified row and column on the game board
	 * @param row 	row where token will be placed
	 * @param col	column where token will be placed
	 * @param token	token character value
	 */
	public void placeToken(int row, int col, char token) {
		// iterating through the game board rows
		for (int i= 0; i < size; i++) {
			// matching the iterated row to the row parameter
			if (i == row) {
				// iterating through the game board columns
				for (int j = 0; j < size; j++) {
					// matching the iterated column to the col parameter
					if (j == col) {
						// assigning the board value at the given row and column to the token parameter
						board[i][j] = token;
					}
				}
			}
		}
	}
  
	/**
	 * Checks whether the specified position on the board is empty
	 * @param row	row to be checked for empty space
	 * @param col	column to be checked for empty space
	 * @return indication of if space is empty (true) or full (false)
	 */
	public boolean isPositionEmpty(int row, int col) {
		// checking if the specified position is equivalent to an empty indicator
		if (board[row][col] == '.') {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Accessor method to return the size of the game board
	 * @return size of the game board
	 */
	public int getSize() {
		// returning the size of the game board
		return size;
	}
  
	/**
	 * Display game board on console, including row and column numbers
	 */
	public void displayBoard() {
		// printing space to align letters to their respective columns
		System.out.print("  ");
		// printing letters in ascending order to identify columns
		for (int i = 0; i < size; i++) {
			System.out.print((char) ('A' + i) + " ");
		}
		// printing a line to separate the header row from the following rows
		System.out.println();
		// iterating through 2D array rows
		for (int i = 0; i < size; i++) {
			// printing the row number to identify each row
			System.out.print(i + " ");
			// iterating through 2D array elements
			for (int j = 0; j < size; j++) {
				// printing the board elements
				System.out.print(board[i][j]);
				System.out.print(" ");
			}
			// adding a new line after each row to separate them
			System.out.println();
		}
	}
  
	/**
	 * Check if the player has won the game in left-right, bottom-top, or diagonal
	 * Awards points: 1 for left-right, 2 for bottom-top, 3 for diagonal, and 0 for none
	 * @param player	player object
	 * @return points that the player was awarded based on the win direction
	 */
	public int checkForWinDirection(Player player) {
		// initializing points variable to log the points obtained based on the win type
		int points = 0;
	  
		// determining if there was a horizontal win
		for (int i = 0; i < size; i++) {
			boolean horWin = true;
			for (int j = 0; j < size; j++) {
				// checking if the players token are the elements in the row
				if (board[i][j] != player.getToken()) {
					// setting horizontal win to false if the players tokens don't fill the row
					horWin = false;
					break;
				}
			}
			// assigning points to 1 if there is a horizontal win
			if (horWin) {
				points = 1;
				break;
			}
		}
	 
		// determining if there was a vertical win
		for (int i = 0; i < size; i++) {
			boolean vertWin = true;
			for (int j = 0; j < size; j++) {
				// checking if the players token are the elements in the column
				if (board[j][i] != player.getToken()) {
					// setting vertical win to false if the players tokens don't fill the column
					vertWin = false;
					break;
				}
			}
			// assigning points to 2 if there is a vertical win
			if (vertWin) {
				points = 2;
				break;
			}
		}

		// determining if there was a diagonal top-left to bottom-right win
		boolean diagWin = true;
		for (int i = 0; i < size; i++) {
			// checking if the players token are the elements in the diagonal
			if (board[i][i] != player.getToken()) {
				// setting diagonal win to false if the players tokens don't fill the diagonal
				diagWin = false;
			}
		}
		// assigning points to 3 if there is a diagonal win
		if (diagWin) {
			points = 3;
		}

		// returning the points value to communicate win information
		return points;
  	}

	/**
	 * Check whether the game board is full, indicating a tie
	 * @return indication of if game board is full (true) or not (false)
	 */
	public boolean checkForTie() {
		// iterating through the 2D board array to access its elements
		for (int i= 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				// checking if the board value at every space is equivalent to the empty indicator
				if (board[i][j] == '.') {
					// returning that there is no tie if there are any empty spaces on the board
					return false;
				}
			}
		}
		return true;
	}
}