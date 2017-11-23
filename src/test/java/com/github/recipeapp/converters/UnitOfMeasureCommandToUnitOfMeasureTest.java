package com.github.recipeapp.converters;

import com.github.recipeapp.commands.UnitOfMeasureCommand;
import com.github.recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private UnitOfMeasureCommandToUnitOfMeasure converter;
    private UnitOfMeasureCommand command;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
        command = new UnitOfMeasureCommand();
    }

    @Test
    public void convert() throws Exception {
        String id = "1";
        String description = "description";
        command.setId(id);
        command.setDescription(description);

        UnitOfMeasure unitOfMeasure = converter.convert(command);

        assertNotNull(unitOfMeasure);
        assertEquals(id, unitOfMeasure.getId());
        assertEquals(description, unitOfMeasure.getDescription());
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