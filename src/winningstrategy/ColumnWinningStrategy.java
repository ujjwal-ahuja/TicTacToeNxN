package winningstrategy;

import model.Move;

import java.util.HashMap;
import java.util.Map;

public class ColumnWinningStrategy implements WinningStrategy {

    private final int size;
    private final Map<String, Integer>[] columnMapArr;

    public ColumnWinningStrategy(int size){
        this.size = size;
        columnMapArr = new HashMap[size];

        for(int i=0; i<size; i++){
            columnMapArr[i] = new HashMap<>();
        }
    }


    @Override
    public boolean checkWinner(Move move) {
        final int column = move.getCell().getColumn();
        final String symbolName = move.getPlayer().getSymbol().getSymbol_name();

        columnMapArr[column].put(symbolName, columnMapArr[column].getOrDefault(symbolName, 0) + 1);
        return size == columnMapArr[column].get(symbolName);
    }
}
