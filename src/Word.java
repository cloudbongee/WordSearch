package com.gradescope.wordsearch;

import java.util.ArrayList;
import java.util.Arrays;
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
                    grid[row][col+i] = Character.toUpperCase(this.value.charAt(i));
                    letterCoordinates.put(this.value.charAt(i), new int[]{row, col+i, direction.ordinal()});
                }
                break;
            case HORIZONTAL:
                for(int i = 0; i < this.length; i++){
                    grid[row+i][col] =Character.toUpperCase(this.value.charAt(i));
                    letterCoordinates.put(this.value.charAt(i), new int[]{row + i, col, direction.ordinal()});
                }
                break;
            case DIAGONAL:
                for(int i = 0; i < this.length; i++){
                    grid[row+i][col+i] = Character.toUpperCase(this.value.charAt(i));
                    letterCoordinates.put(this.value.charAt(i), new int[]{row + i, col+i, direction.ordinal()});
                }break;
        }
    }

    public boolean isValidlyPlaced(char[][] grid){
        if(grid.length < row + length || grid[0].length < col + length ) return false;
        switch(direction){
            case VERTICAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[row][col + i] != this.value.charAt(i) && grid[row][col + i] != '\u0000'){
                        return false;
                    }
                }
            case HORIZONTAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[row + i][col] != this.value.charAt(i) && grid[row + i][col] != '\u0000'){
                        return false;
                    }
                }
            case DIAGONAL:
                for(int i = 0; i < this.length; i++){
                    if(grid[row + i][col + i] != this.value.charAt(i) && grid[row + i][col + i] != '\u0000'){
                        return false;
                    }
                }
        }
        return true;
    }

    public Direction getDirection(){
        return direction;
    }

    public void handleIntersect(Grid grid){


        if(this.direction == Direction.VERTICAL){
            for(int i = 0; i < grid.grid.length - this.length; i++){ // add through the rows
                for(int j = 0; j < grid.grid[0].length - col; j++){ // add through the columns
                    this.row += i;
                    this.col += j;
                    if(isValidlyPlaced(grid.grid)) return;
                }
            }
        }else if(this.direction == Direction.HORIZONTAL){
            for(int i = 0; i < grid.grid.length - row; i++){
                for(int j = 0; j < grid.grid[0].length - length; j++){
                    this.row += i;
                    if(isValidlyPlaced(grid.grid)) return;
                }
            }
        }else{
            for(int i = 0; i < grid.grid.length - length; i++){
                for(int j = 0; j < grid.grid[0].length - length; j++){
                    this.row += i;
                    this.col += j;
                    if(isValidlyPlaced(grid.grid)) return;
                }
            }
        }
    }

}
