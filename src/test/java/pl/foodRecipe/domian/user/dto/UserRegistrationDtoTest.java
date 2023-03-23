package pl.foodRecipe.domian.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.user.dto.UserRegistrationDto;

public class UserRegistrationDtoTest {

    @Test
    public void testSettersAndGetters() {
        String email = "test@example.com";
        String password = "testpassword";

        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();

        userRegistrationDto.setEmail(email);
        userRegistrationDto.setPassword(password);

        assertEquals(email, userRegistrationDto.getEmail());
        assertEquals(password, userRegistrationDto.getPassword());
    }

}
