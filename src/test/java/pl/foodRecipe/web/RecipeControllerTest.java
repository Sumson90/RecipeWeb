package pl.foodRecipe.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import pl.foodRecipe.domain.rating.RatingService;
import pl.foodRecipe.domain.recipe.RecipeService;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private RatingService ratingService;

    @Mock
    private Model model;

    @InjectMocks
    private RecipeController recipeController;

    @Test
    void getRecipe_withExistingRecipe_shouldReturnRecipeView() {
        // arrange
        long id = 1L;
        RecipeDto recipeDto = new RecipeDto(id, "Recipe Title", "Short description", "Description",
                "1234", "Category", true, "poster.jpg", 4.5, 10);

        when(recipeService.findRecipeById(id)).thenReturn(Optional.of(recipeDto));

        Authentication auth = new UsernamePasswordAuthenticationToken("user@test.com", "password");

        // act
        String result = recipeController.getRecipe(id, model, auth);

        // assert
        assertEquals("recipe", result);
        verify(model).addAttribute("recipe", recipeDto);
        verify(ratingService).getUserRatingForRecipe("user@test.com", id);
    }

    @Test
    void getRecipe_withNonexistentRecipe_shouldThrowResponseStatusException() {
        // arrange
        long id = 1L;
        when(recipeService.findRecipeById(id)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ResponseStatusException.class, () -> recipeController.getRecipe(id, model, null));
    }

    @Test
    void getRecipe_withNullAuthentication_shouldNotCheckUserRating() {
        // arrange
        long id = 1L;
        RecipeDto recipeDto = new RecipeDto(id, "Recipe Title", "Short description", "Description",
                "1234", "Category", true, "poster.jpg", 4.5, 10);

        when(recipeService.findRecipeById(id)).thenReturn(Optional.of(recipeDto));

        // act
        String result = recipeController.getRecipe(id, model, null);

        // assert
        assertEquals("recipe", result);
        verify(model).addAttribute("recipe", recipeDto);
        verifyNoInteractions(ratingService);
    }

    @Test
    void findTop10_withNoRecipes_shouldReturnEmptyList() {
        // arrange
        when(recipeService.findTopRecipes(10)).thenReturn(new ArrayList<>());

        // act
        String result = recipeController.findTop10(model);

        // assert
        assertEquals("recipe-listing", result);
        verify(model).addAttribute("heading", "Przepisy TOP10");
        verify(model).addAttribute("description", "Przepisy najlepiej oceniane przez użytkowników");
        verify(model).addAttribute("recipes", new ArrayList<>());
    }

    @Test
    void findTop10_withSomeRecipes_shouldReturnRecipeList() {
        // arrange
        List<RecipeDto> recipes = new ArrayList<>();
        recipes.add(new RecipeDto(1L, "Recipe 1", "Short description", "Description", "1234",
                "Category", true, "poster.jpg", 4.5, 10));
        recipes.add(new RecipeDto(2L, "Recipe 2", "Short description", "Description", "5678",
                "Category", false, null, 3.2, 5));
        when(recipeService.findTopRecipes(10)).thenReturn(recipes);


        // act
        String result = recipeController.findTop10(model);

        // assert
        assertEquals("recipe-listing", result);
        verify(model).addAttribute("heading", "Przepisy TOP10");
        verify(model).addAttribute("description", "Przepisy najlepiej oceniane przez użytkowników");
        verify(model).addAttribute("recipes", recipes);
    }
}