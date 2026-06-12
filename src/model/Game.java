package model;

import model.enums.CellState;
import model.enums.GameStatus;
import winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> players;
    private Player winner;
    private List<Move> moves;
    private GameStatus gameStatus;
    private int nextPlayerToPlay;
    private List<WinningStrategy> winningStrategies;

    public Game(int size,
                List<Player> players,
                List<WinningStrategy> winningStrategies) {
        this.board = new Board(size);
        this.players = players;
        this.moves = new ArrayList<>();
        this.gameStatus = GameStatus.IN_PROGRESS;
        this.nextPlayerToPlay = 0;
        this.winningStrategies = winningStrategies;

    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getNextPlayerToPlay() {
        return nextPlayerToPlay;
    }

    public void setNextPlayerToPlay(int nextPlayerToPlay) {
        this.nextPlayerToPlay = nextPlayerToPlay;
    }

    public void makeMove() {

        Player currentPlayer = players.get(nextPlayerToPlay);

        System.out.println("It's Player " + currentPlayer.getName() + "  a.k.a " + currentPlayer.getSymbol().getSymbol_name() + "'s turn");

        Move move = currentPlayer.makeMove(board);
        while (!validate(move)) {
            System.out.println("Invalid move. Please try again.");
            move = currentPlayer.makeMove(board);
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        Cell cell = board.getCells().get(row).get(col);
        cell.setPlayer(currentPlayer);
        cell.setCellState(CellState.FILLED);

        moves.add(move);

        nextPlayerToPlay = (++nextPlayerToPlay) % players.size();

        if(checkWinner(move)){
            this.winner = currentPlayer;
            this.gameStatus =  GameStatus.ENDED;
        }
        else if(moves.size() == board.getSize() * board.getSize()){
            this.gameStatus = GameStatus.DRAWN;
        }
    }

    private boolean checkWinner(Move move) {
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWinner(move)){
                return true;
            }
        }
        return false;
    }

    private boolean validate(Move move) {
        if (move == null || move.getCell() == null) {
            return false;
        }

        if (move.getPlayer() != players.get(nextPlayerToPlay)) {
            return false;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        if(row < 0 || row >= board.getSize() || col < 0 || col >= board.getSize()) {
            return false;
        }

        Cell cell = board.getCells().get(row).get(col);
        return cell.getCellState() == CellState.EMPTY;

    }
}
