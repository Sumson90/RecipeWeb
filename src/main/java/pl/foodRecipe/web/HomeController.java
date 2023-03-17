package pl.foodRecipe.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import pl.foodRecipe.recipe.RecipeService;
import pl.foodRecipe.recipe.dto.RecipeDto;

import java.util.List;

@Controller
public class HomeController {
    private final RecipeService recipeService;


    public HomeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping("/")
    public String home(Model model){
        List<RecipeDto> promotedRecipes = recipeService.findAllPromotedMovies();
        model.addAttribute("heading", "Promowane przepisy");
        model.addAttribute("description", "Przepisy polecane przez nasz zespół");
        model.addAttribute("recipes" ,promotedRecipes);
        return "recipe-listing";
    }
}
