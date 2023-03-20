package pl.foodRecipe.domain.recipe;

import pl.foodRecipe.domain.recipe.dto.RecipeDto;

public class RecipeDtoMapper {
    static RecipeDto map(Recipe recipe){
        return new RecipeDto(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getShortDescription(),
                recipe.getDescription(),
                recipe.getYoutubeTrailerId(),
                recipe.getCategory().getName(),
                recipe.isPromoted(),
                recipe.getPoster()
        );
    }
}
