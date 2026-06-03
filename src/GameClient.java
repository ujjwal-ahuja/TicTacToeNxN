import controller.GameController;
import model.Game;
import model.HumanPlayer;
import model.Player;
import model.Symbol;
import model.enums.GameStatus;
import winningstrategy.ColumnWinningStrategy;
import winningstrategy.DigonalWinningStrategy;
import winningstrategy.RowWinningStrategy;
import winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class GameClient {
    public static void main(String[] args) {

        GameController gameController = new GameController();

        final int size = 3;

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(007, "Kiyoya", new Symbol("K"), 17));
        players.add(new HumanPlayer(143, "Zinga", new Symbol("Z"), 16));
//        players.add(new HumanPlayer(999, "Rayuga", new Symbol("D"), 17));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy(size));
        winningStrategies.add(new ColumnWinningStrategy(size));
        winningStrategies.add(new DigonalWinningStrategy(size));



        Game game = gameController.startGame(size, players, winningStrategies);


        while (game.getGameStatus().equals(GameStatus.IN_PROGRESS)){
            gameController.display(game);


            gameController.makeMove(game);

        }

        if(gameController.getGameStatus(game).equals(GameStatus.ENDED)){
            Player winner = gameController.getWinner(game);
            System.out.println("And the winner iiiiiissssssss " + winner.getName() + " " + " aka " + winner.getSymbol().getSymbol_name());
        }
        else {
            System.out.println("It's a STALEMATE :(");
        }

    }
}