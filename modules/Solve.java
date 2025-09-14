package modules; 
import java.util.*;

public class Solve  {

    static int count = 0; // Tracks the iterations for recursion depth.
    static boolean solutionImpossible = true; // Tracks whether the solution of the board is impossible.

    /** Generates a list of all possible (x, y) coordinates for a 9x9 Sudoku board.
     * 
     * @return An ArrayList of integer arrays ([x-coordinate, y-coordinate] of a cell)
     */
    public static ArrayList<int[]> availableBoxes() {

        // Creates a list of all possible (x, y) locations on a 9x9 Sudoku board.
        ArrayList<int[]> locations = new ArrayList<>();
    
        // Populate the list with coordinates from (0, 0) to (8, 8).
        for (int unit = 0; unit < 81; unit++) {
            int x = unit % 9;  // Calculate column index.
            int y = unit / 9;  // Calculate row index.
            locations.add(unit, new int[]{x, y});
        }
    
        return locations;
    }
    
    /** Checks if a value can be placed in the specified subgrid without conflicts.
     * 
     * @param board - The current state of the Sudoku board as a 2D integer array.
     * @param x - The x-coordinate of the cell being checked.
     * @param y - The y-coordinate of the cell being checked.
     * @param value - The value being checked.
     * @return true if the value is not present in the 3x3 subgrid; otherwise, false.
     */
    public static boolean checkSubgrid(int[][] board, int x, int y, int value) {
    
        // Determine the starting coordinates of the 3x3 subgrid.
        int startX = (x / 3) * 3;
        int startY = (y / 3) * 3;
    
        // Check if the value exists in the subgrid.
        for (int row = startY; row < startY + 3; row++) {
            for (int cell = startX; cell < startX + 3; cell++) {
                if (board[row][cell] == value) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /** Checks if a value can be placed in the specified row without conflicts.
     * 
     * @param board - The current state of the Sudoku board as a 2D integer array.
     * @param y - The row index being checked.
     * @param value - The value being checked.
     * @return true if the value is not present in the row; otherwise, false.
     */
    public static boolean checkRow(int[][] board, int y, int value) {
        for (int cell = 0; cell < 9; cell++) {
            if (board[y][cell] == value) {
                return false;
            }
        }
        return true;
    }
    
    /** Checks if a value can be placed in the specified column without conflicts.
     * 
     * @param board - The current state of the Sudoku board as a 2D integer array.
     * @param x - The column index being checked.
     * @param value - The value being checked.
     * @return true if the value is not present in the column; otherwise, false.
     */
    public static boolean checkColumn(int[][] board, int x, int value) {
        for (int row = 0; row < 9; row++) {
            if (board[row][x] == value) {
                return false;
            }
        }
        return true;
    }

    /** Finds values aligned with rules of Sudoku for each Sudoku box given location order
     * 
     * @param board - The current state of the Sudoku board as a 2D integer array.
     * @param locations - Locations (x,y) of each Sudoku box
     * @param index - Tracks the amount of boxes solved
     * @return
     */
    public static boolean solve(int[][] board, ArrayList<int[]> locations, int index) {
        
        // Signals that solution for the entire board is found stopping recursion process
        if (index == locations.size()) {
            return true;
        }

        // If the recursion depth exceeds 300,000 iterations, the function is considered to have no solution and exit the method
        if (count > 300000) {
            solutionImpossible = true;
            return true;
        }

        solutionImpossible = false;

        // Gets coordinates (x[cell], y[row]) of selected box
        int boxX = locations.get(index)[0];
        int boxY = locations.get(index)[1];

        // Loops until all boxes have a value of 1 - 9
        for(int value = 1; value <= 9; value++) {

            // Checks if value can be inputted to the selected box
            if (Solve.checkSubgrid(board, boxX, boxY, value) == true && 
                Solve.checkRow(board, boxY, value) == true &&
                Solve.checkColumn(board, boxX, value) == true) {
                        
                    board[boxY][boxX] = value; 

                    count++; // increase counter for recursion depth

                    // Finds next box value and returns true if a value is found
                    if (solve(board, locations, index + 1)) {
                        return true; // Indicates solution is valid starting from first instance
                    } 

                    // Resets current cell if no value can be inputted
                    board[boxY][boxX] = 0; 
            }
        }  
        // Cancels current recursive alogrothim process, backtracking to previous process to try a new value
        return false;
    } 
    
    /** Finds values for each box of Sudoku grid.
     * 
     * @return solved Sudoku board
     */
    public static int[][] solveSudoku() {

        int[][] board = new int[9][9];

        // Shuffled sequence of locations can be sorted in a way where when iterated through linearly, 
        // there is no solution, backtracking infinitely

        // Solves new boards until there is a possible solution for one of them
        while (solutionImpossible == true) {
            
            // Creates new 2D array for values of Sudoku board
            board = new int[9][9];
            ArrayList<int[]> locations = Solve.availableBoxes();

            Collections.shuffle(locations);
        
            solve(board, locations, 0);

            count = 0; // Resets counter for recursion depth
        }
        solutionImpossible = true; // Resets variable for next time board is being solved.
        return board;
    }

    // Prints solved Sudoku board
    public static void main(String[] args) {
        for(int[] row : solveSudoku()){
            for (int elm : row) {
                System.out.print(elm);  
            }
            System.out.println();
        } 
    }
}
