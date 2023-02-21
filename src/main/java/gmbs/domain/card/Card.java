package gmbs.domain.card;

import java.util.Objects;

public class Card {

    private final CardNumber cardNumber;
    private final CardPattern cardPattern;

    public Card(final CardNumber cardNumber, final CardPattern cardPattern) {
        this.cardNumber = cardNumber;
        this.cardPattern = cardPattern;
    }

    public CardNumber getDenomination() {
        return cardNumber;
    }

    public CardPattern getCardPattern() {
        return cardPattern;
    }

    public int value() {
        return this.cardNumber.getValue();
    }

    public boolean isAce() {
        return cardNumber.isAce();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return cardNumber == card.cardNumber && cardPattern == card.cardPattern;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardPattern);
    }

    @Override
    public String toString() {
        return "Card{" +
                "denomination=" + cardNumber +
                ", suit=" + cardPattern +
                '}';
    }
}
