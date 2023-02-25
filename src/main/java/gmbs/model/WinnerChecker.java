package gmbs.model;

import gmbs.model.black_jack_enum.Result;
import gmbs.model.players.Player;
import gmbs.model.vo.BlackJackResult;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WinnerChecker {

    private static final int BLACK_JACK = 21;
    private final int targetHandValue;
    private final Map<Result, Integer> targetResults;
    private final HandCalculator calculator = new HandCalculator();

    public WinnerChecker(final Player comparisonTarget) {
        targetHandValue = calculator.sumHand(comparisonTarget);
        targetResults = Arrays.stream(Result.values())
                .collect(Collectors.toMap(Function.identity(), ignored -> 0));
    }

    public BlackJackResult getResult(final Player opponent) {
        int opponentSumHand = calculator.sumHand(opponent);
        Result opponentResult = getResultByValue(opponentSumHand);
        updateComparisonTargetResults(opponentResult);
        return BlackJackResult.singleResultOf(opponentResult, opponentSumHand);
    }

    private Result getResultByValue(final int userHandValue) {
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

    private void updateComparisonTargetResults(final Result opponentResult) {
        Result targetResult = opponentResult.getOpponent();
        targetResults.replace(targetResult, targetResults.get(targetResult) + 1);
    }

    private boolean isBust(final int sumValue) {
        return sumValue > BLACK_JACK;
    }

    private Result getNoBustResult(final int userHandValue) {
        if (targetHandValue > userHandValue) {
            return Result.LOSE;
        }
        if (targetHandValue < userHandValue) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public BlackJackResult getTargetResult() {
        return BlackJackResult.resultsOf(targetResults, targetHandValue);
    }
}
