package gmbs.model.black_jack_enum;

public enum Result {
    WIN("승"),
    LOSE("패"),
    DRAW("무");

    private final String description;

    Result(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }

    public Result getOpponent() {
        if (this.equals(WIN)) {
            return LOSE;
        }
        if (this.equals(LOSE)) {
            return WIN;
        }
        return DRAW;
    }
}
