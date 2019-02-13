package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.CategoryCommand;
import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.commands.NotesCommand;
import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeCommandToRecipeTest {

    public static final Long ID = 1L;
    public static final String URL = "some url";
    public static final String SOURCE = "some source";
    public static final Integer SERVINGS = 1;
    public static final Integer PREP_TIME = 10;
    public static final NotesCommand NOTE = new NotesCommand();
    public static final Byte[] IMAGE = new Byte[40];
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.MODERATE;
    public static final String DESCRIPTION = "Description";
    public static final Integer COOK_TIME = 15;
    public static final Set<CategoryCommand> CATEGORIES = new HashSet<>();
    public static final Set<IngredientCommand> INGREDIENTS = new HashSet<>();

    static {
        CategoryCommand c1 = new CategoryCommand();
        c1.setId(1L);
        CATEGORIES.add(c1);
        CategoryCommand c2 = new CategoryCommand();
        c2.setId(2L);
        CATEGORIES.add(c2);
        CategoryCommand c3 = new CategoryCommand();
        c3.setId(3L);
        CATEGORIES.add(c3);


        IngredientCommand i1 = new IngredientCommand();
        i1.setId(1L);
        INGREDIENTS.add(i1);
        IngredientCommand i2 = new IngredientCommand();
        i2.setId(2L);
        INGREDIENTS.add(i2);
        IngredientCommand i3 = new IngredientCommand();
        i3.setId(3L);
        INGREDIENTS.add(i3);
        IngredientCommand i4 = new IngredientCommand();
        i4.setId(4L);
        INGREDIENTS.add(i4);
    }

    RecipeCommandToRecipe converter;

    @Before
    public void setUp() throws Exception {

        converter = new RecipeCommandToRecipe(new CategoryCommandToCategory(),
                new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new NotesCommandToNotes());

        NOTE.setId(4L);
    }

    @Test
    public void nullReference(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new RecipeCommand()));
    }


    @Test
    public void convert() {
        //given
        RecipeCommand command = new RecipeCommand();
        command.setUrl(URL);
        command.setSource(SOURCE);
        command.setServings(SERVINGS);
        command.setPrepTime(PREP_TIME);
        command.setNotes(NOTE);
        command.setImage(IMAGE);
        command.setDirections(DIRECTIONS);
        command.setDifficulty(DIFFICULTY);
        command.setDescription(DESCRIPTION);
        command.setCookTime(COOK_TIME);
        command.setId(ID);
        command.setCategories(CATEGORIES);
        command.setIngredients(INGREDIENTS);

        //when
        Recipe recipe = converter.convert(command);

        //then
        assertEquals(URL, recipe.getUrl());
        assertEquals(SOURCE, recipe.getSource());
        assertEquals(SERVINGS, recipe.getServings());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(NOTE.getId(), recipe.getNotes().getId());
        assertEquals(IMAGE.length, recipe.getImage().length);
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(ID, recipe.getId());
        assertEquals(CATEGORIES.size(), recipe.getCategories().size());
        assertEquals(INGREDIENTS.size(), recipe.getIngredients().size());
    }
}