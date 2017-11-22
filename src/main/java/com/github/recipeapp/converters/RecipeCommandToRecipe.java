package com.github.recipeapp.converters;

import com.github.recipeapp.commands.CategoryCommand;
import com.github.recipeapp.commands.IngredientCommand;
import com.github.recipeapp.commands.RecipeCommand;
import com.github.recipeapp.models.Category;
import com.github.recipeapp.models.Ingredient;
import com.github.recipeapp.models.Notes;
import com.github.recipeapp.models.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private NotesCommandToNotes notesCommandToNotes;
    private IngredientCommandToIngredient ingredientCommandToIngredient;
    private CategoryCommandToCategory categoryCommandToCategory;

    @Autowired
    public RecipeCommandToRecipe(NotesCommandToNotes notesCommandToNotes, IngredientCommandToIngredient ingredientCommandToIngredient, CategoryCommandToCategory categoryCommandToCategory) {
        this.notesCommandToNotes = notesCommandToNotes;
        this.ingredientCommandToIngredient = ingredientCommandToIngredient;
        this.categoryCommandToCategory = categoryCommandToCategory;
    }

    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {
        if(recipeCommand == null) {
            return null;
        }

        Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setDifficulty(recipeCommand.getDifficulty());

        Notes notes = notesCommandToNotes.convert(recipeCommand.getNotes());
        recipe.setNotes(notes);

        Set<IngredientCommand> ingredients = recipeCommand.getIngredients();

        if(ingredients != null && ingredients.size() > 0) {
            ingredients.forEach(ingredientCommand -> {
                Ingredient ingredient = ingredientCommandToIngredient.convert(ingredientCommand);
                recipe.getIngredients().add(ingredient);
            });
        }

        Set<CategoryCommand> categories = recipeCommand.getCategories();

        if(categories != null && categories.size() > 0) {
            categories.forEach(categoryCommand -> {
                Category category = categoryCommandToCategory.convert(categoryCommand);
                recipe.getCategories().add(category);
            });
        }

        return recipe;
    }
}
