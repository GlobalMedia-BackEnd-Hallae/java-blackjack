package gmbs.model.vo;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class CardTest {

    @ParameterizedTest
    @DisplayName("card가 ace면 true 반환")
    @MethodSource("cards")
    void isAce(final Card card, final boolean expected) {
        //when
        boolean actual = card.isAce();
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> cards() {
        return Stream.of(
                Arguments.of(new Card(CardSuits.SPADE, BlackJackValue.ACE), true),
                Arguments.of(new Card(CardSuits.SPADE, BlackJackValue.JACK), false)
        );
    }
}