package gmbs.model.black_jack_enum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ResultTest {

    @ParameterizedTest
    @DisplayName("win lose draw 의 상대 결과를 반환")
    @MethodSource("results")
    void getOpponent(final Result selfResult, final Result opponentResultExpected) {
        //when
        Result actual = selfResult.getOpponent();
        //then
        assertThat(actual).isEqualTo(opponentResultExpected);
    }

    private static Stream<Arguments> results() {
        return Stream.of(
                Arguments.of(Result.WIN, Result.LOSE),
                Arguments.of(Result.LOSE, Result.WIN),
                Arguments.of(Result.DRAW, Result.DRAW)
        );
    }
}