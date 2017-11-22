package com.github.recipeapp.converters;

import com.github.recipeapp.commands.UnitOfMeasureCommand;
import com.github.recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private UnitOfMeasureToUnitOfMeasureCommand converter;
    private UnitOfMeasure unitOfMeasure;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
        unitOfMeasure = new UnitOfMeasure();
    }

    @Test
    public void convert() throws Exception {
        Long id = 1L;
        String description = "description";
        unitOfMeasure.setId(id);
        unitOfMeasure.setDescription(description);

        UnitOfMeasureCommand command = converter.convert(this.unitOfMeasure);

        assertNotNull(command);
        assertEquals(id, command.getId());
        assertEquals(description, command.getDescription());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(unitOfMeasure));
    }

}