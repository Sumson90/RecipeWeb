package pl.foodRecipe.recipe;

import pl.foodRecipe.recipe.dto.RecipeDto;

import java.util.List;

public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDto> findAllPromotedMovies(){
        return recipeRepository.findAllByPromotedIsTrue().stream()
                .map(RecipeDtoMapper::map)
                .toList();
    }
}
