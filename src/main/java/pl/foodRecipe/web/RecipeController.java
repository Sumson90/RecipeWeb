package pl.foodRecipe.web;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import pl.foodRecipe.domain.rating.RatingService;
import pl.foodRecipe.domain.recipe.RecipeService;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;

import java.util.List;

@Controller
public class RecipeController {
    private final RecipeService recipeService;
    private final RatingService ratingService;

    public RecipeController(RecipeService recipeService, RatingService ratingService) {
        this.recipeService = recipeService;
        this.ratingService = ratingService;
    }

    @GetMapping("/przepis/{id}")
    public String getRecipe(@PathVariable long id, Model model, Authentication authentication) {
        RecipeDto recipe = recipeService.findRecipeById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("recipe", recipe);
        if (authentication != null) {
            String currentUserEmail = authentication.getName();
            Integer rating = ratingService.getUserRatingForRecipe(currentUserEmail, id).orElse(0);
            model.addAttribute("userRating", rating);
        }


        return "recipe";
    }
    @GetMapping("/top10")
    public String findTop10(Model model) {
        List<RecipeDto> top10Recipes = recipeService.findTopRecipes(10);
        model.addAttribute("heading", "Przepisy TOP10");
        model.addAttribute("description", "Przepisy najlepiej oceniane przez użytkowników");
        model.addAttribute("recipes", top10Recipes);
        return "recipe-listing";
    }

}
