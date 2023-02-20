package gmbs.model;

import gmbs.model.black_jack_enum.Result;

import java.util.Objects;

public class BlackJackResult {

    private final Result result;
    private final int resultHandSum;

    public BlackJackResult(final Result result, final int resultHandSum) {
        this.result = result;
        this.resultHandSum = resultHandSum;
    }

    public Result getResult() {
        return result;
    }

    public int getResultHandSum() {
        return resultHandSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlackJackResult that = (BlackJackResult) o;
        return resultHandSum == that.resultHandSum && result == that.result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(result, resultHandSum);
    }
}
