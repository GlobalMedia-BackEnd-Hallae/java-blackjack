package gmbs.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class UserInputTest {

    @ParameterizedTest
    @DisplayName("y를 통해 생성하면 true 반환")
    @MethodSource("inputs")
    void isYes(final UserInput input, final boolean expected) {
        //when
        boolean actual = input.isYes();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> inputs() {
        return Stream.of(
                Arguments.of(new UserInput("y"), true),
                Arguments.of(new UserInput("n"), false)
        );
    }

    @Test
    @DisplayName("y 또는 n 이 아닌 문자로 생성 시 예외 발생")
    void exceptionByWrongInput() {
        assertThatThrownBy(() -> new UserInput("not n/y")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[err] wrong input");
    }
}