package pl.foodRecipe.domian.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.user.User;
import pl.foodRecipe.domain.user.UserRole;

public class UserTest {

    @Test
    public void testConstructorAndGetters() {
        Long id = 1L;
        String email = "test@example.com";
        String password = "testpassword";
        Set<UserRole> roles = new HashSet<>();

        User user = new User(id, email, password, roles);

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertNotNull(user.getRoles());
        assertEquals(roles, user.getRoles());
    }

    @Test
    public void testSetters() {
        Long id = 1L;
        String email = "test@example.com";
        String password = "testpassword";
        Set<UserRole> roles = new HashSet<>();

        User user = new User();

        user.setId(id);
        user.setEmail(email);
        user.setPassword(password);
        user.setRoles(roles);

        assertEquals(id, user.getId());
        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertNotNull(user.getRoles());
        assertEquals(roles, user.getRoles());
    }

}

