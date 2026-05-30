package winningstrategy;

import model.Move;

import java.util.HashMap;
import java.util.Map;

public class RowWinningStrategy implements WinningStrategy {

    int size;
    private Map<String, Integer>[] rowMapArr;

    public RowWinningStrategy(int size){
        this.size = size;
        rowMapArr = new HashMap[size];

        for(int i=0; i<size; i++){
            rowMapArr[i] = new HashMap<>();
        }
    }

    @Override
    public boolean checkWinner(Move move) {

        final int row = move.getCell().getRow();
        final String symbolName = move.getPlayer().getSymbol().getSymbol_name();

        rowMapArr[row].put(symbolName, rowMapArr[row].getOrDefault(symbolName, 0) + 1);
        return size == rowMapArr[row].get(symbolName);
    }

























//Ugly version but in 2 lines
//    @Override
//    public boolean checkWinner(Move move) {
//        rowMapArr[move.getCell().getRow()].put(move.getPlayer().getSymbol().getSymbol_name(), rowMapArr[move.getCell().getRow()].getOrDefault(move.getPlayer().getSymbol().getSymbol_name(), 0) + 1);
//        return rowMapArr[move.getCell().getRow()].get(move.getPlayer().getSymbol().getSymbol_name()) == size;
//    }


//first draft
//    @Override
//    public boolean checkWinner(Move move) {
//        int currentRow = move.getCell().getRow();
//        Map<String, Integer> currentRowMap = rowMapArr[currentRow];
//        String currSymbol = move.getPlayer().getSymbol().getSymbol_name();
//        if(!currentRowMap.containsKey(currSymbol))
//            currentRowMap.put(currSymbol,1);
//        else
//            currentRowMap.put(currSymbol, currentRowMap.get(currSymbol)+1);
//
//        if(currentRowMap.get(currSymbol) == size)return true;
//
//        return false;
//
//    }
}
