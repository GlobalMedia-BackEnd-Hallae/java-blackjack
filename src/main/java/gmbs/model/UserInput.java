package gmbs.model;

import gmbs.model.black_jack_enum.Buttons;

public class UserInput {

    private final Buttons input;

    public UserInput(String input) {
        validateWrongInput(input);
        this.input = Buttons.find(input);
    }

    public void validateWrongInput(String string) {
        if(Buttons.find(string).equals(Buttons.WRONG)) {
            throw new IllegalArgumentException("[err] wrong input");
        }
    }

    public boolean isYes() {
        return input.equals(Buttons.YES);
    }
}
