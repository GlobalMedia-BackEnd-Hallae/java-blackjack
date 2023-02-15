package gmbs.model.players;

import gmbs.model.Card;
import gmbs.model.CardHand;
import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class UserTest {

    @Test
    @DisplayName("card를 인자로 받아 hit하면 인자로 받은 card를 포함하는 user를 생성")
    void hit() {
        //given
        Card firstCard = new Card(CardSuits.SPADE, BlackJackValue.ACE);
        Card secondCard = new Card(CardSuits.HEART, BlackJackValue.ACE);
        Card hitCard = new Card(CardSuits.DIAMOND, BlackJackValue.ACE);
        CardHand defaultCardHand = CardHand.of(List.of(firstCard, secondCard));
        User user = new User("user", defaultCardHand);
        //when
        List<Card> afterHitCards = user.hit(hitCard)
                .getCardHand()
                .getCards();
        //then
        assertThat(hitCard).isIn(afterHitCards);
    }
}