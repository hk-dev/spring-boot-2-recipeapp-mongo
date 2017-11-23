package com.github.recipeapp.converters;

import com.github.recipeapp.commands.IngredientCommand;
import com.github.recipeapp.models.Ingredient;
import com.github.recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private IngredientToIngredientCommand converter;
    private Ingredient ingredient;

    @Before
    public void setUp() throws Exception {
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredient = new Ingredient();
    }

    @Test
    public void convert() throws Exception {
        String id = "1";
        String description = "description";
        BigDecimal amount = new BigDecimal("10");
        UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
        String unitOfMeasureId = "1";
        unitOfMeasure.setId(unitOfMeasureId);

        ingredient.setId(id);
        ingredient.setDescription(description);
        ingredient.setAmount(amount);
        ingredient.setUnitOfMeasure(unitOfMeasure);

        IngredientCommand command = converter.convert(ingredient);

        assertNotNull(command);
        assertEquals(id, command.getId());
        assertEquals(description, command.getDescription());
        assertEquals(amount, command.getAmount());
        assertEquals(unitOfMeasureId, command.getUnitOfMeasure().getId());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(ingredient));
    }

}