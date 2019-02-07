package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.NotesCommand;
import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.domain.*;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class RecipeToRecipeCommandTest {

    public static final Long ID = 1L;
    public static final String URL = "some url";
    public static final String SOURCE = "some source";
    public static final Integer SERVINGS = 1;
    public static final Integer PREP_TIME = 10;
    public static final Notes NOTE = new Notes();
    public static final byte[] IMAGE = new byte[40];
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.MODERATE;
    public static final String DESCRIPTION = "Description";
    public static final Integer COOK_TIME = 15;
    public static final Set<Category> CATEGORIES = new HashSet<>();
    public static final Set<Ingredient> INGREDIENTS = new HashSet<>();

    static {
        Category c1 = new Category();
        c1.setId(1L);
        CATEGORIES.add(c1);
        Category c2 = new Category();
        c2.setId(2L);
        CATEGORIES.add(c2);
        Category c3 = new Category();
        c3.setId(3L);
        CATEGORIES.add(c3);


        Ingredient i1 = new Ingredient();
        i1.setId(1L);
        INGREDIENTS.add(i1);
        Ingredient i2 = new Ingredient();
        i2.setId(2L);
        INGREDIENTS.add(i2);
        Ingredient i3 = new Ingredient();
        i3.setId(3L);
        INGREDIENTS.add(i3);
        Ingredient i4 = new Ingredient();
        i4.setId(4L);
        INGREDIENTS.add(i4);
    }

    RecipeToRecipeCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new RecipeToRecipeCommand(new NotesToNotesCommand(),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()),
                new CategoryToCategoryCommand());

        NOTE.setId(4L);
    }

    @Test
    public void nullReference(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new Recipe()));
    }

    @Test
    public void convert() {
        //given
        Recipe command = new Recipe();
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
        RecipeCommand recipe = converter.convert(command);

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