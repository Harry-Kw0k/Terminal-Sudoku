import modules.*;
import java.util.*;

public class Sudoku{

    /** Reveals solved sudoku board.
     * 
     * @param board - the grid of ongoing sudoku game
     */ 
    public static void reveal(Box[][] board) {
        for (int row = 0; row < board.length ; row++) {
            for (int cell = 0; cell < board[0].length; cell++) {
                board[row][cell].setHiddenState(false);
            }
        }
    }

    public static void main(String[] args) {
        
        Scanner in = new Scanner(System.in);

        // Tracks if player want to continue playing or stop.
        String redo = "yes"; 
        
        // Run Sudoku game.
        while(redo.equals("yes")) {

            Box.solved = 0;
            int playerLives = 3;
            
            // Create hidden solved board.
            Grid board = new Grid(new Box[9][9]);
            board.createGrid(Solve.solveSudoku(), Solve.availableBoxes());
            
            // Prompts user to pick which difficulty they want to play in.
            String difficulty;
            do {
                System.out.println("\nWelcome to Sudoku!\n\nThe goal of Sudoku is to fill a 9×9 grid with");
                System.out.println("numbers so that each row, column and 3×3 section");
                System.out.println("contain all of the digits between 1 and 9\n\nPick Difficulty: Easy | Medium | Hard");
                difficulty = in.nextLine().toLowerCase();
    
            } while(!difficulty.equals("easy") && !difficulty.equals("medium") && !difficulty.equals("hard"));
    
            // Reveal certain amount of boxes based on difficulty.
            board.setMode(difficulty);
    
            System.out.println("\n\n" + board);

            // Location variables.
            int x;
            int y;

            // Variable used to store value in which the player picked.
            int value;

            // Variable used to verify if player selected correct value and location for a Sudoku box.
            boolean verify;

            // Provide users with prompt to decide their moves on the Sudoku board until user loses or solved all 81 boxes.
            while(Box.solved != 81 && playerLives > 0) {

                // Continues looping until location and value match on board.
                do {

                    // Prompts user to pick a location on the board until they pick an available location.
                    do {
                        System.out.println("Pick location (x y) ");
                        x = in.nextInt();
                        y = in.nextInt();
                        in.nextLine();
            
                    } while(((x < 0 || x >= 9)||(y < 0 || y >= 9)) || board.getGrid()[y][x].getHiddenState() == false);

                    // Prompts user to pick a value from 1 - 9 until they choose within that range.
                    do {
                        System.out.println("Value (1 - 9); ");
                        value = in.nextInt();
                        in.nextLine();
            
                    } while(!(value > 0 && value <= 9));
                    
                    verify = board.isCorrect(board.getGrid()[y][x], value); // Checks if value can be place in the selected location.

                    // Removes a life when user picks wrong location for a value.
                    if (verify == false) {
                        playerLives --;
                    }

                    System.out.println("Remaining Lives: " + playerLives);
        
                } while(verify == false && playerLives != 0 && Box.solved != 81);
                
                // Reveals box value and print out new board.
                if (playerLives > 0) {
                    board.getGrid()[y][x].setHiddenState(false);
                    System.out.println("\n\n" + board);
                    System.out.println("Remaining Lives: " + playerLives);
                }
            }
            
            // Prints whether player lost or won by solved the Sudoku board
            if (playerLives > 0) {
                System.out.print("You Won");
    
            } else {
                System.out.println("You Lost");
            }

            // When user completes Sudoku board or loses, solution of board is printed.
            Sudoku.reveal(board.getGrid());
            System.out.println(board);
            System.out.println("Solution ^ ");  
            
            // Prompts user to decide whether to try again until they input "yes" or "no".
            do {
                System.out.println("Try again? (yes|no)");
                redo = in.nextLine().toLowerCase();
    
            } while(!redo.equals("yes") && !redo.equals("no"));
        }  
        in.close();
    }
}