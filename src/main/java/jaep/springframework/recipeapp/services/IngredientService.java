package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand saveIngredientCommand(IngredientCommand command);
    IngredientCommand findIngredientCommandById(Long idIngredient);
    void deleteById(Long id);
}
