package pl.foodRecipe.domian.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.user.UserRole;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class UserRoleTest {

    @Test
    public void testConstructorAndGetters() {
        String name = "ROLE_USER";
        String description = "A user role";

        UserRole userRole = new UserRole(1L, name, description);

        assertNotNull(userRole.getId());
        assertEquals(name, userRole.getName());
        assertEquals(description, userRole.getDescription());
    }

}


