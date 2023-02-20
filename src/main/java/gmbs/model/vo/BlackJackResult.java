package gmbs.model.vo;

import gmbs.model.black_jack_enum.Result;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BlackJackResult {

    private final Map<Result, Integer> results;
    private final int resultHandSum;

    private BlackJackResult(final Map<Result, Integer> results, final int resultHandSum) {
        this.results = results;
        this.resultHandSum = resultHandSum;
    }

    public static BlackJackResult singleResultFrom(final Result result, final int resultHandSum) {
        Map<Result, Integer> results = Arrays.stream(Result.values())
                .collect(Collectors.toMap(Function.identity(), ignored -> 0));
        results.replace(result, 1);
        return new BlackJackResult(results, resultHandSum);
    }

    public static BlackJackResult resultsFrom(final Map<Result, Integer> results, final int resultHandSum) {
        return new BlackJackResult(results, resultHandSum);
    }

    public Map<Result, Integer> getResultsData() {
        return results;
    }

    public int getResultHandSum() {
        return resultHandSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackJackResult that = (BlackJackResult) o;
        return resultHandSum == that.resultHandSum && results.equals(that.results);
    }

    @Override
    public int hashCode() {
        return Objects.hash(results, resultHandSum);
    }
}
