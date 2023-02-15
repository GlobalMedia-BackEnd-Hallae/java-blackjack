package gmbs.model;

import java.util.*;

public class Deck {

    private final Deque<Card> cards;

    public Deck(final List<Card> cards) {
        List<Card> cardsCopy = new ArrayList<>(cards);
        Collections.shuffle(cardsCopy);
        this.cards = new ArrayDeque<>(cardsCopy);
    }

    public Card draw() {
        return cards.pop();
    }
}
