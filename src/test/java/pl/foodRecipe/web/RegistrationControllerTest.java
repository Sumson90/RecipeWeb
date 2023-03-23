package pl.foodRecipe.web;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;
import pl.foodRecipe.domain.user.UserService;
import pl.foodRecipe.domain.user.dto.UserRegistrationDto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class RegistrationControllerTest {

    @Test
    void register_withValidRegistrationData_shouldCallUserService() {
        // given
        UserService userService = mock(UserService.class);
        RegistrationController registrationController = new RegistrationController(userService);
        UserRegistrationDto userRegistrationDto = new UserRegistrationDto();
        userRegistrationDto.setEmail("test@example.com");
        userRegistrationDto.setPassword("password");

        // when
        registrationController.register(userRegistrationDto);

        // then
        verify(userService, times(1)).registerUserWithDefaultRole(userRegistrationDto);
    }

    @Test
    void register_shouldCallUserService_registerUserWithDefaultRole_andReturnRedirectString() {
        // arrange
        UserRegistrationDto userDto = new UserRegistrationDto();
        UserService userService = mock(UserService.class);
        RegistrationController registrationController = new RegistrationController(userService);
        String expectedRedirect = "redirect:/";

        // act
        String actualRedirect = registrationController.register(userDto);

        // assert
        verify(userService, times(1)).registerUserWithDefaultRole(userDto);
        assertEquals(expectedRedirect, actualRedirect);
    }

    @Test
    void registrationForm_shouldReturnRegistrationFormView() {
        // arrange
        UserService userService = mock(UserService.class);
        RegistrationController registrationController = new RegistrationController(userService);
        Model model = mock(Model.class);
        String expectedViewName = "registration-form";

        // act
        String actualViewName = registrationController.registrationForm(model);

        // assert
        assertEquals(expectedViewName, actualViewName);
        verify(model, times(1)).addAttribute(eq("user"), any(UserRegistrationDto.class));
    }

}

