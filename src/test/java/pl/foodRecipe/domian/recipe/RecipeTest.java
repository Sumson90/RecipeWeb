package pl.foodRecipe.domian.recipe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.foodRecipe.domain.category.Category;
import pl.foodRecipe.domain.rating.Rating;
import pl.foodRecipe.domain.recipe.Recipe;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class RecipeTest {

    private Recipe recipe;

    @Mock
    private Category category;

    @Mock
    private Rating rating;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recipe = new Recipe();
        recipe.setId(1L);
        recipe.setTitle("Recipe Title");
        recipe.setShortDescription("Recipe Short Description");
        recipe.setDescription("Recipe Description");
        recipe.setYoutubeTrailerId("Recipe Trailer Id");
        recipe.setCategory(category);
        recipe.getRatings().add(rating);
        recipe.setPromoted(true);
        recipe.setPoster("Recipe Poster");
    }

    @Test
    public void testRecipe() {
        assertEquals(1L, recipe.getId());
        assertEquals("Recipe Title", recipe.getTitle());
        assertEquals("Recipe Short Description", recipe.getShortDescription());
        assertEquals("Recipe Description", recipe.getDescription());
        assertEquals("Recipe Trailer Id", recipe.getYoutubeTrailerId());
        assertEquals(category, recipe.getCategory());
        assertEquals(1, recipe.getRatings().size());
        assertEquals(true, recipe.isPromoted());
        assertEquals("Recipe Poster", recipe.getPoster());
    }

}


