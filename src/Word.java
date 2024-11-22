import java.util.Random;

public class Word {

    private String value;
    private int length;
    private int row;
    private int col;

    public enum Direction { VERTICAL, HORIZONTAL, DIAGONAL }
    private Direction direction;
    private Random rand; // reference a given random number generator to not have discrepancies
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

    // set random apart from the constructor, it represents a little overhead but it is better reference control
    public void setRandom(Random rand){
        this.rand = rand;
    }

    private int manageCollision(String wordCollided){

    }

    private boolean isValidlyPlaced(){

    }




}
