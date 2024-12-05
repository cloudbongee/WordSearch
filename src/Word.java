
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;


/**
 *  The class word contains information about a word in the crossword (I know, that's a mouthful),
 *  amongst it, location and boolean information (some of it unused, yet useful in the long run)
 * @author Jaime Meyer Beilis Michel
 * @since november 22 2024, Fall 2024 semester at the University of arizona
 * No specific utilization notes for a user, since it handles logic.
 */
public class Word {

    // declare variables. Quite some
    private String value;
    private int length;
    private int row;
    private int col;

    // settle the alignment of the word
    public enum Direction { VERTICAL, HORIZONTAL, DIAGONAL }
    private Direction direction;
    private boolean matched; // unused, will be useful with javafx

    /**
     * The word constructor settles the given parameters. Deterministically settles a word as not matched
     * @param value: String representing the word
     * @param row: As decided by recursion with randomness
     * @param col: As decided by recursion with randomness
     * @param direction: As decided by a random number
     */
    public Word(String value, int row, int col, Direction direction) {
        // determined values
        this.value = value;
        this.length = value.length();

        // randomly generated variables
        this.row = row;
        this.col = col;

        // the direction can be hardcoded by the random number generator and a switch
        this.direction = direction;
        matched = false;
    }

    // This is just an aid constructor to test implementations. I left it as means of debugging for future projects
    public Word(String value) {
        // determined values
        this.value = value;
        this.length = value.length();
    }

    // to complete for the previous constructor, to test the implementation, left it as means of debugging
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    // permit only to toggle to true, but not to toggle back to false the status of a word being false.
    public void toggleMatched() {
        if(!this.matched){
            this.matched = true;
        }
    }


    // get the value of the string
    public String getValue() {
        return value;
    }
    // get the rows of the string
    public int getRow() {
        return row;
    }
    // get the columns of the string
    public int getCol() {
        return col;
    }
    // get if the word has been guessed yet (currently unused)
    public boolean isGuessed(){
        return matched;
    }


    private char representation(int i){
        if(matched){
            return '*';
        }
        return Character.toUpperCase(value.charAt(i));
    }

    /**
     * The desplegate class (I called it like that because my english is not good), represents the string in the parameter
     * grid
     * @param grid: grid of characters for the puzzle
     */
    public void desplegate(char[][] grid){
        // if the direction is diagonal, you want to advance on both directions, otherwise just one
        switch(direction){
            case VERTICAL:
                for(int i = 0; i < this.length; i++){
                    grid[row + i][col] = representation(i); //changed the i from col to row
                }break;
            case HORIZONTAL:
                for(int i = 0; i < this.length; i++){
                    grid[row][col + i] =  representation(i);
                }break;
            case DIAGONAL:
                for(int i = 0; i < this.length; i++){
                    grid[row+i][col+i] = representation(i);
                }break;
        }
    }


    /**
     * The isValidlyPlaced boolean represents wether or not the extension of a word added should or should not be, it
     * takes care of overwriting and mispositioned string
     * @param grid: grid in which the word will be located
     * @return: boolean
     */
    public boolean isValidlyPlaced(char[][] grid){

        // on one of all directions, check if a character would overlap (not the searched for string, not empty)
        switch(direction){
            case VERTICAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[row + i][col] != Character.toUpperCase(this.value.charAt(i)) && grid[row][col + i] != '\u0000'){
                        return false;
                    }
                }
            case HORIZONTAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[row][col + i] != Character.toUpperCase(this.value.charAt(i)) && grid[row + i][col] != '\u0000'){
                        return false;
                    }
                }
            case DIAGONAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[row + i][col + i] != Character.toUpperCase(this.value.charAt(i)) && grid[row + i][col + i] != '\u0000'){
                        return false;
                    }
                }
        }
        return true;
    }

    /**
     * Is placeable is a second logic gate for the word, where it revises if the word should or should not be appended
     * into the list based on its dimensions
     * @param grid
     * @return
     */
    public boolean isPlaceable(char[][] grid){
        // check the dimensions
        if(direction == Direction.HORIZONTAL && length >= grid[0].length) return false;
        else if(direction == Direction.VERTICAL && length >= grid.length) return false;
        else if(direction == Direction.DIAGONAL && (length >= grid[0].length || length >= grid.length)) return false;
        return true;
    }

    /**
     * just a getter for direction
     * @return
     */
    public Direction getDirection(){
        return direction;
    }

    public boolean match(String word, int x, int y, Direction inputDirection, char[][] grid){
        if(word.equalsIgnoreCase(this.value) && this.row == x && this.col == y && this.direction.equals(inputDirection)){
            toggleMatched(); // change the word to true
            return true;
        }
        return false;
    }

}