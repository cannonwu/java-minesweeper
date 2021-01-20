// Cannon Lawrence Wu, 2018

import java.util.Scanner;

public class Cell { 
    public static final char MINE = '*';
    public static final char NO_ADJ_MINE_CELL = '.';
    public static final char HIDDEN_CELL = '#';
    public static final char[] NUM_ADJ_MINES = {'.','1','2','3','4','5','6','7','8'};
    
    private boolean mine;
    private boolean revealed;
    private int numAdjacentMines;

    /* Constructor - sets up a default Cell. It is hidden, not a mine,
     *               and has 0 adjacent mines.
     */
    public  Cell() {
        mine = false;
        revealed = false;
        numAdjacentMines = 0;
    }

    /* Mark this cell as containing a mine
     */
    public void setMine() {
        mine = true;
    }
    
    /* Helper method which returns a char value corresponding to the integer parameter
     * numMines = 0 --> returns '.'
     * numMines = 1 --> returns '1', numMines = 2 --> returns '2' etc
     */
    private char getNumMines(int numMines) {
        return NUM_ADJ_MINES[numMines];
    }

    /* If the Cell is not revealed, returns HIDDEN_CELL
     * Calls getNumMines() to get the char corresponding to number of mines if the Cell is revealed.
     *
     */
    public char getVal() {
        if (revealed) {
            return getNumMines(numAdjacentMines);
        } else {
            return HIDDEN_CELL;
        }
    }
	
	public char getValDev() {
		if (!mine) {
			return getNumMines(numAdjacentMines);
		} else {
			return MINE;
		}
	}

    /* Mark this cell as revealed.
     */
    public void reveal() {
        revealed = true;
    }

    /* Store the number of neighboring mines there are to this cell.
     */
    public void setAdjacentMines(int numMines) {
        numAdjacentMines = numMines;
    }

    /* Returns the number of neighboring mines there are to this cell.
     */
    public int getAdjacentMines() {
        return numAdjacentMines;
    }
    
    /* Checks if the cell is a mine or not
     * @return   true if it is a mine, false if it is not a mine
     */
    public boolean isMine() {
        return mine;
    }
	
	public boolean isRevealed() {
		if (revealed == true) {
			return true;
		} else {
			return false;
		}
	}
	
	public char printMine() {
		return MINE;
	}
}