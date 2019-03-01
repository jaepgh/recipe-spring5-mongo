package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand saveIngredientCommand(IngredientCommand command, String recipeId);
    IngredientCommand findIngredientCommandById(String idIngredient);
    void deleteById(String id);
}
