package winningstrategy;

import model.Move;

public interface WinningStrategy {
    public boolean checkWinner(Move move);
}
