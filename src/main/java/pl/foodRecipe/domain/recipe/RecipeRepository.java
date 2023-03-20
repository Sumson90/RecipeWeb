package pl.foodRecipe.domain.recipe;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findAllByPromotedIsTrue();
    List<Recipe> findAllByCategory_NameIgnoreCase(String category);
}
