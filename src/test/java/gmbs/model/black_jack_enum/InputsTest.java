package gmbs.model.black_jack_enum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class InputsTest {

    @ParameterizedTest
    @DisplayName("inputValue에 따른 inputs 를 반환")
    @MethodSource("inputs")
    void find(String input, Inputs expected) {
        //when
        Inputs actual = Inputs.find(input);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("y", Inputs.YES),
                Arguments.of("n", Inputs.NO),
                Arguments.of("not y/n", Inputs.WRONG)
        );
    }
}