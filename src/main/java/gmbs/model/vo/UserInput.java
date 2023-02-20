package gmbs.model.vo;

import gmbs.model.black_jack_enum.Buttons;

import java.util.Objects;

public class UserInput {

    private final Buttons input;

    public UserInput(final String input) {
        validateWrongInput(input);
        this.input = Buttons.find(input);
    }

    public void validateWrongInput(final String string) {
        if(Buttons.find(string).equals(Buttons.WRONG)) {
            throw new IllegalArgumentException("[err] wrong input");
        }
    }

    public boolean isYes() {
        return input.equals(Buttons.YES);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInput userInput = (UserInput) o;
        return input == userInput.input;
    }

    @Override
    public int hashCode() {
        return Objects.hash(input);
    }
}
