package pl.foodRecipe.category;

import pl.foodRecipe.category.dto.CategoryDto;

public class CategoryDtoMapper {
    static CategoryDto map(Category category){
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
