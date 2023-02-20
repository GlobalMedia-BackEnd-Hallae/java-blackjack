package gmbs.model;

import gmbs.model.black_jack_enum.Result;
import gmbs.model.players.Player;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinnerChecker {

    private static final int BLACK_JACK = 21;
    private final HandCalculator calculator = new HandCalculator();
    private final int targetHandValue;
    private final Map<Result, Integer> targetResults;

    public WinnerChecker(Player comparisonTarget) {
        targetHandValue = calculator.sumHand(comparisonTarget.getCardHand());
        targetResults = Arrays.stream(Result.values())
                .collect(Collectors.toMap(Function.identity(), ignored -> 0));
    }

    public Result getResult(Player opponent) {
        int opponentSumHand = calculator.sumHand(opponent.getCardHand());
        Result opponentResult = getResultByValue(opponentSumHand);
        addComparisonTargetResults(opponentResult);
        return opponentResult;
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

    private boolean isBust(int sumValue) {
        return sumValue > BLACK_JACK;
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

    private void addComparisonTargetResults(Result opponentResult) {
        Result targetResult = opponentResult.getOpponent();
        targetResults.replace(targetResult, targetResults.get(targetResult) + 1);
    }

    public Map<Result, Integer> getTargetResults() {
        return targetResults;
    }
}
