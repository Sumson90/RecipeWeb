package pl.foodRecipe.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import pl.foodRecipe.domain.category.CategoryService;
import pl.foodRecipe.domain.category.dto.CategoryDto;
import pl.foodRecipe.domain.recipe.RecipeService;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Mock
    private CategoryService categoryService;

    @Mock
    private RecipeService recipeService;

    @Mock
    private Model model;

    @InjectMocks
    private CategoryController categoryController;

    @Test
    void getCategory_withExistingCategory_shouldReturnRecipeListingView() {
        // arrange
        String categoryName = "Breakfast";
        CategoryDto categoryDto = new CategoryDto(1L,categoryName, "Recipes for breakfast");
        List<RecipeDto> recipes = new ArrayList<>();
        recipes.add(new RecipeDto(1L, "Recipe 1", "Short description", "Description", "1234",
                categoryName, true, "poster.jpg", 4.5, 10));

        when(categoryService.findCategoryByName(categoryName)).thenReturn(Optional.of(categoryDto));
        when(recipeService.findRecipesByCategoryName(categoryName)).thenReturn(recipes);

        // act
        String result = categoryController.getCategory(categoryName, model);

        // assert
        assertEquals("recipe-listing", result);
        verify(model).addAttribute("heading", categoryDto.getName());
        verify(model).addAttribute("description", categoryDto.getDescription());
        verify(model).addAttribute("recipes", recipes);
    }

    @Test
    void getCategory_withNonexistentCategory_shouldThrowResponseStatusException() {
        // arrange
        String categoryName = "Nonexistent category";
        when(categoryService.findCategoryByName(categoryName)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(ResponseStatusException.class, () -> categoryController.getCategory(categoryName, model));
    }

    @Test
    void getCategoriesList_withNoCategories_shouldReturnEmptyList() {
        // arrange
        when(categoryService.findAllCategories()).thenReturn(new ArrayList<>());

        // act
        String result = categoryController.getCategoriesList(model);

        // assert
        assertEquals("categories-listing", result);
        verify(model).addAttribute("categories", new ArrayList<>());
    }

    @Test
    void getCategoriesList_withSomeCategories_shouldReturnCategoryList() {
        // arrange
        List<CategoryDto> categories = new ArrayList<>();
        categories.add(new CategoryDto(1L,"Breakfast", "Recipes for breakfast"));
        categories.add(new CategoryDto(2L,"Dinner", "Recipes for dinner"));

        when(categoryService.findAllCategories()).thenReturn(categories);

        // act
        String result = categoryController.getCategoriesList(model);

        // assert
        assertEquals("categories-listing", result);
        verify(model).addAttribute("categories", categories);
    }
}

