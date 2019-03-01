package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.domain.Recipe;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAllRecipes();
    Recipe getRecipeById(String id);

    RecipeCommand saveRecipeCommand(RecipeCommand testRecipeCommand);
    RecipeCommand findRecipeCommandById(String id);
    void deleteById(String id);

}
