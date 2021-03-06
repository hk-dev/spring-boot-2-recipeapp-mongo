package com.github.recipeapp.models;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    private Category category;

    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void getId() throws Exception {
        String idValue = "4";
        category.setId(idValue);
        assertEquals(idValue, category.getId());
    }
}