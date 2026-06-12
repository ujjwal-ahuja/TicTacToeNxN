import controller.GameController;
import model.*;
import model.enums.BotDifficultyLevel;
import model.enums.GameStatus;
import winningstrategy.ColumnWinningStrategy;
import winningstrategy.DigonalWinningStrategy;
import winningstrategy.RowWinningStrategy;
import winningstrategy.WinningStrategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GameClient {
    public static void main(String[] args) {

        GameController gameController = new GameController();

        final int size = 3;

        List<Player> players = new ArrayList<>();
        players.add(new HumanPlayer(007, "Kiyoya", new Symbol("K"), 17));
        players.add(new BotPlayer(000, "BotZilla", new Symbol("B"), BotDifficultyLevel.MID));
//        players.add(new HumanPlayer(143, "Zinga", new Symbol("Z"), 16));
//        players.add(new HumanPlayer(999, "Rayuga", new Symbol("R"), 17));

        List<WinningStrategy> winningStrategies = new ArrayList<>();
        winningStrategies.add(new RowWinningStrategy(size));
        winningStrategies.add(new ColumnWinningStrategy(size));
        winningStrategies.add(new DigonalWinningStrategy(size));


        validateGameConfig(size, players, winningStrategies);

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

    private static void validateGameConfig(int size,
                                           List<Player> players,
                                           List<WinningStrategy> winningStrategies) {
        if (size < 3) {
            throw new IllegalArgumentException("Board size should be at least 3.");
        }

        if (players == null || players.size() < 2) {
            throw new IllegalArgumentException("At least 2 players are required.");
        }

        if (players.size() >= size) {
            throw new IllegalArgumentException("Players should be less than board size.");
        }

        if (winningStrategies == null || winningStrategies.isEmpty()) {
            throw new IllegalArgumentException("At least 1 winning strategy is required.");
        }

        validatePlayers(players);
    }

    private static void validatePlayers(List<Player> players) {
        Set<String> symbols = new HashSet<>();

        for (Player player : players) {
            if (player == null) {
                throw new IllegalArgumentException("Player cannot be null.");
            }

            if (player.getName() == null || player.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("Player name cannot be empty.");
            }

            if (player.getSymbol() == null ||
                    player.getSymbol().getSymbol_name() == null ||
                    player.getSymbol().getSymbol_name().trim().isEmpty()) {
                throw new IllegalArgumentException("Player symbol cannot be empty.");
            }

            String symbol = player.getSymbol().getSymbol_name().trim();
            if (symbols.contains(symbol)) {
                throw new IllegalArgumentException("Player symbols should be unique.");
            }

            symbols.add(symbol);
        }
    }
}
