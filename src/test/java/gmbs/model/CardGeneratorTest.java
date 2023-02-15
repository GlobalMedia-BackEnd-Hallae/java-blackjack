package gmbs.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CardGeneratorTest {

    @Test
    @DisplayName("모든 카드를 생성하는지 확인")
    void createWholeCards() {
        //given
        int expectedSize = 52;
        //when
        List<Card> cards = new CardGenerator().createWholeCards();
        //then
        assertThat(cards).hasSize(expectedSize);
    }
}