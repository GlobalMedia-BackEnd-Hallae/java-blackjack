package gmbs.domain.participant;

import gmbs.domain.card.Card;
import gmbs.domain.card.CardNumber;
import gmbs.domain.card.CardPattern;
import gmbs.domain.card.Cards;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static gmbs.utils.TestUtil.getCards;
import static org.assertj.core.api.Assertions.assertThat;

class DealerTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("턴 강제 종료 여부")
    void Dealer(String comment, Cards cards, boolean expect) {
        // given
        Dealer dealer = new Dealer(new Name("딜러"), cards);

        // then
        assertThat(dealer.isFinished()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("합계 22인 경우 true", getCards(CardNumber.TWO, CardNumber.QUEEN, CardNumber.KING),
                        true),
                Arguments.arguments("합계 17인 경우 true", getCards(CardNumber.SEVEN, CardNumber.QUEEN), true),
                Arguments.arguments("합계 15인 경우 false", getCards(CardNumber.QUEEN, CardNumber.FIVE), false)
        );
    }

    @Test
    @DisplayName("딜러 draw 확인")
    void drawCard() {
        // given
        Dealer dealer = new Dealer(new Name("딜러"), getCards(CardNumber.QUEEN));
        Card newCard = new Card(CardNumber.ACE, CardPattern.CLOVER);

        // when
        dealer.drawCard(newCard);

        // then
        assertThat(dealer.getCards().getValue()).contains(newCard);
    }
}
