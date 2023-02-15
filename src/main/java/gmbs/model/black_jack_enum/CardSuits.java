package gmbs.model.black_jack_enum;

public enum CardSuits {

    SPADE("♠"),
    CLUB("♣"),
    HEART("♥"),
    DIAMOND("◆");

    private final String description;

    CardSuits(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
