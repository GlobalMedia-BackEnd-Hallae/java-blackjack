package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HandCheckerTest {

    @ParameterizedTest
    @DisplayName("player 손패의 카드 합을 계산한다")
    @MethodSource("hands")
    void handSum(CardHand hand, int cardSumExpected) {
        //given
        HandChecker checker = new HandChecker();
        //when
        int actual = checker.handSum(hand);
        //then
        assertThat(actual).isEqualTo(cardSumExpected);
    }

    private static Stream<Arguments> hands() {
        Card spadeAce = new Card(CardSuits.SPADE, BlackJackValue.ACE);
        Card heartAce = new Card(CardSuits.HEART, BlackJackValue.ACE);
        Card diamondAce = new Card(CardSuits.DIAMOND, BlackJackValue.ACE);
        Card clubAce = new Card(CardSuits.CLUB, BlackJackValue.ACE);
        Card spadeQueen = new Card(CardSuits.SPADE, BlackJackValue.QUEEN);
        Card spadeKing = new Card(CardSuits.SPADE, BlackJackValue.KING);
        Card spadeNine = new Card(CardSuits.SPADE, BlackJackValue.NINE);
        return Stream.of(
                Arguments.of(CardHand.of(List.of(spadeAce, heartAce)), 12),
                Arguments.of(CardHand.of(List.of(spadeAce, heartAce ,spadeQueen)), 12),
                Arguments.of(CardHand.of(List.of(spadeAce, spadeQueen)), 21),
                Arguments.of(CardHand.of(List.of(spadeAce, heartAce, diamondAce, clubAce)), 14),
                Arguments.of(CardHand.of(List.of(spadeKing, spadeQueen, spadeNine)), 29),
                Arguments.of(CardHand.of(List.of(spadeKing, spadeQueen, spadeAce)), 21),
                Arguments.of(CardHand.of(List.of(spadeKing, spadeQueen)), 20)
        );
    }
}