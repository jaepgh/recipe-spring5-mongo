package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.domain.Ingredient;
import jaep.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {
    public static final Long ID = 1L;
    public static final String DESCRIPTION = "Description";
    public static final BigDecimal AMOUNT = new BigDecimal(15);
    public static final UnitOfMeasure UNIT_OF_MEASURE = new UnitOfMeasure();

    IngredientToIngredientCommand converter;

    @Before
    public void setUp() throws Exception {
        UNIT_OF_MEASURE.setId(2L);
        converter = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Test
    public void nullReference(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new Ingredient()));
    }

    @Test
    public void convert() {
        //given
        Ingredient ingredient = new Ingredient();
        ingredient.setUom(UNIT_OF_MEASURE);
        ingredient.setDescription(DESCRIPTION);
        ingredient.setAmount(AMOUNT);
        ingredient.setId(ID);

        //when
        IngredientCommand command = converter.convert(ingredient);

        //then
        assertEquals(ID, command.getId());
        assertEquals(AMOUNT, command.getAmount());
        assertEquals(DESCRIPTION, command.getDescription());
        assertEquals(UNIT_OF_MEASURE.getId(), command.getUnitOfMeasure().getId());
    }
}