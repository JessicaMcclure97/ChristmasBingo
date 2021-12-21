import java.util.ArrayList;
import static java.util.Collections.shuffle;

/**
 * Bingo Master Board 'Brain' to run
 * the program.
 */
public class BingoMasterBoard {

    int maxNumber;
    int currentNumber;

    ArrayList<Integer> fullList;

    /**
     * Constructor setting up the bingo master
     * board to keep track of state.
     * @param maxNumber Maximum numbers available
     */
    BingoMasterBoard(int maxNumber){
        this.maxNumber = maxNumber;
        currentNumber = 0;


        fullList = new ArrayList<>();
        for(int i=0; i < maxNumber; i++){
            fullList.add(i+1); //since don't want 0
        }
        //shuffle so we can pick a random ball at a single time
        shuffle(fullList);
    }

    /**
     * This must pick a random number that hasn't
     * been called before.
     */
    public int pickNumber(){
        int ball = fullList.get(currentNumber);
        currentNumber ++;

        return ball;

    }


}
