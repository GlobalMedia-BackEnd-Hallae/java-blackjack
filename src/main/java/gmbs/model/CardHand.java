package gmbs.model;

import java.util.ArrayList;
import java.util.List;

public class CardHand {

    private final List<Card> cards;

    public static CardHand of(List<Card> cards) {
        return new CardHand(cards);
    }

    public static CardHand from(CardHand cardHand, Card addCard) {
        List<Card> addedCards = new ArrayList<>(cardHand.getCards());
        addedCards.add(addCard);
        return new CardHand(addedCards);
    }

    private CardHand(List<Card> cards) {
        this.cards = cards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public int getHandCount() {
        return cards.size();
    }

    public int aceCount() {
        return cards.stream()
                .filter(card -> card
                        .getCardValue()
                        .name()
                        .equals("ACE"))
                .toList()
                .size();
    }
}
