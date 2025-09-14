package modules;

public class Box {
    
    // Instance variables representing state and value of Sudoku box
    private boolean hiddenState; // true if box is hidden, false if revealed
    private int value; // the value of the box (1-9)

    // Static variable to track the total number of revealed(solved) boxes
    public static int solved = 0;
    
    /** Initializes a Sudoku box with specific values.
     * 
     *  @param hiddenState - state of box  
     *  @param value - value of box
     */
    public Box (boolean hiddenState, int value) {
        this.hiddenState = hiddenState;
        this.value = value;
    }

    /** Constructor to initialize a Sudoku box with a given value.
     * 
     * @param value - the value of the box (typically between 1 and 9 for Sudoku, or 0 for an empty box).
     * Initializes the hiddenState to true, indicating that the box is hidden at the start.
     */
    public Box(int value) {
        this.hiddenState = true; 
        this.value = value;      
    }

    /** Set state of box
     * 
     *  @param fixed - updated state
     */
    public void setHiddenState(boolean hiddenState) {
        this.hiddenState = hiddenState;
        if (hiddenState == false) {
            solved++;
        }
    }

    /** Set value of box
     * 
     *  @param value - new value of box
     */
    public void setValue(int value) {
        this.value = value;
    }
    
    /** Retrieve value of box
     * 
     * @return - value of box
     */
    public int getValue() {
        return this.value;
    }

    /** Retrieve state of box
     * 
     * @return - if box is hidden
     */
    public boolean getHiddenState() {
        return this.hiddenState;
    }

    /** Display of sudoku box
     * 
     * @return - value, String
     */
    public String toString() {
        if (this.hiddenState == false) {
            return " " + this.value + " ";
        } else {
            return "   ";
        }
    }
}

