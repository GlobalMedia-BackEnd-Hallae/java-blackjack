package gmbs.model.black_jack_enum;

public enum Result {
    WIN("win"),
    LOSE("lose"),
    DRAW("draw");

    private final String description;

    Result(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
