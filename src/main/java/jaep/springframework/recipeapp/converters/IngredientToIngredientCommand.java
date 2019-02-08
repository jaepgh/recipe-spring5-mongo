package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.converter = converter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {

        if (ingredient == null) {
            return null;
        }

        final  IngredientCommand command = new IngredientCommand();
        command.setId(ingredient.getId());
        command.setAmount(ingredient.getAmount());
        command.setDescription(ingredient.getDescription());
        command.setUom(converter.convert(ingredient.getUom()));

        return command;
    }
}
