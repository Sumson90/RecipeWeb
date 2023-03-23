package pl.foodRecipe.domian.category;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.foodRecipe.domain.category.Category;
import pl.foodRecipe.domain.category.CategoryRepository;
import pl.foodRecipe.domain.category.CategoryService;
import pl.foodRecipe.domain.category.dto.CategoryDto;

@ExtendWith(MockitoExtension.class)
public class CategoryRepositoryTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testFindByNameIgnoreCase() {
        Category category = new Category();
        Long id = 1L;
        String name = "TestCategory";
        String description = "This is a test category";

        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        when(categoryRepository.findByNameIgnoreCase(anyString())).thenReturn(Optional.of(category));

        Optional<CategoryDto> result = categoryService.findCategoryByName("testcategory");

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
        assertEquals(name, result.get().getName());
        assertEquals(description, result.get().getDescription());
    }

}


