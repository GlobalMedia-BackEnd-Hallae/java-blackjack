package gmbs.model.vo;

import java.util.Objects;

public class UserName {

    private final String name;

    public UserName(final String name) {
        validateBlank(name);
        this.name = name;
    }

    private void validateBlank(final String name) {
        if(name.isEmpty()) {
            throw new IllegalArgumentException("[err] invalid name");
        }
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserName userName = (UserName) o;
        return Objects.equals(name, userName.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
