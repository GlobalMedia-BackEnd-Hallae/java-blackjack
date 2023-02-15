package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import gmbs.model.black_jack_enum.Result;
import gmbs.model.players.Dealer;
import gmbs.model.players.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WinnerCheckerTest {

    @ParameterizedTest
    @DisplayName("생성 시 넘겨주는 player를 기준으로 cardHand를 비교하여 승,무,패를 반환")
    @MethodSource("hands")
    void getResult(CardHand dealerHand, CardHand userHand, Result expected) {
        //given
        Dealer dealer = new Dealer(dealerHand);
        User user = new User("user", userHand);
        WinnerChecker checker = new WinnerChecker(dealer);
        //when
        Result actual = checker.getResult(user);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> hands() {
        Card spadeAce = new Card(CardSuits.SPADE, BlackJackValue.ACE);
        Card clubAce = new Card(CardSuits.CLUB, BlackJackValue.ACE);
        Card spadeQueen = new Card(CardSuits.SPADE, BlackJackValue.QUEEN);
        Card spadeSix = new Card(CardSuits.SPADE, BlackJackValue.SIX);
        CardHand bust = CardHand.of(List.of(spadeQueen, spadeQueen, spadeSix));
        CardHand blackJack = CardHand.of(List.of(spadeAce, spadeQueen));
        CardHand sixteen = CardHand.of(List.of(spadeQueen, spadeSix));
        CardHand twelve = CardHand.of(List.of(spadeAce, clubAce));
        return Stream.of(
                Arguments.of(blackJack, blackJack, Result.DRAW),
                Arguments.of(bust, blackJack, Result.WIN),
                Arguments.of(bust, bust, Result.DRAW),
                Arguments.of(bust, sixteen, Result.WIN),
                Arguments.of(sixteen, bust, Result.LOSE),
                Arguments.of(sixteen, twelve, Result.LOSE),
                Arguments.of(twelve, sixteen, Result.WIN)
        );
    }
}