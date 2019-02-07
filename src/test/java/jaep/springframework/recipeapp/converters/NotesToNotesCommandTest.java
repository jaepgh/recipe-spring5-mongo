package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.NotesCommand;
import jaep.springframework.recipeapp.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    public static final Long ID = 1L;
    public static final String RECIPE_NOTES = "Notes";

    NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }

    @Test
    public void nullReference(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    public void convert() {
        //given
        Notes note = new Notes();
        note.setId(ID);
        note.setRecipeNotes(RECIPE_NOTES);

        //when
        NotesCommand command = converter.convert(note);
        //then
        assertEquals(ID, command.getId());
        assertEquals(RECIPE_NOTES, command.getRecipeNotes());
    }
}