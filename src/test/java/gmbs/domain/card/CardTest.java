package gmbs.domain.card;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatNoException;


class CardTest {
    @Test
    void create() {
        // then
        assertThatNoException().isThrownBy(() -> new Card(CardNumber.ACE, CardPattern.CLOVER));
    }
}
