package com.github.recipeapp.converters;

import com.github.recipeapp.commands.CategoryCommand;
import com.github.recipeapp.commands.IngredientCommand;
import com.github.recipeapp.commands.NotesCommand;
import com.github.recipeapp.commands.RecipeCommand;
import com.github.recipeapp.models.Difficulty;
import com.github.recipeapp.models.Recipe;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    private RecipeCommandToRecipe converter;
    private RecipeCommand command;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipe(new NotesCommandToNotes(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new CategoryCommandToCategory());
        command = new RecipeCommand();
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

        command.setId(id);
        command.setCookTime(cookTime);
        command.setPrepTime(prepTime);
        command.setDescription(description);
        command.setDifficulty(difficulty);
        command.setDirections(directions);
        command.setServings(servings);
        command.setSource(source);
        command.setUrl(url);

        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(notesId);
        command.setNotes(notesCommand);

        CategoryCommand categoryCommandOne = new CategoryCommand();
        categoryCommandOne.setId(categoryIdOne);

        CategoryCommand categoryCommandTwo = new CategoryCommand();
        categoryCommandTwo.setId(categoryIdTwo);

        command.getCategories().add(categoryCommandOne);
        command.getCategories().add(categoryCommandTwo);

        IngredientCommand ingredientCommandOne = new IngredientCommand();
        ingredientCommandOne.setId(ingredientIdOne);

        IngredientCommand ingredientCommandTwo = new IngredientCommand();
        ingredientCommandTwo.setId(ingredientIdTwo);

        command.getIngredients().add(ingredientCommandOne);
        command.getIngredients().add(ingredientCommandTwo);

        Recipe recipe  = converter.convert(command);

        assertNotNull(recipe);
        assertEquals(id, recipe.getId());
        assertEquals(cookTime, recipe.getCookTime());
        assertEquals(prepTime, recipe.getPrepTime());
        assertEquals(description, recipe.getDescription());
        assertEquals(difficulty, recipe.getDifficulty());
        assertEquals(directions, recipe.getDirections());
        assertEquals(servings, recipe.getServings());
        assertEquals(source, recipe.getSource());
        assertEquals(url, recipe.getUrl());
        assertEquals(notesId, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

}