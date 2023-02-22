package gmbs.domain.participant;

import gmbs.domain.card.Card;
import gmbs.domain.card.Cards;
import gmbs.domain.card.CardNumber;
import gmbs.domain.card.CardPattern;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static gmbs.utils.TestUtil.getCards;
import static org.assertj.core.api.Assertions.assertThat;

class PlayerTest {

    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("턴 강제 종료 여부")
    void player(String comment, Cards cards, boolean expect) {
        // given
        Player player = new Player(new Name("name"), cards);

        // then
        assertThat(player.isFinished()).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("합계 22인 경우 true", getCards(CardNumber.TWO, CardNumber.QUEEN, CardNumber.KING),
                        true),
                Arguments.arguments("합계 20인 경우 false", getCards(CardNumber.QUEEN, CardNumber.KING), false)
        );
    }

    @Test
    void drawCard() {
        // given
        Player player = new Player(new Name("name"), getCards(CardNumber.QUEEN));
        Card newCard = new Card(CardNumber.ACE, CardPattern.CLOVER);

        // when
        player.drawCard(newCard);

        // then
        assertThat(player.getCards().getValue()).contains(newCard);
    }
}
