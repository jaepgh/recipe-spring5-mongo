package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.UnitOfMeasureCommand;
import jaep.springframework.recipeapp.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static final String ID = "1L";
    public static final String DESCRIPTION = "Some";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void nullReference(){
        assertNull(converter.convert(null));
    }

    @Test
    public void emptyObject(){
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    public void convert() {
        //given
        UnitOfMeasure unit = new UnitOfMeasure();
        unit.setId(ID);
        unit.setUom(DESCRIPTION);

        //when
        UnitOfMeasureCommand command = converter.convert(unit);

        //then
        assertEquals(ID, command.getId());
        assertEquals(DESCRIPTION, command.getUom());
    }
}