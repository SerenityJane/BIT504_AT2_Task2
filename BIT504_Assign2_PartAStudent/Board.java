import java.awt.Color;
import java.awt.Graphics;

public class Board {
	// grid line width
	public static final int GRID_WIDTH = 8;
	// grid line half width
	public static final int GRID_WIDHT_HALF = GRID_WIDTH / 2;

	// 2D array of ROWS-by-COLS Cell instances
	Cell[][] cells;

	/** Constructor to create the game board */
	public Board() {

		/* initialises the cells array from line 11 using the ROWS and COLS constants
		 * from the GameMain class (lines 18 & 19 in the GameMain class)*/
		cells = new Cell[GameMain.ROWS][GameMain.COLS];

		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col] = new Cell(row, col);
			}
		}
	}

	/** Return true if it is a draw (i.e., no more EMPTY cells) */
	public boolean isDraw() {

		/*
		 * Checks whether the game has ended in a draw using a nested loop to check
		 * through each column in each row. If any of the cells content in the board
		 * grid are Player.Empty (from the Player enum) then the game is not a draw and
		 * will return false to the GameMain class (called in GameMain line 160)to
		 * continue the game (as hasWon() checks have already been done on line 149 in
		 * GameMain class). If there are no empty positions left then the default return
		 * is true on line 47 and will be returned to stop the game and change to
		 * gameState to a draw - ending the current game (called in GameMain line 160)
		 */
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				if (cells[row][col].content == Player.Empty) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Return true if the current player "thePlayer" has won after making their move
	 */
	public boolean hasWon(Player thePlayer, int playerRow, int playerCol) {
		// check if player has 3-in-that-row
		if (cells[playerRow][0].content == thePlayer && cells[playerRow][1].content == thePlayer
				&& cells[playerRow][2].content == thePlayer)
			return true;

		// Checks if the player has 3-in-that-column (playerCol)
		if (cells[0][playerCol].content == thePlayer && cells[1][playerCol].content == thePlayer
				&& cells[2][playerCol].content == thePlayer)
			return true;

		// 3-in-the-diagonal (left top to right bottom)
		if (cells[0][0].content == thePlayer && cells[1][1].content == thePlayer && cells[2][2].content == thePlayer)
			return true;

		// Checks the diagonal in the other direction (right top to left bottom)
		if (cells[2][0].content == thePlayer && cells[1][1].content == thePlayer && cells[0][2].content == thePlayer)
			return true;

		// no winner, keep playing
		return false;
	}

	/**
	 * Draws the grid (rows then columns) using constant sizes, then call on the
	 * Cells to paint themselves into the grid
	 */
	public void paint(Graphics g) {
		// draw the grid
		g.setColor(Color.gray);
		for (int row = 1; row < GameMain.ROWS; ++row) {
			g.fillRoundRect(0, GameMain.CELL_SIZE * row - GRID_WIDHT_HALF, GameMain.CANVAS_WIDTH - 1, GRID_WIDTH,
					GRID_WIDTH, GRID_WIDTH);
		}
		for (int col = 1; col < GameMain.COLS; ++col) {
			g.fillRoundRect(GameMain.CELL_SIZE * col - GRID_WIDHT_HALF, 0, GRID_WIDTH, GameMain.CANVAS_HEIGHT - 1,
					GRID_WIDTH, GRID_WIDTH);
		}

		// Draw the cells
		for (int row = 0; row < GameMain.ROWS; ++row) {
			for (int col = 0; col < GameMain.COLS; ++col) {
				cells[row][col].paint(g);
			}
		}
	}

}
