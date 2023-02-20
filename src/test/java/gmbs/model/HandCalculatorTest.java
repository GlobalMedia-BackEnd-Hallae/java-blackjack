package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import gmbs.model.players.Dealer;
import gmbs.model.players.Player;
import gmbs.model.players.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class HandCalculatorTest {

    private static final Card king = new Card(CardSuits.SPADE, BlackJackValue.KING);
    private static final Card ace = new Card(CardSuits.SPADE, BlackJackValue.ACE);
    private static final Card seven = new Card(CardSuits.SPADE, BlackJackValue.SEVEN);
    private static final Card six = new Card(CardSuits.SPADE, BlackJackValue.SIX);
    private static final Card two = new Card(CardSuits.SPADE, BlackJackValue.TWO);
    private static final HandCalculator calculator = new HandCalculator();

    @ParameterizedTest
    @DisplayName("player를 인자로 받아 갖고 있는 card를 확인하여 hit 할 수 있는지 확인")
    @MethodSource("playerHit")
    void canHit(Player player, boolean expected) {
        //when
        boolean actual = calculator.canHit(player);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> playerHit() {
        return Stream.of(
                Arguments.of(createDealer(List.of(king, seven)), false),
                Arguments.of(createDealer(List.of(king, six)), true),
                Arguments.of(createDealer(List.of(king, ace)), false),
                Arguments.of(createUser(List.of(king, seven)), true),
                Arguments.of(createUser(List.of(king, six)), true),
                Arguments.of(createUser(List.of(king, king)), true),
                Arguments.of(createUser(List.of(king, ace)), false)
        );
    }

    @ParameterizedTest
    @DisplayName("player 손패의 카드 합을 계산한다")
    @MethodSource("hands")
    void handSum(Player player, int cardSumExpected) {
        //when
        int actual = calculator.sumHand(player);
        //then
        assertThat(actual).isEqualTo(cardSumExpected);
    }

    private static Stream<Arguments> hands() {
        return Stream.of(
                Arguments.of(createDealer(List.of(six, six)), 12),
                Arguments.of(createDealer(List.of(ace, king)), 21),
                Arguments.of(createDealer(List.of(ace, ace, six)), 18),
                Arguments.of(createUser(List.of(ace, ace, ace, six)), 19),
                Arguments.of(createDealer(List.of(ace, ace, ace, ace)), 14),
                Arguments.of(createUser(List.of(king, king, six)), 26),
                Arguments.of(createUser(List.of(king, king, ace)), 21),
                Arguments.of(createUser(List.of(ace, six)), 17),
                Arguments.of(createUser(List.of(two, two, two, ace, king, king)), 27)
        );
    }

    private static Dealer createDealer(List<Card> cards) {
        return new Dealer(CardHand.of(cards));
    }

    private static User createUser(List<Card> cards) {
        return new User("testUser", CardHand.of(cards));
    }
}