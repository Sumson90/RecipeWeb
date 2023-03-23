package pl.foodRecipe.domian.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.user.User;
import pl.foodRecipe.domain.user.UserRole;
import pl.foodRecipe.domain.user.dto.UserCredentialsDto;
import pl.foodRecipe.domain.user.UserCredentialsDtoMapper;
public class UserCredentialsDtoMapperTest {

    @Test
    public void testMap() {
        String email = "test@example.com";
        String password = "testpassword";
        Set<UserRole> roles = new HashSet<>();
        roles.add(new UserRole(null, "ROLE_USER", "A user role"));

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);

        UserCredentialsDto userCredentialsDto = UserCredentialsDtoMapper.map(user);

        assertEquals(email, userCredentialsDto.getEmail());
        assertEquals(password, userCredentialsDto.getPassword());
        assertNotNull(userCredentialsDto.getRoles());
        assertEquals(roles.stream().map(UserRole::getName).collect(Collectors.toSet()), userCredentialsDto.getRoles());
    }

}

