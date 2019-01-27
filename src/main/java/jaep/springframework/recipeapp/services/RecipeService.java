package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.domain.Recipe;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public interface RecipeService {
    Set<Recipe> getAllRecipes();
}
