package pl.foodRecipe.domian.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import pl.foodRecipe.domain.category.CategoryDtoMapper;
import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.category.Category;
import pl.foodRecipe.domain.category.dto.CategoryDto;

public class CategoryDtoMapperTest {

    @Test
    public void testMap() {
        Category category = new Category();
        Long id = 1L;
        String name = "TestCategory";
        String description = "This is a test category";

        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        CategoryDto expected = new CategoryDto(id, name, description);

        CategoryDto result = CategoryDtoMapper.map(category);

        assertEquals(expected, result);
    }

}

