package gmbs.model.black_jack_enum;

import java.util.Arrays;

public enum Buttons {

    YES("y"),
    NO("n"),
    WRONG("");

    private final String inputValue;

    Buttons(final String input) {
        this.inputValue = input;
    }

    public static Buttons find(final String target) {
        return Arrays.stream(Buttons.values())
                .filter(input->input.inputValue.equals(target))
                .findAny()
                .orElse(WRONG);
    }
}
