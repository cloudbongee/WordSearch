import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;


/**
 * The word class implements logic for positioning words in a wordsearch. It also contains variables that help memorize
 * and gather previous results
 */
public class Word {

    private String value;
    private int length;
    private int row;
    private int col;

    public enum Direction { VERTICAL, HORIZONTAL, DIAGONAL }
    private Direction direction;
    private boolean matched;

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

    public void desplegate(char[][] grid, HashMap<Character, int[]> letterCoordinates){

        switch(direction){
            case VERTICAL:
                for(int i = 0; i < this.length; i++){
                    grid[row][col+i] = this.value.charAt(i);
                    letterCoordinates.put(this.value.charAt(i), new int[]{row, col+i, direction.ordinal()});
                }
            case HORIZONTAL:
                for(int i = 0; i < this.length; i++){
                    grid[row+i][col] = this.value.charAt(i);
                    letterCoordinates.put(this.value.charAt(i), new int[]{row + i, col, direction.ordinal()});
                }
            case DIAGONAL:
                for(int i = 0; i < this.length; i++){
                    grid[row+i][col+i] = this.value.charAt(i);
                    letterCoordinates.put(this.value.charAt(i), new int[]{row + i, col+i, direction.ordinal()});
                }
        }
    }

    public boolean isValidlyPlaced(char[][] grid){
        switch(direction){
            case VERTICAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[col][row + i] != this.value.charAt(i) && grid[col][row + i] != '\u0000'){
                        return false;
                    }
                }
            case HORIZONTAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[col + i][row] != this.value.charAt(i) && grid[col + i][row] != '\u0000'){
                        return false;
                    }
                }
            case DIAGONAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[col + i][row + i] != this.value.charAt(i) && grid[col + i][row + i] != '\u0000'){
                        return false;
                    }
                }
        }
        return true;
    }

    public Direction getDirection(){
        return direction;
    }

    public void handleIntersect(HashMap<Character, int[]> letterCoordinates, char[][] grid){
        System.out.println(value);
        for(int i = 0; i < length; i++){
            System.out.println(value.charAt(i));
            // if there is some letter that could intersect
            if(letterCoordinates.containsKey(this.value.charAt(i))){
                // if the letter has an opposing direction
                if(letterCoordinates.get(this.value.charAt(i))[2] != this.direction.ordinal()){
                    try {
                        // assign row and columns to the coordinates
                        if (this.direction == Direction.VERTICAL) {
                            this.row = letterCoordinates.get(this.value.charAt(i))[0];
                            this.col = letterCoordinates.get(this.value.charAt(i))[1] - i;
                        } else if (this.direction == Direction.HORIZONTAL) {
                            this.row = letterCoordinates.get(this.value.charAt(i))[0] - i;
                            this.col = letterCoordinates.get(this.value.charAt(i))[1];
                        } else {
                            this.row = letterCoordinates.get(this.value.charAt(i))[0] - i;
                            this.col = letterCoordinates.get(this.value.charAt(i))[1] - i;
                        }
                        // check if the word is validly placed. If it is, remove from the list and return
                        if (isValidlyPlaced(grid)) {
                            letterCoordinates.remove(this.value.charAt(i));
                            return;
                        }
                    }catch (NullPointerException e){
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
//    public void handleIntersect(HashMap<Character, int[]> charCoordinates, Grid grid){
//        for(int i = 0; i < this.length; i++){
//            // if they are not from the same direction, and contain the same letter
//            if(charCoordinates.containsKey(this.value.charAt(i)) && this.direction.ordinal() != charCoordinates.get(this.value.charAt(i))[2]){
//                if(this.direction == Direction.VERTICAL) {
//                    this.row = charCoordinates.get(this.value.charAt(i))[0];
//                    this.col = charCoordinates.get(this.value.charAt(i))[1] - i;
//                }
//                else if(this.direction == Direction.HORIZONTAL) {
//                    this.row = charCoordinates.get(this.value.charAt(i))[0] - i;
//                    this.col = charCoordinates.get(this.value.charAt(i))[1];
//                }else{
//                    this.row = charCoordinates.get(this.value.charAt(i))[0] - i;
//                    this.col = charCoordinates.get(this.value.charAt(i))[1] - i;
//                }
//                charCoordinates.remove(this.value.charAt(i));
//                if(isValidlyPlaced(grid.grid)){
//                    charCoordinates.remove(this.value.charAt(i));
//                    return;
//                }
//                else{
//                    grid.add(this.value);
//                }
//            }
//        }
//    }

//    private int[] intersectWords(Word word){
//        for(int i = 0; i < this.length; i++){
//
//        }
//    }

//
//    public void appendAtGrid(char[][] grid){
//        while(!isValidlyPlaced(grid)){ // repeat task of finding a correct position until necessary
//            System.out.println("Putting into place, if something is not running the way you want it to,\n then it's probably how you wrote the logic here");
//
//        }
//        desplegate(grid);
//    }

}
