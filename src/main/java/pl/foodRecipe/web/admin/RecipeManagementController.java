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
public class RecipeManagementController {
    private final RecipeService recipeService;
    private final CategoryService categoryService;

    public RecipeManagementController(RecipeService recipeService, CategoryService categoryService) {
        this.recipeService = recipeService;
        this.categoryService = categoryService;
    }

    @GetMapping("/admin/dodaj-przepis")
    public String addRecipeForm(Model model) {
        List<CategoryDto> allCategories = categoryService.findAllCategories();
        model.addAttribute("categories", allCategories);
        RecipeSaveDto recipe = new RecipeSaveDto();
        model.addAttribute("recipe", recipe);
        return "admin/recipe-form";
    }

    @PostMapping("/admin/dodaj-przepis")
    public String addRecipe(RecipeSaveDto recipe, RedirectAttributes redirectAttributes) {
        recipeService.addRecipe(recipe);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Film %s został zapisany".formatted(recipe.getTitle()));
        return "redirect:/admin";
    }
}
