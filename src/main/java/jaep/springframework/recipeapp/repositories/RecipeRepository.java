package jaep.springframework.recipeapp.repositories;

import jaep.springframework.recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, String> {

}
