package winningstrategy;

import model.Board;
import model.Cell;
import model.Move;
import model.Player;
import model.enums.CellState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GodBotStrategy implements BotPlayingStrategy {
    private final Random random = new Random();

    @Override
    public Move makeMove(Board board, Player player) {
        List<Cell> emptyCells = getEmptyCells(board);

        if (emptyCells.isEmpty()) {
            return null;
        }

        for (Cell cell : emptyCells) {
            if (isWinningMove(board, cell, player)) {
                return new Move(cell, player);
            }
        }

        Player opponent = findOpponent(board, player);
        if (opponent != null) {
            for (Cell cell : emptyCells) {
                if (isWinningMove(board, cell, opponent)) {
                    return new Move(cell, player);
                }
            }
        }

        if (board.getSize() == 3 && opponent != null) {
            return new Move(findBestMoveUsingMinimax(board, player, opponent), player);
        }

        Cell centerCell = getCenterCell(board);
        if (centerCell != null && centerCell.getCellState() == CellState.EMPTY) {
            return new Move(centerCell, player);
        }

        return new Move(emptyCells.get(random.nextInt(emptyCells.size())), player);
    }

    private List<Cell> getEmptyCells(Board board) {
        List<Cell> emptyCells = new ArrayList<>();

        for (List<Cell> cells : board.getCells()) {
            for (Cell cell : cells) {
                if (cell.getCellState() == CellState.EMPTY) {
                    emptyCells.add(cell);
                }
            }
        }

        return emptyCells;
    }

    private Player findOpponent(Board board, Player player) {
        for (List<Cell> cells : board.getCells()) {
            for (Cell cell : cells) {
                if (cell.getCellState() == CellState.FILLED && cell.getPlayer() != player) {
                    return cell.getPlayer();
                }
            }
        }

        return null;
    }

    private Cell getCenterCell(Board board) {
        if (board.getSize() % 2 == 0) {
            return null;
        }

        int center = board.getSize() / 2;
        return board.getCells().get(center).get(center);
    }

    private Cell findBestMoveUsingMinimax(Board board, Player botPlayer, Player opponent) {
        int bestScore = Integer.MIN_VALUE;
        Cell bestCell = getEmptyCells(board).get(0);

        for (Cell cell : getEmptyCells(board)) {
            makeTemporaryMove(cell, botPlayer);
            int score = minimax(board, false, botPlayer, opponent);
            undoTemporaryMove(cell);

            if (score > bestScore) {
                bestScore = score;
                bestCell = cell;
            }
        }

        return bestCell;
    }

    private int minimax(Board board, boolean isBotTurn, Player botPlayer, Player opponent) {
        if (hasWon(board, botPlayer)) {
            return 1;
        }

        if (hasWon(board, opponent)) {
            return -1;
        }

        List<Cell> emptyCells = getEmptyCells(board);
        if (emptyCells.isEmpty()) {
            return 0;
        }

        if (isBotTurn) {
            int bestScore = Integer.MIN_VALUE;
            for (Cell cell : emptyCells) {
                makeTemporaryMove(cell, botPlayer);
                bestScore = Math.max(bestScore, minimax(board, false, botPlayer, opponent));
                undoTemporaryMove(cell);
            }
            return bestScore;
        }

        int bestScore = Integer.MAX_VALUE;
        for (Cell cell : emptyCells) {
            makeTemporaryMove(cell, opponent);
            bestScore = Math.min(bestScore, minimax(board, true, botPlayer, opponent));
            undoTemporaryMove(cell);
        }
        return bestScore;
    }

    private boolean isWinningMove(Board board, Cell cell, Player player) {
        makeTemporaryMove(cell, player);
        boolean won = hasWon(board, player);
        undoTemporaryMove(cell);
        return won;
    }

    private void makeTemporaryMove(Cell cell, Player player) {
        cell.setPlayer(player);
        cell.setCellState(CellState.FILLED);
    }

    private void undoTemporaryMove(Cell cell) {
        cell.setPlayer(null);
        cell.setCellState(CellState.EMPTY);
    }

    private boolean hasWon(Board board, Player player) {
        int size = board.getSize();

        for (int row = 0; row < size; row++) {
            boolean rowWon = true;
            for (int col = 0; col < size; col++) {
                rowWon = rowWon && board.getCells().get(row).get(col).getPlayer() == player;
            }
            if (rowWon) {
                return true;
            }
        }

        for (int col = 0; col < size; col++) {
            boolean colWon = true;
            for (int row = 0; row < size; row++) {
                colWon = colWon && board.getCells().get(row).get(col).getPlayer() == player;
            }
            if (colWon) {
                return true;
            }
        }

        boolean mainDiagonalWon = true;
        boolean antiDiagonalWon = true;
        for (int i = 0; i < size; i++) {
            mainDiagonalWon = mainDiagonalWon && board.getCells().get(i).get(i).getPlayer() == player;
            antiDiagonalWon = antiDiagonalWon && board.getCells().get(i).get(size - 1 - i).getPlayer() == player;
        }

        return mainDiagonalWon || antiDiagonalWon;
    }
}
