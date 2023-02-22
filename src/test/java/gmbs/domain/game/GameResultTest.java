package gmbs.domain.game;

import gmbs.domain.card.CardNumber;
import gmbs.domain.participant.Dealer;
import gmbs.domain.participant.Name;
import gmbs.domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static gmbs.utils.TestUtil.getCards;
import static org.assertj.core.api.Assertions.assertThat;

class GameResultTest {
    @ParameterizedTest(name = "{0}")
    @MethodSource("provideParameters")
    @DisplayName("플레이어 승리")
    void test(String comment, Player player, Dealer dealer) {
        // given
        GameResult gameResult = new GameResult(List.of(player), dealer);

        // then
        assertThat(gameResult.getMatchResult(player)).isEqualTo(MatchResult.WIN);
    }

    private static Stream<Arguments> provideParameters() {
        return Stream.of(
                Arguments.arguments("플레이어가 버스트가 아니고 딜러가 버스트인 경우",
                        new Player(new Name("abc"), getCards(CardNumber.ACE, CardNumber.NINE)),
                        new Dealer(new Name("딜러"), getCards(CardNumber.QUEEN, CardNumber.KING, CardNumber.JACK))),
                Arguments.arguments("둘 다 버스트가 아니고 딜러보다 숫자가 높은 경우",
                        new Player(new Name("abc"), getCards(CardNumber.KING, CardNumber.QUEEN)),
                        new Dealer(new Name("딜러"), getCards(CardNumber.QUEEN, CardNumber.NINE))),
                Arguments.arguments("플레이어만 블랙잭",
                        new Player(new Name("abc"), getCards(CardNumber.KING, CardNumber.ACE)),
                        new Dealer(new Name("딜러"), getCards(CardNumber.QUEEN, CardNumber.TEN, CardNumber.ACE)))
        );
    }

    @ParameterizedTest(name = "{3} 개수 -> {4}회")
    @MethodSource("provideParameters2")
    @DisplayName("딜러가 승리한 횟수")
    void dealer_count_test(Player player1, Player player2, Dealer dealer, MatchResult result, int expect) {
        // given
        List<Player> players = Arrays.asList(player1, player2);
        GameResult gameResult = new GameResult(players, dealer);

        // then
        assertThat(gameResult.calculateDealerMatchResultCount(result)).isEqualTo(expect);
    }

    private static Stream<Arguments> provideParameters2() {
        return Stream.of(
                Arguments.arguments(
                        new Player(new Name("abc1"), getCards(CardNumber.ACE, CardNumber.NINE)),
                        new Player(new Name("abc2"), getCards(CardNumber.EIGHT, CardNumber.NINE)),
                        new Dealer(new Name("딜러"), getCards(CardNumber.QUEEN, CardNumber.NINE)),
                        MatchResult.WIN,
                        1
                ),
                Arguments.arguments(
                        new Player(new Name("abc1"), getCards(CardNumber.ACE, CardNumber.NINE)),
                        new Player(new Name("abc2"), getCards(CardNumber.NINE, CardNumber.NINE)),
                        new Dealer(new Name("딜러"), getCards(CardNumber.EIGHT, CardNumber.NINE)),
                        MatchResult.LOSE,
                        2
                )
        );
    }
}
