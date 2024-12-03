
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

/**
 * The grid class contains the board, and the information iin which the word set game will be developed,
 * the class implements functions to add words
 * @author Jaime Meyer Beilis Michel
 * @since november 22 2024, Fall 2024 semester at the University of arizona
 * No specific utilization notes for a user, since it handles logic.
 */
public class Grid {
    private int rows;
    private int cols;
    public char[][] grid;
    private HashMap<String, Word> wordList;
    private Random rand = new Random();

    public Grid(int rows, int cols) { // constructor of the class, settles all by default or input
        this.rows = rows;
        this.cols = cols;

        grid = new char[rows][cols];
        wordList = new HashMap<>();
    }

    public void addToWordlist(String word) { // setter method for the hashset of words
        wordList.put(word,null);
    }

    public HashMap<String, Word> getWordList() { // getter method for the hashset of words
        return wordList;
    }

    /**
     * Add is a recursive function, which places the words on their respective position
     * @param word: String which will be converted into an object of the word class
     * @param directionDecision: an integer that rules out the direction
     */
    public void add(String word, int directionDecision){

        // utilize the random input to decide direction
        Word.Direction direction;
        if(directionDecision == 0){
            direction = Word.Direction.DIAGONAL;
        }else if(directionDecision == 1){
            direction = Word.Direction.HORIZONTAL;
        }else if(directionDecision == 2){
            direction = Word.Direction.VERTICAL;
        }else { direction = null; System.out.println("Something is wrong here " + directionDecision); }


        // create a new word, inside the bounds
        Word newWord = new Word(word, rand.nextInt(rows - word.length()), rand.nextInt(cols - word.length()), direction);

        // if it is not correctly positioned, find a new one
        if(!newWord.isValidlyPlaced(grid) && newWord.isPlaceable(grid)){
            add(word, rand.nextInt(3));
        }else{
            newWord.desplegate(grid); // represent in the grid
        }

        wordList.put(word,newWord);

    }

    // adds a random letter of the english alphabet to the grid when it is empty
    public void fillEmpty(){
        for(int i =0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j]=='\u0000')grid[i][j] = (char) ( 65 + rand.nextInt(26));
            }
        }
    }

    // represent the grid as a string for printing or pouring into a file
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(); // iterate from row to column
        for(int i = 0; i < rows; i++){
            String row = "";
            for(int j = 0; j < cols; j++){
                row += grid[i][j] + " ";
            }
            row.trim();
            row += "\n";
            result.append(row);
        }

        return result.toString();
    }

    /**
     * The gameFinished method checks that all words in the word list have been guessed correctly.
     * @return boolean determining if the game should be called off
     */
    public boolean gameFinished(){
        boolean result = true;
        for(Word word : this.wordList.values()){
            result = result && word.isGuessed();
        }
        return result;
    }
}
