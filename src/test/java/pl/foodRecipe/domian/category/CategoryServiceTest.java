package pl.foodRecipe.domian.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
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
public class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryService categoryService;

    @Test
    public void testFindCategoryByName() {
        Category category = new Category();
        Long id = 1L;
        String name = "TestCategory";
        String description = "This is a test category";

        category.setId(id);
        category.setName(name);
        category.setDescription(description);

        CategoryDto expected = new CategoryDto(id, name, description);

        when(categoryRepository.findByNameIgnoreCase(name)).thenReturn(Optional.of(category));

        Optional<CategoryDto> result = categoryService.findCategoryByName(name);

        assertTrue(result.isPresent());
        assertEquals(expected, result.get());
    }

    @Test
    public void testFindAllCategories() {
        Category category1 = new Category();
        Long id1 = 1L;
        String name1 = "TestCategory1";
        String description1 = "This is a test category 1";

        category1.setId(id1);
        category1.setName(name1);
        category1.setDescription(description1);

        Category category2 = new Category();
        Long id2 = 2L;
        String name2 = "TestCategory2";
        String description2 = "This is a test category 2";

        category2.setId(id2);
        category2.setName(name2);
        category2.setDescription(description2);

        List<Category> categories = new ArrayList<>();
        categories.add(category1);
        categories.add(category2);

        List<CategoryDto> expected = new ArrayList<>();
        expected.add(new CategoryDto(id1, name1, description1));
        expected.add(new CategoryDto(id2, name2, description2));

        when(categoryRepository.findAll()).thenReturn(categories);

        List<CategoryDto> result = categoryService.findAllCategories();

        assertEquals(expected, result);
    }

    @Test
    public void testAddCategory() {
        CategoryDto categoryDto = new CategoryDto();
        String name = "TestCategory";
        String description = "This is a test category";

        categoryDto.setName(name);
        categoryDto.setDescription(description);

        categoryService.addCategory(categoryDto);

        verify(categoryRepository, times(1)).save(any(Category.class));
    }

}

