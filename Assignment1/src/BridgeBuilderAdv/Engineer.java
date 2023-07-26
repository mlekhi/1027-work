package BridgeBuilderAdv;

import java.util.Random;

/**
 * This class represents a the rival engineer for the bridge builder game. 
 * Its methods include marking a move based on selected difficulty and retrieving the token.
 * @author Maya Lekhi
 */

public class Engineer {
	/**
	 * Random number generator
	 */
	private Random rand = new Random();
	/**
	 * Token character for engineer
	 */
	private char token;
	/**
	 * Hard mode indicator
	 */
	private boolean hardMode;
 
	/**
	 * Constructor assigns token to '0' and sets the hard mode based on the player's selection
	 * @param hardMode	the player's game difficulty selection
	 */
	public Engineer(boolean hardMode) {
		// assigning the token and hardMode variables
		token = '0';
		this.hardMode = hardMode;
	}
  
	/**
	 * Dictates the engineer's move pattern based on the game mode selection
	 * Easy mode makes the engineer select a random unfilled spot to place its token in
	 * Hard mode makes the engineer select the value right of the player's last position
	 * Should it be full, the engineer selects the topmost empty space in the same column
	 * @param board 		game board that move will be displayed on
	 * @param playerLastRow	row where player's token was last placed
	 * @param playerLastCol	column where player's token was last placed
	 */
	public void makeMove(GameBoard board, int playerLastRow, int playerLastCol) {
		// easy mode token placing logic
		if (!hardMode) {
			// setting the row and column as a random integer within the range of the board size
			int row = rand.nextInt(board.getSize());
			int col = rand.nextInt(board.getSize());
			// replacing the row and column values with different random integers if spot is taken
			while (!board.isPositionEmpty(row, col)) {
				row = rand.nextInt(board.getSize());
				col = rand.nextInt(board.getSize());
			}
			// placing the token on the random position
			board.placeToken(row, col, token);
		// hard mode token placing logic
		} else {
			// verifying if the position to the right of the player's last move is available
			if ((playerLastCol + 1) < board.getSize() && board.isPositionEmpty(playerLastRow, playerLastCol + 1)) {
				// placing the token to the right of the player's last move if position is free
				board.placeToken(playerLastRow, playerLastCol + 1, token);
			} else {
				// setting a variable to check if the column is full
				boolean colFull = true;
				// iterating through the board rows
				for (int i = 0; i < board.getSize(); i++) {
					// checking if the position is empty at each row in the player's last column
					if (board.isPositionEmpty(i, playerLastCol)) {
						// placing the token and marking that the column is not full if the position is empty
						board.placeToken(i, playerLastCol, token);
						colFull = false;
						break;
					}
				}
				// if the column is full, selecting a random position on the board to place the token
				if (colFull) {
					// setting the row and column as a random integer within the range of the board size
					int row = rand.nextInt(board.getSize());
					int col = rand.nextInt(board.getSize());
					// replacing the row and column values with different random integers if spot is taken
					while (!board.isPositionEmpty(row, col)) {
						row = rand.nextInt(board.getSize());
						col = rand.nextInt(board.getSize());
					}
					// placing the token on the random position
					board.placeToken(row, col, token);
				}
			}
		}
	}
	
	/**
	 * Accessor method to return engineer's token
	 * @return token character
	 */
	public char getToken() {
		// returning the token character
		return token;
	}    
}