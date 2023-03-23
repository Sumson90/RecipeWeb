package pl.foodRecipe.domian.recipe.dto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;

import static org.assertj.core.api.Assertions.assertThat;

public class RecipeDtoTest {

    private RecipeDto recipeDto;

    @BeforeEach
    public void setUp() {
        recipeDto = new RecipeDto();
        recipeDto.setId(1L);
        recipeDto.setTitle("Recipe Title");
        recipeDto.setShortDescription("Recipe Short Description");
        recipeDto.setDescription("Recipe Description");
        recipeDto.setYoutubeTrailerId("Recipe Trailer Id");
        recipeDto.setCategory("Recipe Category");
        recipeDto.setPromoted(true);
        recipeDto.setPoster("Recipe Poster");
        recipeDto.setAvgRating(4.5);
        recipeDto.setRatingCount(10);
    }

    @Test
    public void testRecipeDto() {
        assertThat(recipeDto.getId()).isEqualTo(1L);
        assertThat(recipeDto.getTitle()).isEqualTo("Recipe Title");
        assertThat(recipeDto.getShortDescription()).isEqualTo("Recipe Short Description");
        assertThat(recipeDto.getDescription()).isEqualTo("Recipe Description");
        assertThat(recipeDto.getYoutubeTrailerId()).isEqualTo("Recipe Trailer Id");
        assertThat(recipeDto.getCategory()).isEqualTo("Recipe Category");
        assertThat(recipeDto.isPromoted()).isEqualTo(true);
        assertThat(recipeDto.getPoster()).isEqualTo("Recipe Poster");
        assertThat(recipeDto.getAvgRating()).isEqualTo(4.5);
        assertThat(recipeDto.getRatingCount()).isEqualTo(10);
    }

}

