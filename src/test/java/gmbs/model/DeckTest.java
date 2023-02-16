package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DeckTest {

//    @Test
//    @DisplayName("card리스트를 통해 생성된 deck을 draw시 card 한 장을 반환하는지 확인")
//    void draw() {
//        //given
//        List<Card> cards = List.of(new Card(CardSuits.HEART, BlackJackValue.ACE),new Card(CardSuits.HEART, BlackJackValue.JACK));
//        Deck deck = new Deck(cards);
//        //when
//        Card card = deck.draw();
//        //then
//        assertThat(card).isIn(cards);
//    }

    @Test
    @DisplayName("card리스트를 통해 생성된 deck을 draw시 card 한 장을 반환하는지 확인")
    void draw2() {
        //given
        Card heartAce = new Card(CardSuits.HEART, BlackJackValue.ACE);
        Card heartJack = new Card(CardSuits.HEART, BlackJackValue.JACK);
        Card heartQueen = new Card(CardSuits.HEART, BlackJackValue.QUEEN);
        Card heartKing = new Card(CardSuits.HEART, BlackJackValue.KING);
        List<Card> deckCards = List.of(heartAce, heartJack, heartQueen, heartKing);
        Deck deck = new Deck(deckCards);
        int drawCount = 2;
        //when
        List<Card> drawCards = deck.draw(drawCount).getCards();
        //then
        assertThat(drawCards).hasSize(drawCount);
        assertThat(deckCards).containsAll(drawCards);
    }
}