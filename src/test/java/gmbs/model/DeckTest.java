package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DeckTest {

    @Test
    @DisplayName("card리스트를 통해 생성된 deck을 draw시 card 한 장을 반환하는지 확인")
    void draw() {
        //given
        List<Card> cards = List.of(new Card(CardSuits.HEART, BlackJackValue.ACE),new Card(CardSuits.HEART, BlackJackValue.JACK));
        Deck deck = new Deck(cards);
        //when
        Card card = deck.draw();
        //then
        assertThat(card).isIn(cards);
    }
}