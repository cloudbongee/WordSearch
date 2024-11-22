import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Grid {
    private int rows;
    private int cols;
    private char[][] grid;
    private HashMap<String, Boolean> words; //
    private HashMap<String, int[]> wordCoordinates;
    private Random rand = new Random();

    public Grid(int rows, int cols, ArrayList<String> inputWords){
        this.rows = rows;
        this.cols = cols;
        this.grid = new char[rows][cols];
        // initiate a tracking of the guessing of the words by initiating them as false
        inputWords.forEach(key -> {

            // add the grid tracking to the word
            words.put(key, false);


            // generate a random integer for the word position and direction
            int newRow = rand.nextInt(0,rows);
            int newCol = rand.nextInt(0,cols);
            // generate which direction the words have to be facing
            int direction = rand.nextInt(0,3);
        });
    }

    private void fill(){


        // O(n^2) algorithm that places the words
        for(String word : words.keySet()){

            // if it's impossible to place a word just don't do it...
            if(word.length() >= this.grid.length || word.length() >= this.grid[0].length){
                throw new IllegalArgumentException("The word given is way larger than the grid");
            }

            switch(direction){
                case 0:
                    {}
                case 1:
                    {}
                case 2:
                    {}
            }
        }
    }

    private void addDiagonal(String word, int row, int col) throws IllegalArgumentException{

        // if the dimensions are invalid, switch them
        if(row + word.length() >= this.grid.length || col + word.length() >= this.grid[0].length){
            while(row + word.length() >= this.grid.length || col + word.length() >= this.grid[0].length){
                row--;
                col--;
            }
        }
        // the case it intersects with another word
        for (int i = 0; i < word.length(); i++){
            // if one of the letters is invalid then analyze the wordlist
            if(this.grid[row][col] != '\u0000' || this.grid[row][col] != word.charAt(i)){

            }
        }
        for (int i = 0; i < word.length(); i++) this.grid[row + i][col + i] = word.charAt(i);

    }

    private static void addHorizontal(){

    }

    private static void addVertical(){

    }


}
