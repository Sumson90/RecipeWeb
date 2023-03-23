package pl.foodRecipe.web;

import org.junit.jupiter.api.Test;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest {

    private LoginController loginController = new LoginController();

    @Test
    void loginForm_shouldReturnLoginFormView() {
        // arrange
        Model model = null;

        // act
        String result = loginController.loginForm();

        // assert
        assertEquals("login-form", result);
    }

}
