package gmbs.model.black_jack_enum;

import java.util.Arrays;

public enum Buttons {

    YES("y"),
    NO("n"),
    WRONG("not_defined");

    private final String description;

    Buttons(final String description) {
        this.description = description;
    }

    public static Buttons find(final String target) {
        return Arrays.stream(Buttons.values())
                .filter(input -> input.description.equals(target))
                .findAny()
                .orElse(WRONG);
    }
}
