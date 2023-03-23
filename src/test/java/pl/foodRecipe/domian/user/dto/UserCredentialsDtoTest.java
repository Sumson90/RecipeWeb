package pl.foodRecipe.domian.user.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.user.dto.UserCredentialsDto;

public class UserCredentialsDtoTest {

    @Test
    public void testConstructorAndGetters() {
        String email = "test@example.com";
        String password = "testpassword";
        Set<String> roles = new HashSet<>();

        UserCredentialsDto userCredentialsDto = new UserCredentialsDto(email, password, roles);

        assertEquals(email, userCredentialsDto.getEmail());
        assertEquals(password, userCredentialsDto.getPassword());
        assertNotNull(userCredentialsDto.getRoles());
        assertEquals(roles, userCredentialsDto.getRoles());
    }

}

