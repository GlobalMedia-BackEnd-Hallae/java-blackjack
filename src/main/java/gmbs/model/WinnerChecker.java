package gmbs.model;

import gmbs.model.black_jack_enum.Result;
import gmbs.model.players.Player;

public class WinnerChecker {

    private static final int BLACK_JACK = 21;
    private final HandCalculator calculator = new HandCalculator();
    private final int targetHandValue;

    public WinnerChecker(Player comparisonTarget) {
        targetHandValue = calculator.sumHand(comparisonTarget.getCardHand());
    }

    public Result getResult(Player player) {
        int userHandValue = calculator.sumHand(player.getCardHand());
        return getResultByValue(userHandValue);
    }

    private Result getResultByValue(int userHandValue) {
        if (isBust(targetHandValue) && !isBust(userHandValue)) {
            return Result.WIN;
        }
        if (!isBust(targetHandValue) && isBust(userHandValue)) {
            return Result.LOSE;
        }
        if (!isBust(targetHandValue) && !isBust(userHandValue)) {
            return getNoBustResult(userHandValue);
        }
        return Result.DRAW;
    }

    private Result getNoBustResult(int userHandValue) {
        if (targetHandValue > userHandValue) {
            return Result.LOSE;
        }
        if (targetHandValue < userHandValue) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    private boolean isBust(int sumValue) {
        return sumValue > BLACK_JACK;
    }

}
