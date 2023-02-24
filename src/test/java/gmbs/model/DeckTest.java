package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;
import gmbs.model.vo.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

class DeckTest {

    @Test
    @DisplayName("주어진 수 만큼 deck에서 카드를 draw하는지 확인")
    void draw() {
        //given
        Card heartAce = new Card(CardSuits.HEART, BlackJackValue.ACE);
        Card heartJack = new Card(CardSuits.HEART, BlackJackValue.JACK);
        Card heartQueen = new Card(CardSuits.HEART, BlackJackValue.QUEEN);
        Card heartKing = new Card(CardSuits.HEART, BlackJackValue.KING);
        List<Card> deckCards = List.of(heartAce, heartJack, heartQueen, heartKing);
        Deck deck = new Deck(deckCards);
        int drawCount = 2;
        //when
        List<Card> drawCards = deck.draw(drawCount)
                .getCards();
        //then
        assertThat(drawCards).hasSize(drawCount);
        assertThat(deckCards).containsAll(drawCards);
    }
}