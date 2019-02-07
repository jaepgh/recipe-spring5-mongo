package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.converters.RecipeCommandToRecipe;
import jaep.springframework.recipeapp.converters.RecipeToRecipeCommand;
import jaep.springframework.recipeapp.domain.Recipe;
import jaep.springframework.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeRepository = recipeRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }


    @Override
    public Set<Recipe> getAllRecipes() {
        log.debug("Inside receive service implementation");
        Set<Recipe> result = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(result::add);

        return result;
    }

    @Override
    public Recipe getRecipeById(Long id){
        Optional<Recipe> recipeOptional =recipeRepository.findById(id);

        if(!recipeOptional.isPresent()){
            throw new  RuntimeException("Recipe not found");
        }

        return recipeOptional.get();
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipe.convert(command);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId:" + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
