package com.github.recipeapp.converters;

import com.github.recipeapp.commands.IngredientCommand;
import com.github.recipeapp.commands.UnitOfMeasureCommand;
import com.github.recipeapp.models.Ingredient;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private IngredientCommandToIngredient converter;
    private IngredientCommand command;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        command = new IngredientCommand();
    }

    @Test
    public void convert() throws Exception {
        String id = "1";
        String description = "description";
        BigDecimal amount = new BigDecimal("10");
        UnitOfMeasureCommand unitOfMeasure = new UnitOfMeasureCommand();
        String unitOfMeasureId = "1";
        unitOfMeasure.setId(unitOfMeasureId);

        command.setId(id);
        command.setDescription(description);
        command.setAmount(amount);
        command.setUnitOfMeasure(unitOfMeasure);

        Ingredient ingredient = converter.convert(command);

        assertNotNull(ingredient);
        assertEquals(id, ingredient.getId());
        assertEquals(description, ingredient.getDescription());
        assertEquals(amount, ingredient.getAmount());
        assertEquals(unitOfMeasureId, ingredient.getUnitOfMeasure().getId());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(command));
    }

}