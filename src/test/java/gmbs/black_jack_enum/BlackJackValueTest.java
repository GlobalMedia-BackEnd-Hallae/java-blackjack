package gmbs.black_jack_enum;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class BlackJackValueTest {

    @Test
    @DisplayName("hasAlternativeValue가 false 일 때 alternatvieValue를 호출하면 예외를 발생")
    void exceptionByAlternativeValue() {
        assertThatThrownBy(BlackJackCard.JACK::alternativeValue);
    }
}