package pl.foodRecipe.recipe;

import pl.foodRecipe.recipe.dto.RecipeDto;

public class RecipeDtoMapper {
    static RecipeDto map(Recipe recipe){
        return new RecipeDto(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getCategory().getName(),
                recipe.isPromoted()
        );
    }
}
