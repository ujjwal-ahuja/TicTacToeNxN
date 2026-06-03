package controller;

import model.Game;
import model.Player;
import winningstrategy.WinningStrategy;

import java.util.List;

public class GameController {

    public Game startGame(int size,
                          List<Player> players,
                          List<WinningStrategy> winningStrategies) {
        return new Game(size, players, winningStrategies);
    }

    public void display(Game game) {
        game.getBoard().display();
    }

    public Object getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }

    public void makeMove(Game game) {
        game.makeMove();
    }
}
