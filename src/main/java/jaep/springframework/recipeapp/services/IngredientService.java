package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand saveIngredientCommand(Long idRecipe, IngredientCommand testRecipeCommand);
    IngredientCommand findIngredientCommandById(Long idRecipe, Long idIngredient);
    void deleteById(Long idRecipe, Long id);
}
