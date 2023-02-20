package gmbs.model.black_jack_enum;

import java.util.Arrays;

public enum Inputs {

    YES("y"),
    NO("n"),
    WRONG("");

    private final String inputValue;

    Inputs(final String input) {
        this.inputValue = input;
    }

    public static Inputs find(final String target) {
        return Arrays.stream(Inputs.values())
                .filter(input->input.inputValue.equals(target))
                .findAny()
                .orElse(WRONG);
    }
}
