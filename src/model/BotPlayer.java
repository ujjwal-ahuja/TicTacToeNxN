package model;

import factory.BotPlayingStrategyFactory;
import model.enums.BotDifficultyLevel;
import model.enums.PlayerType;
import winningstrategy.BotPlayingStrategy;

public class BotPlayer extends Player {

    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;

    public BotPlayer(int id,
                     String name,
                     Symbol symbol,
                     BotDifficultyLevel botDifficultyLevel){
        super(id, name, symbol, PlayerType.BOT);
        this.botDifficultyLevel = botDifficultyLevel;
        this.botPlayingStrategy = BotPlayingStrategyFactory.getStrategy(botDifficultyLevel);
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move makeMove(Board board) {
        System.out.println("Its Bot's turn now, Understood ?? baka");
        return botPlayingStrategy.makeMove(board,  this);
    }
}
