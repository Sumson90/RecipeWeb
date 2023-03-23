package pl.foodRecipe.domian.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;
import pl.foodRecipe.domain.category.Category;
import pl.foodRecipe.domain.category.CategoryRepository;
import pl.foodRecipe.domain.recipe.Recipe;
import pl.foodRecipe.domain.recipe.RecipeRepository;
import pl.foodRecipe.domain.recipe.RecipeService;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;
import pl.foodRecipe.domain.recipe.dto.RecipeSaveDto;
import pl.foodRecipe.storage.FileStorageService;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeServiceTest {

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private FileStorageService fileStorageService;

    @InjectMocks
    private RecipeService recipeService;

    @Test
    public void testFindAllPromotedRecipes() {
        Category category = new Category();
        category.setName("Category 1");

        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe1.setTitle("Recipe 1");
        recipe1.setPromoted(true);
        recipe1.setCategory(category);

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipe2.setTitle("Recipe 2");
        recipe2.setPromoted(true);
        recipe2.setCategory(category);

        List<Recipe> recipes = Arrays.asList(recipe1, recipe2);

        when(recipeRepository.findAllByPromotedIsTrue()).thenReturn(recipes);

        List<RecipeDto> recipeDtos = recipeService.findAllPromotedRecipes();

        assertThat(recipeDtos).hasSize(2);

        assertThat(recipeDtos.get(0).getId()).isEqualTo(recipe1.getId());
        assertThat(recipeDtos.get(0).getTitle()).isEqualTo(recipe1.getTitle());
        assertThat(recipeDtos.get(0).getCategory()).isEqualTo(category.getName());
        assertThat(recipeDtos.get(0).isPromoted()).isEqualTo(recipe1.isPromoted());
        assertThat(recipeDtos.get(1).getId()).isEqualTo(recipe2.getId());
        assertThat(recipeDtos.get(1).getTitle()).isEqualTo(recipe2.getTitle());
        assertThat(recipeDtos.get(1).getCategory()).isEqualTo(category.getName());
        assertThat(recipeDtos.get(1).isPromoted()).isEqualTo(recipe2.isPromoted());

        verify(recipeRepository).findAllByPromotedIsTrue();
    }



    @Test
    public void testFindRecipeById() {
        Category category = new Category();
        category.setName("Recipe Category");

        Recipe recipe = new Recipe();
        recipe.setId(1L);
        recipe.setCategory(category);

        RecipeDto expectedRecipeDto = new RecipeDto(1L, null, null, null, null, "Recipe Category", false, null, 0, 0);

        when(recipeRepository.findById(1L)).thenReturn(Optional.of(recipe));

        Optional<RecipeDto> result = recipeService.findRecipeById(1L);
        assertThat(result).isNotEmpty();
        assertThat(result.get()).isEqualTo(expectedRecipeDto);
    }


    @Test
    public void testFindRecipesByCategoryName() {
        Category category = new Category();
        category.setId(1L); // set the ID of the category
        category.setName("Recipe Category");

        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe1.setTitle("Recipe 1");
        recipe1.setCategory(category);

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipe2.setTitle("Recipe 2");
        recipe2.setCategory(category);

        List<Recipe> recipes = List.of(recipe1, recipe2);

        List<RecipeDto> expectedRecipeDtos = List.of(
                new RecipeDto(1L, "Recipe 1", null, null, null, "Recipe Category", false, null, 0, 0),
                new RecipeDto(2L, "Recipe 2", null, null, null, "Recipe Category", false, null, 0, 0)
        );

        when(recipeRepository.findAllByCategory_NameIgnoreCase("Recipe Category")).thenReturn(recipes);

        List<RecipeDto> result = recipeService.findRecipesByCategoryName("Recipe Category");
        assertThat(result).containsExactlyElementsOf(expectedRecipeDtos);
    }


    @Test
    public void testAddRecipeWithPoster() throws Exception {
        RecipeSaveDto recipeToSave = new RecipeSaveDto();
        recipeToSave.setTitle("Test Recipe");
        recipeToSave.setPromoted(false);
        recipeToSave.setShortDescription("Test Description");
        recipeToSave.setDescription("Test Recipe Description");
        recipeToSave.setYoutubeTrailerId("Test Trailer");
        recipeToSave.setCategory("Test Category");
        byte[] imageData = null;
        InputStream inputStream = getClass().getResourceAsStream("/test-image.png");
        if(inputStream != null) {
            imageData = inputStream.readAllBytes();
        }
        recipeToSave.setPoster(new MockMultipartFile("test-image.png", imageData));

        when(categoryRepository.findByNameIgnoreCase("Test Category")).thenReturn(Optional.of(new Category()));
        when(fileStorageService.saveImage(any())).thenReturn("test-image.png");
        when(recipeRepository.save(any())).thenReturn(new Recipe());

        recipeService.addRecipe(recipeToSave);

        verify(recipeRepository).save(any());
    }


    @Test
    public void testFindTopRecipes() {
        Recipe recipe1 = new Recipe();
        recipe1.setId(1L);
        recipe1.setTitle("Recipe 1");
        recipe1.setShortDescription("Short Description 1");
        recipe1.setDescription("Description 1");
        recipe1.setYoutubeTrailerId("Trailer 1");
        recipe1.setCategory(new Category(1L, "Category 1", "description"));

        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        recipe2.setTitle("Recipe 2");
        recipe2.setShortDescription("Short Description 2");
        recipe2.setDescription("Description 2");
        recipe2.setYoutubeTrailerId("Trailer 2");
        recipe2.setCategory(new Category(2L, "Category 2", "description"));

        List<Recipe> recipes = List.of(recipe1, recipe2);

        List<RecipeDto> expectedRecipeDtos = List.of(
                new RecipeDto(1L, "Recipe 1", "Short Description 1", "Description 1", "Trailer 1", "Category 1", false, null, 0, 0),
                new RecipeDto(2L, "Recipe 2", "Short Description 2", "Description 2", "Trailer 2", "Category 2", false, null, 0, 0)
        );

        when(recipeRepository.findTopByRating(Pageable.ofSize(10))).thenReturn(recipes);

        List<RecipeDto> result = recipeService.findTopRecipes(10);
        assertThat(result).containsExactlyElementsOf(expectedRecipeDtos);
    }



}