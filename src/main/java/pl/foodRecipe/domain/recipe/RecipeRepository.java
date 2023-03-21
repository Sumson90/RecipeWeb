package pl.foodRecipe.domain.recipe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findAllByPromotedIsTrue();
    List<Recipe> findAllByCategory_NameIgnoreCase(String category);
    @Query("select m from Recipe m join m.ratings r group by m order by avg(r.rating) desc")
    List<Recipe> findTopByRating(Pageable page);
}
