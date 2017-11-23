package com.github.recipeapp.converters;

import com.github.recipeapp.commands.RecipeCommand;
import com.github.recipeapp.models.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    private RecipeToRecipeCommand converter;
    private Recipe recipe;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand());
        recipe = new Recipe();
    }

    @Test
    public void convert() throws Exception {
        String id = "1";
        Integer cookTime = Integer.valueOf("10");
        Integer prepTime = Integer.valueOf("10");
        String description = "description";
        String directions = "directions";
        Difficulty difficulty = Difficulty.EASY;
        Integer servings = Integer.valueOf("4");
        String source = "source";
        String url = "URL";
        String categoryIdOne = "1";
        String categoryIdTwo = "2";
        String ingredientIdOne = "1";
        String ingredientIdTwo = "2";
        String notesId = "1";

        recipe.setId(id);
        recipe.setCookTime(cookTime);
        recipe.setPrepTime(prepTime);
        recipe.setDescription(description);
        recipe.setDifficulty(difficulty);
        recipe.setDirections(directions);
        recipe.setServings(servings);
        recipe.setSource(source);
        recipe.setUrl(url);

        Notes notes = new Notes();
        notes.setId(notesId);
        recipe.setNotes(notes);

        Category categoryOne = new Category();
        categoryOne.setId(categoryIdOne);

        Category categoryTwo = new Category();
        categoryTwo.setId(categoryIdTwo);

        recipe.getCategories().add(categoryOne);
        recipe.getCategories().add(categoryTwo);

        Ingredient ingredientOne = new Ingredient();
        ingredientOne.setId(ingredientIdOne);

        Ingredient ingredientTwo = new Ingredient();
        ingredientTwo.setId(ingredientIdTwo);

        recipe.getIngredients().add(ingredientOne);
        recipe.getIngredients().add(ingredientTwo);

        RecipeCommand recipeCommand  = converter.convert(this.recipe);

        assertNotNull(recipeCommand);
        assertEquals(id, recipeCommand.getId());
        assertEquals(cookTime, recipeCommand.getCookTime());
        assertEquals(prepTime, recipeCommand.getPrepTime());
        assertEquals(description, recipeCommand.getDescription());
        assertEquals(difficulty, recipeCommand.getDifficulty());
        assertEquals(directions, recipeCommand.getDirections());
        assertEquals(servings, recipeCommand.getServings());
        assertEquals(source, recipeCommand.getSource());
        assertEquals(url, recipeCommand.getUrl());
        assertEquals(notesId, recipeCommand.getNotes().getId());
        assertEquals(2, recipeCommand.getCategories().size());
        assertEquals(2, recipeCommand.getIngredients().size());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(recipe));
    }

}