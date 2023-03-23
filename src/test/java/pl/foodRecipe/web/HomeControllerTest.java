package pl.foodRecipe.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import pl.foodRecipe.domain.recipe.RecipeService;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @Test
    void home_withNoRecipes_shouldReturnEmptyList() {
        // arrange
        when(recipeService.findAllPromotedRecipes()).thenReturn(new ArrayList<>());

        // act
        String result = homeController.home(model);

        // assert
        assertEquals("recipe-listing", result);
        verify(model).addAttribute("heading", "Promowane przepisy");
        verify(model).addAttribute("description", "Przepisy polecane przez nasz zespół");
        verify(model).addAttribute("recipes", new ArrayList<>());
    }

    @Test
    void home_withSomeRecipes_shouldReturnRecipeList() {
        // arrange
        List<RecipeDto> recipes = new ArrayList<>();
        recipes.add(new RecipeDto(1L, "Recipe 1", "Short description", "Description", "1234",
                "Category", true, "poster.jpg", 4.5, 10));
        recipes.add(new RecipeDto(2L, "Recipe 2", "Short description", "Description", "5678",
                "Category", false, null, 3.5, 20));

        when(recipeService.findAllPromotedRecipes()).thenReturn(recipes);

        // act
        String result = homeController.home(model);

        // assert
        assertEquals("recipe-listing", result);
        verify(model).addAttribute("heading", "Promowane przepisy");
        verify(model).addAttribute("description", "Przepisy polecane przez nasz zespół");
        verify(model).addAttribute("recipes", recipes);
    }
}

