package gmbs.model.first_class_collection;

import gmbs.model.vo.Card;

import java.util.ArrayList;
import java.util.List;

public class CardHand {

    private final List<Card> cards;

    public static CardHand of(final List<Card> cards) {
        return new CardHand(cards);
    }

    public static CardHand from(final CardHand presentHand, CardHand drawHand) {
        List<Card> addedCards = new ArrayList<>(presentHand.getCards());
        addedCards.addAll(drawHand.getCards());
        return new CardHand(addedCards);
    }

    private CardHand(final List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int aceCount() {
        return cards.stream()
                .filter(Card::isAce)
                .toList()
                .size();
    }
}
