package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import gmbs.model.black_jack_enum.Result;
import gmbs.model.first_class_collection.CardHand;
import gmbs.model.players.Dealer;
import gmbs.model.players.User;
import gmbs.model.vo.BlackJackResult;
import gmbs.model.vo.Card;
import gmbs.model.vo.UserName;
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
    private static final CardHand bust = CardHand.from(List.of(spadeQueen, spadeQueen, spadeSix));
    private static final CardHand blackJack = CardHand.from(List.of(spadeAce, spadeQueen));
    private static final CardHand sixteen = CardHand.from(List.of(spadeQueen, spadeSix));
    private static final CardHand twelve = CardHand.from(List.of(spadeAce, clubAce));

    @ParameterizedTest
    @DisplayName("target과 opponent 를 비교하여 opponent의 결과를 반환")
    @MethodSource("hands")
    void getResult(final CardHand dealerHand, final CardHand userHand, final BlackJackResult expected) {
        //given
        Dealer target = new Dealer(dealerHand);
        User opponent = new User(new UserName("user"), userHand);
        WinnerChecker checker = new WinnerChecker(target);
        //when
        BlackJackResult actual = checker.getResult(opponent);
        //then
        assertThat(actual).isEqualTo(expected);
    }

    private static Stream<Arguments> hands() {
        return Stream.of(
                Arguments.of(blackJack, blackJack, BlackJackResult.singleResultFrom(Result.DRAW, 21)),
                Arguments.of(bust, blackJack, BlackJackResult.singleResultFrom(Result.WIN, 21)),
                Arguments.of(bust, bust, BlackJackResult.singleResultFrom(Result.DRAW, 26)),
                Arguments.of(bust, sixteen, BlackJackResult.singleResultFrom(Result.WIN, 16)),
                Arguments.of(sixteen, bust, BlackJackResult.singleResultFrom(Result.LOSE, 26)),
                Arguments.of(sixteen, twelve, BlackJackResult.singleResultFrom(Result.LOSE, 12)),
                Arguments.of(twelve, sixteen, BlackJackResult.singleResultFrom(Result.WIN, 16))
        );
    }

    @Test
    @DisplayName("target의 상대player에 따른 결과들을 비교하여 반환")
    void getComparisonTargetResults() {
        //given
        UserName name = new UserName("any");
        Dealer target = new Dealer(sixteen);
        User winUser = new User(name, blackJack);
        User loseUser = new User(name, twelve);
        User drawUser = new User(name, sixteen);
        WinnerChecker checker = new WinnerChecker(target);
        checker.getResult(winUser);
        checker.getResult(loseUser);
        checker.getResult(drawUser);
        //when
        Map<Result, Integer> targetResult = checker.getTargetResult()
                .getResultsData();
        //then
        assertThat(targetResult).containsEntry(Result.WIN, 1)
                .containsEntry(Result.LOSE, 1)
                .containsEntry(Result.DRAW, 1);
    }
}