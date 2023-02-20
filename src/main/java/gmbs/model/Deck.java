package gmbs.model;

import gmbs.model.vo.Card;

import java.util.*;

public class Deck {

    private final Deque<Card> deckCards;

    public Deck(final List<Card> deckCards) {
        List<Card> cardsCopy = new ArrayList<>(deckCards);
        Collections.shuffle(cardsCopy);
        this.deckCards = new ArrayDeque<>(cardsCopy);
    }

    public CardHand draw(final int cardCount) {
        validateDrawSize(cardCount);
        List<Card> drawCards = new ArrayList<>();
        for(int i = 0; i< cardCount; i ++) {
            drawCards.add(deckCards.pop());
        }
        return CardHand.of(drawCards);
    }

    private boolean validateDrawSize(final int cardCount) {
        if(cardCount > deckCards.size() || cardCount <= 0) {
            throw new IllegalArgumentException("[err] draw cards out of range");
        }
        return true;
    }
}
