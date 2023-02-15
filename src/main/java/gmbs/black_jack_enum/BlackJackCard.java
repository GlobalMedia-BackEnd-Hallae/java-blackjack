package gmbs.black_jack_enum;

public enum BlackJackCard {

    ACE(1, true, 11),
    TWO(2, false, -1),
    THREE(3, false, -1),
    FOUR(4, false, -1),
    FIVE(5, false, -1),
    SIX(6, false, -1),
    SEVEN(7, false, -1),
    EIGHT(8, false, -1),
    NINE(9, false, -1),
    JACK(10, false, -1),
    QUEEN(10, false, -1),
    KING(10, false, -1);

    private final int value;
    private final boolean hasAlternativeValue;
    private final int alternativeValue;

    BlackJackCard(final int value, final boolean hasAlternativeValue, final int alternativeValue) {
        this.value = value;
        this.hasAlternativeValue = hasAlternativeValue;
        this.alternativeValue = alternativeValue;
    }

    public int value() {
        return value;
    }

    public boolean hasAlternativeValue() {
        return hasAlternativeValue;
    }

    public int alternativeValue() {
        if(!hasAlternativeValue) {
            throw new IllegalArgumentException("[err] has no alternative value");
        }
        return alternativeValue;
    }
}
