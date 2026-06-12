package winningstrategy;

import model.Board;
import model.Cell;
import model.Move;
import model.Player;
import model.enums.CellState;

import java.util.List;

public class NoobBotStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        for(List<Cell> cells : board.getCells()){
            for (Cell cell : cells){
                if(cell.getCellState() == CellState.EMPTY){
                    return new Move(cell, player);
                }
            }
        }
        return null;
    }
}
