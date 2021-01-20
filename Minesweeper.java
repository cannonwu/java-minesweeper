// Cannon Lawrence Wu, 2018

import java.util.Scanner;
import java.lang.Math;
import java.util.Random;

public class Minesweeper { 

    private static final int GAME_WON = 1;
    private static final int GAME_LOST = -1;
    private static final int GAME_NOTOVER = 0;
    private static final int NUM_ROWS = 0;
    private static final int NUM_COLS = 0;
    // Add other static variables and constants you might need
    private static Cell[][] grid;
	
    /* 
     * Create the grid and place mines in random locations.
     *
     * @param rows     The number of rows in the Minesweeper grid
     * @param columns  The number of columns in the Minesweeper grid
     *
     */
    public static void initGrid(int rows, int columns) {					//create grid of user-inputed length and width
        grid = new Cell[rows + 2][columns + 2];
		for (int i = 0; i < rows + 2; i++) {
            for (int j = 0; j < columns + 2; j++) {
               grid[i][j] = new Cell();
            }
		}
	}
    
    /*
     * Places mines in random locations in the grid.
     *
     * @param amountMines   The number of mines to be set in the grid.
     */
    public static void disperseMines(int amountMines) {
		Random random = new Random();
		for (int k = 0; k < amountMines; k++) {
			int randomRow = random.nextInt(grid[0].length - 1);				//generate random index locations
			int randomCol = random.nextInt(grid.length - 1);
			System.out.println("test3");
			while (grid[randomRow][randomCol].isMine()) {
				randomRow = random.nextInt(grid[0].length - 1);
				randomCol = random.nextInt(grid.length - 1);
			}
			while (randomRow == 0) {
				randomRow = random.nextInt(grid[0].length - 1);
			}
			while (randomCol == 0) {
				randomCol = random.nextInt(grid.length - 1);
			}
			System.out.println("test4");
			grid[randomRow][randomCol].setMine();
		}
    }

    /*
     * Updates each cell with the number of adjacent cells with mines
     */
    public static void adjacentMines() {
        int numAdjacentMines = 0;
		for (int i = 1; i < grid[0].length - 2; i++) {						//count up the number of mines in neighboring cells
            for (int j = 1; j < grid.length - 2; j++) {
				if (grid[i][j].isMine() == false) {
					if (grid[i + 1][j].isMine()) {							//right
						numAdjacentMines++;
					}
					if (grid[i][j + 1].isMine()) {							//down
						numAdjacentMines++;
					}
					if (grid[i - 1][j].isMine()) {							//left
						numAdjacentMines++;
					}
					if (grid[i][j - 1].isMine()) {							//up
						numAdjacentMines++;
					}
					if (grid[i + 1][j - 1].isMine()) {						//diagonal right, up
						numAdjacentMines++;
					}
					if (grid[i + 1][j + 1].isMine()) {						//diagonal right, down
						numAdjacentMines++;
					}
					if (grid[i - 1][j + 1].isMine()) {						//diagonal left, down
						numAdjacentMines++;
					}
					if (grid[i + 1][j - 1].isMine()) {						//diagonal left, up
						numAdjacentMines++;
					}
					System.out.println("Number of mines " + numAdjacentMines);
					grid[i][j].setAdjacentMines(numAdjacentMines);
				}
			}
		}
    }
 
    /*
     * Method to print Minesweeper grid
     */
    private static void printGrid() {
        for (int i = 1; i < grid[0].length - 1; i++) {
            for (int j = 1; j < grid.length - 1; j++) {
                if (grid[i][j].isRevealed() == false) {
					System.out.print(grid[i][j].getVal() + " ");
				} else if (grid[i][j].isMine() && grid[i][j].isRevealed()) {
					System.out.print(grid[i][j].printMine() + " ");
				}
            }
			System.out.println();
        }
    }

    /*
     * Method to reveal all the hidden cells. Prints grid after all cells
     * have been revealed.
     */
    public static void revealGrid() {
		for (int i = 1; i < grid[0].length - 2; i++) {
            for (int j = 1; j < grid.length - 2; j++) {
				System.out.print(grid[i][j].getValDev());
			}
			System.out.println();
        }
    }

    /* 
     * Reveals the selected cell. So the cell now displays a '.' if no
     * adjacent cells have mines, or, a number representing the number 
     * of adjcent cells with mines.
     *
     * @param   row    Row of the user selected cell
     * @param   column Column of the user selected cell
     * @return  an integer indicating if the game is won, lost or not-over
     */
    public static int revealCell(int row, int column) {
        /*
         * Handle user's cell selection specified by row and column 
         * There are three different cases:
         * 1. user chooses already explored cell - do nothing
         * 2. user chooses cell which has a mine - game lost
         * 3. user chooses a mine-free cell - reveal the cell
         * Print Minesweeper grid after handling user input
         *
         */
        if (grid[row][column].isRevealed()) {
			System.out.println("Pick again.");
			printGrid();
		} else if (grid[row][column].isMine()) {
			revealGrid();
			return GAME_LOST;
		}
		for (int i = 1; i < grid[0].length - 1; i++) {
            for (int j = 1; j < grid.length - 1; j++) {
				grid[row][column].reveal();
			}
		}
		printGrid();
		return GAME_NOTOVER;
    }

    /*
     * Check if all the mine-free cells are revealed
     * @return  true if game over, false if not
     */
    public static boolean checkGameOver() {
        boolean variable = false;
		for (int i = 1; i < grid[0].length - 1; i++) {
            for (int j = 1; j < grid.length - 1; j++) {
				if ((grid[i][j].isMine() == false) && grid[i][j].isRevealed()) {
					variable = true;
					System.out.println("You revealed all non-mines! You win!");
				}
				if (grid[i][j].isMine() && grid[i][j].isRevealed()) {
					System.out.println("You hit a mine! Game Over.");
				}
			}
		}
		return variable;
    }

    /* public int nextInt(int n) {
		int range = (max - min) + 1;
		return (int)(Math.random() * range);
	} */
	
    public static void main(String[] args) { 
    	//System.out.println(randomWithRange(5,1));
    	//System.out.println(randomWithRange(8,1));
    	
		Scanner scanner = new Scanner(System.in);
		System.out.println("How many rows? ");
		int rows = Integer.parseInt(scanner.nextLine());
		System.out.println("How many columns? ");
		int columns = Integer.parseInt(scanner.nextLine());
		initGrid(rows, columns);
		System.out.println("How many mines?");
		int mines = Integer.parseInt(scanner.nextLine());
		disperseMines(mines);
		adjacentMines();
		printGrid();
		System.out.println("Debug grid?");
		if (scanner.nextLine().equals("yes")) {
			revealGrid();
		}
		while (checkGameOver() == false) {
			System.out.println("Which row to play:");
			int pickRow = Integer.parseInt(scanner.nextLine());
			pickRow--;
			System.out.println("Which column to play:");
			int pickColumn = Integer.parseInt(scanner.nextLine());
			pickColumn--;
			revealCell(pickRow, pickColumn);
			checkGameOver();
			
		}
  	}
}