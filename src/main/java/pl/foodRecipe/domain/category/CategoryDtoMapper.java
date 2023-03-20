package pl.foodRecipe.domain.category;

import pl.foodRecipe.domain.category.dto.CategoryDto;

public class CategoryDtoMapper {
    static CategoryDto map(Category category){
        return new CategoryDto(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}
