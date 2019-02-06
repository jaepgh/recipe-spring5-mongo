package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.UnitOfMeasureCommand;
import jaep.springframework.recipeapp.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Override
    public UnitOfMeasure convert(UnitOfMeasureCommand unitOfMeasureCommand) {

        if (unitOfMeasureCommand == null) {
            return null;
        }

        final UnitOfMeasure unit= new UnitOfMeasure();
        unit.setId(unitOfMeasureCommand.getId());
        unit.setUom(unitOfMeasureCommand.getUom());

        return unit;
    }
}
