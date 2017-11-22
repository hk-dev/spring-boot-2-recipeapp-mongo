package com.github.recipeapp.converters;

import com.github.recipeapp.commands.NotesCommand;
import com.github.recipeapp.models.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private NotesToNotesCommand converter;
    private Notes notes;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
        notes = new Notes();
    }

    @Test
    public void convert() throws Exception {
        Long id = 1L;
        String recipeNotes = "recipeNotes";
        notes.setId(id);
        notes.setRecipeNotes(recipeNotes);

        NotesCommand command = converter.convert(notes);

        assertNotNull(command);
        assertEquals(id, command.getId());
        assertEquals(recipeNotes, command.getRecipeNotes());
    }

    @Test
    public void testNull() throws Exception {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmpty() throws Exception {
        assertNotNull(converter.convert(notes));
    }

}