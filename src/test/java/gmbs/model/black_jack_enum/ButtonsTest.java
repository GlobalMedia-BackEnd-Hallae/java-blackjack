package gmbs.model.black_jack_enum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class ButtonsTest {

    @ParameterizedTest
    @DisplayName("inputValue에 따른 inputs 를 반환")
    @MethodSource("inputs")
    void find(final String input, final Buttons expected) {
        //when
        Buttons actual = Buttons.find(input);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of("y", Buttons.YES),
                Arguments.of("n", Buttons.NO),
                Arguments.of("not y/n", Buttons.WRONG)
        );
    }
}