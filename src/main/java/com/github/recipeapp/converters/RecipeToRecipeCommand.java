package com.github.recipeapp.converters;

import com.github.recipeapp.commands.CategoryCommand;
import com.github.recipeapp.commands.IngredientCommand;
import com.github.recipeapp.commands.NotesCommand;
import com.github.recipeapp.commands.RecipeCommand;
import com.github.recipeapp.models.Category;
import com.github.recipeapp.models.Ingredient;
import com.github.recipeapp.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private NotesToNotesCommand notesToNotesCommand;
    private IngredientToIngredientCommand ingredientToIngredientCommand;
    private CategoryToCategoryCommand categoryToCategoryCommand;

    @Autowired
    public RecipeToRecipeCommand(NotesToNotesCommand notesToNotesCommand, IngredientToIngredientCommand ingredientToIngredientCommand, CategoryToCategoryCommand categoryToCategoryCommand) {
        this.notesToNotesCommand = notesToNotesCommand;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
        this.categoryToCategoryCommand = categoryToCategoryCommand;
    }

    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if (recipe == null) {
            return null;
        }

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setDifficulty(recipe.getDifficulty());

        NotesCommand notesCommand = notesToNotesCommand.convert(recipe.getNotes());
        recipeCommand.setNotes(notesCommand);

        Set<Ingredient> ingredients = recipe.getIngredients();

        if (ingredients != null && ingredients.size() > 0) {
            ingredients.forEach(ingredient -> {
                IngredientCommand ingredientCommand = ingredientToIngredientCommand.convert(ingredient);
                recipeCommand.getIngredients().add(ingredientCommand);
            });
        }

        Set<Category> categories = recipe.getCategories();

        if (categories != null && categories.size() > 0) {
            categories.forEach(category -> {
                CategoryCommand categoryCommand = categoryToCategoryCommand.convert(category);
                recipeCommand.getCategories().add(categoryCommand);
            });
        }

        return recipeCommand;
    }
}
