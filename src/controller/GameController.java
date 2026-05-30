package controller;

import model.Game;

public class GameController {
    public Game startGame() {
        return null;
    }

    public void display(Game game) {
        game.getBoard().display();
    }
}
