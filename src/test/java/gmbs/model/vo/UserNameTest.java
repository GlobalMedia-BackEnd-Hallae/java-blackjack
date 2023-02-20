package gmbs.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserNameTest {
    @Test
    @DisplayName("공백으로 생성시 예외 발생")
    void exceptionByEmptyString() {
        assertThatThrownBy(() -> new UserName("")).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("[err] invalid name");
    }
}