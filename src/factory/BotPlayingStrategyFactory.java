package factory;

import model.enums.BotDifficultyLevel;
import winningstrategy.BotPlayingStrategy;
import winningstrategy.GodBotStrategy;
import winningstrategy.MidBotStrategy;
import winningstrategy.NoobBotStrategy;

public class BotPlayingStrategyFactory {

    public static BotPlayingStrategy getStrategy(BotDifficultyLevel botDifficultyLevel)
    {
        return switch (botDifficultyLevel) {
            case NOOB -> new NoobBotStrategy();
            case MID -> new MidBotStrategy();
            case GOD_MODE -> new GodBotStrategy();
        };
    }
}
