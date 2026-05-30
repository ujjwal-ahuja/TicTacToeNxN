package model;

import model.enums.GameStatus;
import model.enums.PlayerType;
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
}
