package pl.foodRecipe.domian.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.foodRecipe.domain.user.UserRole;
import pl.foodRecipe.domain.user.UserRoleRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRoleRepositoryTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @Test
    public void testFindByName() {
        String roleName = "ROLE_ADMIN";
        String roleDescription = "Administrator Role";

        UserRole userRole = new UserRole();
        userRole.setName(roleName);
        userRole.setDescription(roleDescription);

        when(userRoleRepository.findByName(roleName)).thenReturn(Optional.of(userRole));

        Optional<UserRole> result = userRoleRepository.findByName(roleName);

        assertTrue(result.isPresent());
        assertEquals(roleName, result.get().getName());
        assertEquals(roleDescription, result.get().getDescription());
    }

}

