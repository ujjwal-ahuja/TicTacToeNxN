import controller.GameController;
import model.Game;
import model.enums.GameStatus;

public class GameClient {
    public static void main(String[] args) {

        GameController gameController = new GameController();
        Game game = gameController.startGame();

        gameController.display(game);

        while (game.getGameStatus() == GameStatus.IN_PROGRESS){
            //move
            //display board
            //check winner
            // if winner true -->
            //set status --> game finished
        }

    }
}