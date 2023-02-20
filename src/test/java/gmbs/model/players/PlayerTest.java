package gmbs.model.players;

import gmbs.model.vo.Card;
import gmbs.model.first_class_collection.CardHand;
import gmbs.model.vo.UserName;
import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class PlayerTest {

    private static final Card firstCard = new Card(CardSuits.SPADE, BlackJackValue.ACE);
    private static final Card secondCard = new Card(CardSuits.HEART, BlackJackValue.ACE);
    private static final CardHand beforeHitHand = CardHand.of(List.of(firstCard, secondCard));
    private static final CardHand hitDraw = CardHand.of(List.of(new Card(CardSuits.DIAMOND, BlackJackValue.ACE)));

    @ParameterizedTest
    @DisplayName("card를 인자로 받아 hit하면 인자로 받은 card를 포함하는 Player를 생성")
    @MethodSource("players")
    void hit(final Player player) {
        //when
        CardHand afterHitHand = player.draw(hitDraw)
                .getCardHand();
        //then
        assertThat(afterHitHand.getCards()).containsAll(beforeHitHand.getCards());
    }

    private static Stream<Arguments> players() {
        return Stream.of(
                Arguments.of(new User(new UserName("user"), beforeHitHand)),
                Arguments.of(new Dealer(beforeHitHand))
                );
    }
}