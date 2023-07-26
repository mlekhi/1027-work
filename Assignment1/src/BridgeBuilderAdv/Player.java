package BridgeBuilderAdv;

/**
 * This class represents a player for the bridge builder game. 
 * Its methods include marking a move, retrieving the token, and adding to and calculating the score.
 * @author Maya Lekhi
 */

public class Player {
	/**
	 * Token character for player
	 */
	private char token;
	/**
	 * Player game score
	 */
	private int score;
  
	/**
	 * Constructor assigns token to '+' and sets the default starting score to 0
	 */
	public Player() {
		// assigning the token and score variables
		token = '+';
		score = 0;
	}
	
	/**
	 * Places the player's token at the specified row and column on the game board
	 * @param board game board that move will be displayed on
	 * @param row	row where token will be placed
	 * @param col	column where token will be placed
	 */
	public void makeMove(GameBoard board, int row, int col) {
		// using the placeToken method to set the token on the appropriate row & column on the board
		board.placeToken(row, col, token);
	}
	
	/**
	 * Accessor method to return player's token
	 * @return token character
	 */
	public char getToken() {
		// returning the token character
		return token;
	}
	
	/**
	 * Accessor method to return player's score
	 * @return score value
	 */
	public int getScore() {
		// returning the score value
		return score;
	}
	
	/**
	 * Increases the player's score by the specified increment
	 * @param increment	value to increase the score by
	 */
	public void addScore(int increment) {
		// adding the current score and	 increment value to update the score value
		score = score + increment;
	}
}