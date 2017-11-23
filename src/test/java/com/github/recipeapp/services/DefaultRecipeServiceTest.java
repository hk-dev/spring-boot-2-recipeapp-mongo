package com.github.recipeapp.services;

import com.github.recipeapp.converters.RecipeCommandToRecipe;
import com.github.recipeapp.converters.RecipeToRecipeCommand;
import com.github.recipeapp.models.Recipe;
import com.github.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class DefaultRecipeServiceTest {

    private DefaultRecipeService recipeService;

    @Mock
    private RecipeRepository recipeRepository;

    @Mock
    private RecipeCommandToRecipe recipeCommandToRecipe;

    @Mock
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new DefaultRecipeService(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getRecipes() throws Exception {
        Recipe recipe = new Recipe();
        Set<Recipe> recipeData = new HashSet<>();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(1, recipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("1");

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        assertNotNull("Null recipe returned", recipeService.getRecipeById(1L));

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void deleteById() throws Exception {
        Long id = 2L;
        recipeService.deleteById(id);
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}