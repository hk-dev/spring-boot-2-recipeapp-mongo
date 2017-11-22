package com.github.recipeapp.services;

import com.github.recipeapp.commands.RecipeCommand;
import com.github.recipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe getRecipeById(Long l);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand getCommandById(Long id);

    void deleteById(Long id);
}
