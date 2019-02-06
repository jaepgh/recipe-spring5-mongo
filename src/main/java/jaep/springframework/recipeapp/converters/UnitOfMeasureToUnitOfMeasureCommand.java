package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.UnitOfMeasureCommand;
import jaep.springframework.recipeapp.domain.UnitOfMeasure;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure == null) {
            return null;
        }

        final UnitOfMeasureCommand command = new UnitOfMeasureCommand();
        command.setId(unitOfMeasure.getId());
        command.setUom(unitOfMeasure.getUom());

        return command;
    }
}
