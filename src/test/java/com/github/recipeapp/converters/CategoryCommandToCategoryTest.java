package com.github.recipeapp.converters;

import com.github.recipeapp.commands.CategoryCommand;
import com.github.recipeapp.models.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class CategoryCommandToCategoryTest {

    private CategoryCommandToCategory converter;
    private CategoryCommand command;

    @Before
    public void setUp() throws Exception {
        converter = new CategoryCommandToCategory();
        command = new CategoryCommand();
    }

    @Test
    public void convert() throws Exception {
        String id = "1";
        String description = "description";
        command.setId(id);
        command.setDescription(description);

        Category category = converter.convert(command);

        assertNotNull(category);
        assertEquals(id, category.getId());
        assertEquals(description, category.getDescription());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(command));
    }
}