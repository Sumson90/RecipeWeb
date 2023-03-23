package pl.foodRecipe.domian.recipe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.foodRecipe.domain.category.Category;
import pl.foodRecipe.domain.rating.Rating;
import pl.foodRecipe.domain.recipe.Recipe;
import pl.foodRecipe.domain.recipe.RecipeDtoMapper;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RecipeDtoMapperTest {

    @Mock
    private Recipe recipe;

    @Mock
    private Category category;

    @Test
    public void testMap() {
        Set<Rating> ratings = new HashSet<>();
        Rating rating1 = new Rating();
        rating1.setRating(4);
        Rating rating2 = new Rating();
        rating2.setRating(5);
        ratings.add(rating1);
        ratings.add(rating2);

        when(recipe.getId()).thenReturn(1L);
        when(recipe.getTitle()).thenReturn("Recipe Title");
        when(recipe.getShortDescription()).thenReturn("Recipe Short Description");
        when(recipe.getDescription()).thenReturn("Recipe Description");
        when(recipe.getYoutubeTrailerId()).thenReturn("Recipe Trailer Id");
        when(recipe.getCategory()).thenReturn(category);
        when(category.getName()).thenReturn("Recipe Category");
        when(recipe.isPromoted()).thenReturn(true);
        when(recipe.getPoster()).thenReturn("Recipe Poster");
        when(recipe.getRatings()).thenReturn(ratings);

        RecipeDtoMapper recipeDtoMapper = new RecipeDtoMapper();
        RecipeDto recipeDto = recipeDtoMapper.map(recipe);
        assertThat(recipeDto.getId()).isEqualTo(1L);
        assertThat(recipeDto.getTitle()).isEqualTo("Recipe Title");
        assertThat(recipeDto.getShortDescription()).isEqualTo("Recipe Short Description");
        assertThat(recipeDto.getDescription()).isEqualTo("Recipe Description");
        assertThat(recipeDto.getYoutubeTrailerId()).isEqualTo("Recipe Trailer Id");
        assertThat(recipeDto.getCategory()).isEqualTo("Recipe Category");
        assertThat(recipeDto.isPromoted()).isEqualTo(true);
        assertThat(recipeDto.getPoster()).isEqualTo("Recipe Poster");
        assertThat(recipeDto.getAvgRating()).isEqualTo(4.5);
        assertThat(recipeDto.getRatingCount()).isEqualTo(2);
    }

}

