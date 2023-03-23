package pl.foodRecipe.domian.category.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.category.dto.CategoryDto;

public class CategoryDtoTest {

    private CategoryDto categoryDto;

    @BeforeEach
    public void setUp() {
        categoryDto = new CategoryDto();
    }

    @Test
    public void testGetId() {
        Long id = 1L;
        categoryDto.setId(id);

        assertEquals(id, categoryDto.getId());
    }

    @Test
    public void testGetName() {
        String name = "TestCategory";
        categoryDto.setName(name);

        assertEquals(name, categoryDto.getName());
    }

    @Test
    public void testGetDescription() {
        String description = "This is a test category";
        categoryDto.setDescription(description);

        assertEquals(description, categoryDto.getDescription());
    }

    @Test
    public void testToString() {
        Long id = 1L;
        String name = "TestCategory";
        String description = "This is a test category";
        categoryDto.setId(id);
        categoryDto.setName(name);
        categoryDto.setDescription(description);

        String expected = "CategoryDto(id=" + id + ", name=" + name + ", description=" + description + ")";

        assertEquals(expected, categoryDto.toString());
    }

}

