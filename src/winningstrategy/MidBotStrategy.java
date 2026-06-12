package winningstrategy;

import model.Board;
import model.Cell;
import model.Move;
import model.Player;
import model.enums.CellState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MidBotStrategy implements BotPlayingStrategy {
    private final Random random = new Random();

    @Override
    public Move makeMove(Board board, Player player) {
        List<Cell> emptyCells = new ArrayList<>();

        for (List<Cell> cells : board.getCells()) {
            for (Cell cell : cells) {
                if (cell.getCellState() == CellState.EMPTY) {
                    emptyCells.add(cell);
                }
            }
        }

        if (emptyCells.isEmpty()) {
            return null;
        }

        int randomIndex = random.nextInt(emptyCells.size());
        return new Move(emptyCells.get(randomIndex), player);
    }
}
