package pl.foodRecipe.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.foodRecipe.recipe.RecipeService;
import pl.foodRecipe.recipe.dto.RecipeDto;

import java.util.Optional;

@Controller
public class RecipeController {
    private final RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping("/przepis/{id}")
    public String getRecipe(@PathVariable long id, Model model) {
        Optional<RecipeDto> optionalRecipe = recipeService.findRecipeById(id);
        optionalRecipe.ifPresent(recipe -> model.addAttribute("recipe", recipe));
        return "recipe";
    }

}
