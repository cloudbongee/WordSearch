import com.sun.source.tree.CaseTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class Grid {
    private int rows;
    private int cols;
    public char[][] grid;
    private HashMap<String,Word> wordList;
    private HashMap<Character, int[]> charCoordinates;
    private Random rand = new Random();

    public Grid(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;

        // apparently they have to be switched
        grid = new char[rows][cols];
        wordList = new HashMap<>();
        charCoordinates = new HashMap<>();

    }

    public boolean isGameFinished(){
        boolean result = true;
        for(Word word : wordList.values()){
            if(!word.isGuessed()){
                result = false;
            }
        }
        return result;
    }

    public void add(String word){
        Word.Direction direction;
        int directionDecision = rand.nextInt(1,4);
        if(directionDecision == 1){
            direction = Word.Direction.DIAGONAL;
        }else if(directionDecision == 2){
            direction = Word.Direction.HORIZONTAL;
        }else if(directionDecision == 3){
            direction = Word.Direction.VERTICAL;
        }else { direction = null; }

        // create a new word,
        Word newWord = new Word(word, rand.nextInt(rows - word.length()), rand.nextInt(cols - word.length()), direction);
        // add it to the list of words
        wordList.put(word, newWord);
        newWord.handleIntersect(charCoordinates, this);
        // append it at the grid
        if(newWord.isValidlyPlaced(this.grid)) {
            newWord.desplegate(grid, charCoordinates);
        }else{
            add(newWord.getValue());
        }

    }

    public void fillEmpty(){
        for(int i =0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(grid[i][j]=='\u0000')grid[i][j] = (char) rand.nextInt(65,91);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for(int i = 0; i < rows; i++){
            String row = "";
            for(int j = 0; j < cols; j++){
                row += " " + grid[i][j];
            }
            row.trim();
            row += "\n";
            result.append(row);
        }
        return result.toString();
    }




}
