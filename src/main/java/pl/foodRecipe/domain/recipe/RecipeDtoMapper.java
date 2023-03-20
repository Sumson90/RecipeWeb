package pl.foodRecipe.domain.recipe;

import pl.foodRecipe.domain.recipe.dto.RecipeDto;
import pl.foodRecipe.domain.rating.Rating;
public class RecipeDtoMapper {
    static RecipeDto map(Recipe recipe) {
        double avgRating = recipe.getRatings().stream()
                .map(Rating::getRating)
                .mapToDouble(val -> val)
                .average().orElse(0);
        int ratingCount = recipe.getRatings().size();
        return new RecipeDto(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getShortDescription(),
                recipe.getDescription(),
                recipe.getYoutubeTrailerId(),
                recipe.getCategory().getName(),
                recipe.isPromoted(),
                recipe.getPoster(),
                avgRating,
                ratingCount);
    }
}
