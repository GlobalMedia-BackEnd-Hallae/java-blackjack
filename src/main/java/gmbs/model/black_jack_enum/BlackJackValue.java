package gmbs.model.black_jack_enum;

public enum BlackJackValue {

    ACE(1, true, 11, "A"),
    TWO(2, false, -1, "2"),
    THREE(3, false, -1, "3"),
    FOUR(4, false, -1, "4"),
    FIVE(5, false, -1, "5"),
    SIX(6, false, -1, "6"),
    SEVEN(7, false, -1, "7"),
    EIGHT(8, false, -1, "8"),
    NINE(9, false, -1, "9"),
    TEN(10, false, -1, "10"),
    JACK(10, false, -1, "J"),
    QUEEN(10, false, -1, "Q"),
    KING(10, false, -1, "K");

    private final int value;
    private final boolean hasAlternativeValue;
    private final int alternativeValue;
    private final String name;

    BlackJackValue(final int value, final boolean hasAlternativeValue, final int alternativeValue, final String name) {
        this.value = value;
        this.hasAlternativeValue = hasAlternativeValue;
        this.alternativeValue = alternativeValue;
        this.name = name;
    }

    public int value() {
        return value;
    }

    public int alternativeValue() {
        if (!hasAlternativeValue) {
            throw new IllegalArgumentException("[err] has no alternative value");
        }
        return alternativeValue;
    }

    public String getDescription() {
        return name;
    }
}
