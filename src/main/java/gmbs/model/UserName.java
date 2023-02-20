package gmbs.model;

public class UserName {

    private final String name;

    public UserName(String name) {
        validateBlank(name);
        this.name = name;
    }

    private void validateBlank(String name) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("[err] invalid name");
        }
    }
    public String getName() {
        return name;
    }
}
