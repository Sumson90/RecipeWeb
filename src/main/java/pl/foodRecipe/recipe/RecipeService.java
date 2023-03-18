package pl.foodRecipe.recipe;

import org.springframework.stereotype.Service;
import pl.foodRecipe.recipe.dto.RecipeDto;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public List<RecipeDto> findAllPromotedRecipes() {
        return recipeRepository.findAllByPromotedIsTrue().stream()
                .map(RecipeDtoMapper::map)
                .toList();
    }

    public Optional<RecipeDto> findRecipeById(long id) {
        return recipeRepository.findById(id).map(RecipeDtoMapper::map);
    }

    public List<RecipeDto> findRecipesByCategoryName(String category) {
        return recipeRepository.findAllByCategory_NameIgnoreCase(category).stream()
                .map(RecipeDtoMapper::map)
                .toList();
    }


}

