package gmbs.model.vo;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;

import java.util.Objects;

public class Card {
    private final BlackJackValue cardValue;
    private final String cardName;

    public Card(final CardSuits shape, final BlackJackValue value) {
        cardValue = value;
        cardName = shape.getDescription() + value.getDescription();
    }

    public BlackJackValue getCardValue() {
        return cardValue;
    }

    public boolean isAce() {
        return cardValue.name()
                .equals("ACE");
    }

    public String getName() {
        return cardName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardValue == card.cardValue && Objects.equals(cardName, card.cardName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardValue, cardName);
    }
}
