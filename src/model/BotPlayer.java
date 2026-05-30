package model;

import model.enums.BotDifficultyLevel;
import model.enums.PlayerType;

public class BotPlayer extends Player {

    private BotDifficultyLevel botDifficultyLevel;

    public BotPlayer(BotDifficultyLevel botDifficultyLevel,
                     int id,
                     String name,
                     Symbol symbol,
                     PlayerType playerType){
        super(id, name, symbol, playerType);
        this.botDifficultyLevel = botDifficultyLevel;
    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
