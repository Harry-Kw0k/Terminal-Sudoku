package modules;
import java.util.*;

public class Grid {
    
    // A 2D array of Box objects representing the Sudoku grid.
    private Box[][] grid;

    /** Initializes grid with specific size.
     * 
     * @param grid - A 2D array of Box objects to represnt the Sudoku Grid.
     */
    public Grid(Box[][] grid) {
        this.grid = grid;
    }

    /** Retrieves the grid.
     * 
     * @return A 2D array of Box objects to represnt the Sudoku Grid.
     */
    public Box[][] getGrid() {
        return this.grid;
    }

    /** Input complete objects into list creating the grid
     * 
     * @param gridValues - Values of the boxes
     * @param locations - coordinates(x,y) of boxes on the grid
     */
    public void createGrid(int[][] gridValues, ArrayList<int[]> locations) {
        for (int row = 0; row < 9; row++) {
            for (int cell = 0; cell < 9; cell++) {
                Box box = new Box(gridValues[row][cell]);
                this.grid[row][cell] = box;
            }
        }
    }

    /** Sets the board depending on difficulty level chosen.
     * 
     * @param difficulty - the difficulty level ("easy", "medium", or "hard").
     */
    public void setMode(String difficulty) {
        
        ArrayList<int[]> locations = Solve.availableBoxes();
        Collections.shuffle(locations);

        int numCellsRevealed;
        
        // Determine the amount of boxes revealed based on difficulty.
        switch (difficulty.toLowerCase()) {
            case "easy":
                numCellsRevealed = 38;
                break;
            case "medium":
                numCellsRevealed = 30;
                break;
            default:
                numCellsRevealed = 22;
        }

        // Reveals the specified number of boxes.
        for (int unit = 0; unit < numCellsRevealed ; unit++ ) {
            this.grid[locations.get(unit)[1]][locations.get(unit)[0]].setHiddenState(false);
        }
    }

     /** Determines if the given value is correct for the specified box.
     * 
     * @param box - the Box object whose value will be checked.
     * @param value - the value to be compared against the box's value.
     * @return true if the value matches the box's value, otherwise false.
     */
    public boolean isCorrect(Box box, int value) {
        if (value == box.getValue()) {
            return true;
        } else {
            return false;
        }
    }

    /** Generates a string visual of the board
     * 
     * @return A formatted string representing the Sudoku grid.
     */
    public String toString() {

        String board = "";
        int count1 = 3;
        int count2 = 0;
        int count3 = 3;

        int yCoord = 0;

        board += "    0  1  2   3  4  5   6  7  8\n";
        // Loops until 9x9 grid is formulated
        for (int row = 0; row < this.grid.length; row++) {

            // Creates a divider after every third row
            if (count3 == 3) {
                count3 = 0;
                board += "  -------------------------------\n";
            }
            count3++;

            board += yCoord + " ";
            yCoord++;

            for (int cell = 0; cell < this.grid[0].length; cell++) {

                // Places divider every third box
                if (count1 == 3) {
                    count1 = 0;
                    board += "|";
                } 
                count1++;
                count2++;

                // Prints box
                board += this.grid[row][cell];

                // When row is fully added, space down
                if (count2 == 9) {
                    count2 = 0;
                    board += "|\n";
                }
            }  
        } 
        board += "  -------------------------------\n";
        return board;
    }
}
