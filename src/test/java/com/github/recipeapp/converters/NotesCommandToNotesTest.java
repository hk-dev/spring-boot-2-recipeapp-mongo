package com.github.recipeapp.converters;

import com.github.recipeapp.commands.NotesCommand;
import com.github.recipeapp.models.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private NotesCommandToNotes converter;
    private NotesCommand command;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
        command = new NotesCommand();
    }

    @Test
    public void convert() throws Exception {
        String id = "1";
        String recipeNotes = "recipeNotes";
        command.setId(id);
        command.setRecipeNotes(recipeNotes);

        Notes notes = converter.convert(command);

        assertNotNull(notes);
        assertEquals(id, notes.getId());
        assertEquals(recipeNotes, notes.getRecipeNotes());
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