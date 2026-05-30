package winningstrategy;

import model.Move;

import java.util.HashMap;

public class DigonalWinningStrategy implements WinningStrategy{

    private int size;
    private HashMap<String, Integer>[] digonalMapArr;

    public DigonalWinningStrategy(int size){
        this.size = size;
        digonalMapArr = new HashMap[2];

        digonalMapArr[0] = new HashMap<>();
        digonalMapArr[1] = new HashMap<>();
    }


    @Override
    public boolean checkWinner(Move move) {

        final int row = move.getCell().getRow();
        final int column = move.getCell().getColumn();
        final String symbolName = move.getPlayer().getSymbol().getSymbol_name();

        boolean won = false;

        //Main Digonal
        if(row == column){
            digonalMapArr[0].put(symbolName, digonalMapArr[0].getOrDefault(symbolName, 0)+ 1);
            won = won || size == digonalMapArr[0].get(symbolName);
        }

        //Cross Digonal
        if(row+column == size-1){
            digonalMapArr[1].put(symbolName, digonalMapArr[1].getOrDefault(symbolName, 0)+ 1);
            won = won || size == digonalMapArr[1].get(symbolName);
        }

        return won;
    }
}
