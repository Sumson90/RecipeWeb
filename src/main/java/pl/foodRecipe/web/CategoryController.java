package pl.foodRecipe.web;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;
import pl.foodRecipe.domain.category.CategoryService;
import pl.foodRecipe.domain.category.dto.CategoryDto;
import pl.foodRecipe.domain.recipe.RecipeService;
import pl.foodRecipe.domain.recipe.dto.RecipeDto;

import java.util.List;

@Controller
public class CategoryController {
    private final CategoryService categoryService;
    private final RecipeService recipeService ;

    public CategoryController(CategoryService categoryService, RecipeService recipeService ) {
        this.categoryService = categoryService;
        this.recipeService = recipeService;
    }

    @GetMapping("/kategoria/{name}")
    public String getGenre(@PathVariable String name, Model model) {
        CategoryDto category = categoryService.findCategoryByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        List<RecipeDto> recipes = recipeService.findRecipesByCategoryName(name);
        model.addAttribute("heading", category.getName());
        model.addAttribute("description", category.getDescription());
        model.addAttribute("recipes", recipes);
        return "recipe-listing";
    }
    @GetMapping("/kategorie-przepisow")
    public String getCategoriesList(Model model) {
        List<CategoryDto> categories = categoryService.findAllCategories();
        model.addAttribute("categories", categories);
        return "categories-listing";
    }
}
