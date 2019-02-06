package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.domain.Recipe;
import java.util.Set;

public interface RecipeService {
    Set<Recipe> getAllRecipes();
    Recipe getRecipeById(Long id);
}
