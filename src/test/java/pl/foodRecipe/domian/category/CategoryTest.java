package pl.foodRecipe.domian.category;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.foodRecipe.domain.category.Category;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoryTest {

    private Category category;

    @BeforeEach
    public void setUp() {
        category = new Category();
    }

    @Test
    public void testGetName() {
        String name = "TestCategory";
        category.setName(name);

        assertEquals(name, category.getName());
    }

    @Test
    public void testGetDescription() {
        String description = "This is a test category";
        category.setDescription(description);

        assertEquals(description, category.getDescription());
    }

    @Test
    public void testToString() {
        String name = "TestCategory";
        String description = "This is a test category";
        category.setName(name);
        category.setDescription(description);

        String expected = "Category(id=null, name=" + name + ", description=" + description + ")";

        assertEquals(expected, category.toString());
    }



}

