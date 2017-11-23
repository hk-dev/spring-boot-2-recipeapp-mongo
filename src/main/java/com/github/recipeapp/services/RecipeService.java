package com.github.recipeapp.services;

import com.github.recipeapp.commands.RecipeCommand;
import com.github.recipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe getRecipeById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand getCommandById(String id);

    void deleteById(String id);
}
