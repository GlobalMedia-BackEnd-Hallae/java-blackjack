package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import gmbs.model.black_jack_enum.Result;
import gmbs.model.players.Dealer;
import gmbs.model.players.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class WinnerCheckerTest {

    private static final Card spadeAce = new Card(CardSuits.SPADE, BlackJackValue.ACE);
    private static final Card clubAce = new Card(CardSuits.CLUB, BlackJackValue.ACE);
    private static final Card spadeQueen = new Card(CardSuits.SPADE, BlackJackValue.QUEEN);
    private static final Card spadeSix = new Card(CardSuits.SPADE, BlackJackValue.SIX);
    private static final CardHand bust = CardHand.of(List.of(spadeQueen, spadeQueen, spadeSix));
    private static final CardHand blackJack = CardHand.of(List.of(spadeAce, spadeQueen));
    private static final CardHand sixteen = CardHand.of(List.of(spadeQueen, spadeSix));
    private static final CardHand twelve = CardHand.of(List.of(spadeAce, clubAce));

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

    @Test
    @DisplayName("target의 상대player에 따른 결과들을 비교하여 반환")
    void getComparisonTargetResults() {
        //given
        Dealer target = new Dealer(sixteen);
        User winUser = new User("win", blackJack);
        User loseUser = new User("lose", twelve);
        User drawUser = new User("draw", sixteen);
        WinnerChecker checker = new WinnerChecker(target);
        checker.getResult(winUser);
        checker.getResult(loseUser);
        checker.getResult(drawUser);
        //when
        Map<Result, Integer> targetResult = checker.getTargetResults();
        //then
        assertThat(targetResult).containsEntry(Result.WIN, 1)
                .containsEntry(Result.LOSE, 1)
                .containsEntry(Result.DRAW, 1);
    }
}