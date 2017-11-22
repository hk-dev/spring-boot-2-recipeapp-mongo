package com.github.recipeapp.converters;

import com.github.recipeapp.commands.CategoryCommand;
import com.github.recipeapp.models.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private CategoryToCategoryCommand converter;
    private Category category;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryToCategoryCommand();
        category = new Category();
    }

    @Test
    public void convert() throws Exception {
        Long id = 1L;
        String description = "description";
        category.setId(id);
        category.setDescription(description);

        CategoryCommand command = converter.convert(category);

        assertNotNull(command);
        assertEquals(id, command.getId());
        assertEquals(description, command.getDescription());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(category));
    }
}