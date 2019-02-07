package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.commands.UnitOfMeasureCommand;
import jaep.springframework.recipeapp.domain.Ingredient;
import jaep.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {
    public static final Long ID = 1L;
    public static final String DESCRIPTION = "Description";
    public static final BigDecimal AMOUNT = new BigDecimal(15);
    public static final UnitOfMeasureCommand UNIT_OF_MEASURE = new UnitOfMeasureCommand();

    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        UNIT_OF_MEASURE.setId(2L);
        converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
    }

    @Test
    public void nullReference(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(new IngredientCommand());
    }

    @Test
    public void convert() {
        //given
        IngredientCommand command = new IngredientCommand();
        command.setId(ID);
        command.setDescription(DESCRIPTION);
        command.setAmount(AMOUNT);
        command.setUnitOfMeasure(UNIT_OF_MEASURE);

        //when
        Ingredient ingredient = converter.convert(command);

        //then
        assertEquals(ID, ingredient.getId());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(UNIT_OF_MEASURE.getId(), ingredient.getUom().getId());
    }
}