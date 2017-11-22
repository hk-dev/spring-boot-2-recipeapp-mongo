package com.github.recipeapp.commands;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryCommandTest {

    private CategoryCommand command;

    @Before
    public void setUp() throws Exception {
        command = new CategoryCommand();
    }

    @Test
    public void testCategoryCommand() throws Exception {
        Long id = 1L;
        String description = "Category Command";
        command.setId(id);
        command.setDescription(description);
        assertEquals(id, command.getId());
        assertEquals(description, command.getDescription());
    }
}