package pl.foodRecipe.domian.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import pl.foodRecipe.domain.user.*;
import pl.foodRecipe.domain.user.dto.UserCredentialsDto;
import pl.foodRecipe.domain.user.dto.UserRegistrationDto;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private UserRoleRepository userRoleRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindCredentialsByEmail() {
        // given
        String email = "test@example.com";
        User user = new User();
        user.setEmail(email);
        user.setPassword("password");
        UserRole role = new UserRole();
        role.setName("USER");
        user.getRoles().add(role);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        UserCredentialsDto expectedCredentials = new UserCredentialsDto(email, "password",
                user.getRoles().stream().map(UserRole::getName).collect(Collectors.toSet()));

        // when
        Optional<UserCredentialsDto> result = userService.findCredentialsByEmail(email);

        // then
        verify(userRepository).findByEmail(email);
        assertEquals(expectedCredentials, result.orElse(null));
    }
}



