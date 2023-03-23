package pl.foodRecipe.domian.user;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.foodRecipe.domain.user.User;
import pl.foodRecipe.domain.user.UserRepository;
import pl.foodRecipe.domain.user.UserService;
import pl.foodRecipe.domain.user.dto.UserCredentialsDto;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        String password = "testpassword";

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        Optional<UserCredentialsDto> result = userService.findCredentialsByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
        assertEquals(password, result.get().getPassword());
    }

}


