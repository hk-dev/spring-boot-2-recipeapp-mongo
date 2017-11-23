package com.github.recipeapp.services;

import com.github.recipeapp.commands.RecipeCommand;
import com.github.recipeapp.converters.RecipeToRecipeCommand;
import com.github.recipeapp.models.Recipe;
import com.github.recipeapp.repositories.RecipeRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest
public class RecipeServiceIT {

    @Autowired
    private RecipeService recipeService;

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private RecipeToRecipeCommand recipeToRecipeCommand;

    @Test
    @Transactional
    public void testSave() throws Exception {
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe recipe = recipes.iterator().next();

        RecipeCommand command = recipeToRecipeCommand.convert(recipe);
        command.setDescription("test description");

        RecipeCommand recipeCommand = recipeService.saveRecipeCommand(command);

        assertEquals("test description", recipeCommand.getDescription());
        assertEquals(recipe.getId(), recipeCommand.getId());
    }
}
