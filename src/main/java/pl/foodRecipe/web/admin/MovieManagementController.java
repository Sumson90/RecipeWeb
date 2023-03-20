package pl.foodRecipe.web.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.foodRecipe.domain.category.CategoryService;
import pl.foodRecipe.domain.category.dto.CategoryDto;
import pl.foodRecipe.domain.recipe.RecipeService;
import pl.foodRecipe.domain.recipe.dto.RecipeSaveDto;

import java.util.List;

@Controller
public class MovieManagementController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public MovieManagementController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/dodaj-przepis")
    public String addRecipeForm(Model model) {
        List<CategoryDto> allGenres = categoryService.findAllCategories();
        model.addAttribute("genres", allGenres);
        RecipeSaveDto recipe = new RecipeSaveDto();
        model.addAttribute("recipe", recipe);
        return "admin/recipe-form";
    }

    @PostMapping("/admin/dodaj-przepis")
    public String addRecipe(RecipeSaveDto movie, RedirectAttributes redirectAttributes) {
        recipeService.addRecipe(movie);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został zapisany".formatted(movie.getTitle()));
        return "redirect:/admin";
    }
}
