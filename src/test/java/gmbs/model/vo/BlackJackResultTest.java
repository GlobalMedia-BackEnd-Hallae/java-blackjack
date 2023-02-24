package gmbs.model.vo;

import gmbs.model.black_jack_enum.Result;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class BlackJackResultTest {

    @Test
    @DisplayName("Result 하나와 정수를 통해 BlackJackResult를 생성하는지 확인")
    void singleResultFrom() {
        //given
        Result result = Result.WIN;
        int sum = 19;
        //when
        BlackJackResult blackJackResult = BlackJackResult.singleResultOf(result, sum);
        //then
        assertThat(blackJackResult.getResultsData())
                .containsEntry(Result.DRAW, 0)
                .containsEntry(Result.LOSE, 0)
                .containsEntry(Result.WIN, 1);
        assertThat(blackJackResult.getResultHandSum()).isEqualTo(sum);
    }

    @Test
    @DisplayName("Result의 map과 정수를 통해 BlackJackResult를 생성하는지 확인")
    void resultsFrom() {
        //given
        Map<Result, Integer> results = new HashMap<>();
        results.put(Result.WIN, 1);
        results.put(Result.DRAW, 2);
        results.put(Result.LOSE, 3);
        int sum = 19;
        //when
        BlackJackResult blackJackResult = BlackJackResult.resultsOf(results, sum);
        //then
        assertThat(blackJackResult.getResultsData())
                .containsEntry(Result.WIN, 1)
                .containsEntry(Result.DRAW, 2)
                .containsEntry(Result.LOSE, 3);
        assertThat(blackJackResult.getResultHandSum()).isEqualTo(sum);
    }
}