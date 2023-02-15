package gmbs.model.black_jack_enum;

public enum CardSuits {

    SPADE("spade"),
    CLUB("club"),
    HEART("heart"),
    DIAMOND("diamond");

    private final String name;

    CardSuits(String name) {
        this.name = name;
    }

    public String getDescription() {
        return name;
    }
}
