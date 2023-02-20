package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import gmbs.model.players.Dealer;
import gmbs.model.players.Player;
import gmbs.model.players.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlackJackManagerTest {

    private static final Card king = new Card(CardSuits.SPADE, BlackJackValue.KING);
    private static final Card six = new Card(CardSuits.SPADE, BlackJackValue.SIX);
    private static final Card hitDraw = new Card(CardSuits.SPADE, BlackJackValue.SEVEN);
    private final BlackJackManager manager = new BlackJackManager(new Deck(List.of(hitDraw)));

    @ParameterizedTest
    @DisplayName("player를 인자로 받아 hit시 card가 추가된 player를 반환")
    @MethodSource("players")
    void hit(final Player player) {
        //when
        List<Card> afterHitCards = manager.hit(player)
                .getCardHand()
                .getCards();
        //then
        assertThat(hitDraw).isIn(afterHitCards);
    }

    private static Stream<Arguments> players() {
        return Stream.of(
                Arguments.of(createDealer(List.of(six, six))),
                Arguments.of(createUser(List.of(king, king)))
        );
    }

    private static Dealer createDealer(final List<Card> cards) {
        return new Dealer(CardHand.of(cards));
    }

    private static User createUser(final List<Card> cards) {
        return new User(new UserName("testUser"), CardHand.of(cards));
    }

    @Test
    @DisplayName("dealer를 생성하는지 확인")
    void createDealer() {
        assertThat(manager.createDealer()).isInstanceOf(Dealer.class);
    }

    @Test
    @DisplayName("user를 생성하는지 확인")
    void createUser() {
        assertThat(manager.createUser(new UserName("userName"))).isInstanceOf(User.class);
    }
}