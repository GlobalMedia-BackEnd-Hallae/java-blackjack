package gmbs.model;

import gmbs.model.black_jack_enum.BlackJackValue;
import gmbs.model.black_jack_enum.CardSuits;

public class Card {

    private final CardSuits cardShape;
    private final BlackJackValue cardValue;
    private final String cardName;
    
    public Card(final CardSuits shape, final BlackJackValue value) {
        cardShape = shape;
        cardValue = value;
        cardName = shape.getDescription() + value.getDescription();
    }

    public BlackJackValue getCardValue() {
        return cardValue;
    }

    public int getNumberValue() {
        return cardValue.value();
    }

    public int getAlternativeValue() {
        return cardValue.alternativeValue();
    }

    public String getName() {
        return cardName;
    }

    public CardSuits getShape() {
        return cardShape;
    }
}
