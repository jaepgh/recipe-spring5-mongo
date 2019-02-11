package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.converters.IngredientCommandToIngredient;
import jaep.springframework.recipeapp.converters.IngredientToIngredientCommand;
import jaep.springframework.recipeapp.domain.Ingredient;
import jaep.springframework.recipeapp.domain.Recipe;
import jaep.springframework.recipeapp.repositories.IngredientRepository;
import jaep.springframework.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {


    private final RecipeRepository repository;
    private final IngredientRepository ingredientRepository;
    private final IngredientCommandToIngredient toIngredient;
    private final IngredientToIngredientCommand toIngredientCommand;

    public IngredientServiceImpl(RecipeRepository repository, IngredientRepository ingredientRepository,
                                 IngredientCommandToIngredient toIngredient,
                                 IngredientToIngredientCommand toIngredientCommand) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;
        this.toIngredient = toIngredient;
        this.toIngredientCommand = toIngredientCommand;
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        return null;
    }

    @Override
    @Transactional
    public IngredientCommand findIngredientCommandById(Long idIngredient) {

        Optional<Ingredient> result = ingredientRepository.findById(idIngredient);

        if (!result.isPresent()) {
            log.debug("No ingredient found with ID: " + idIngredient);
            throw new RuntimeException("No ingredient found with ID: " + idIngredient);
        }

        return toIngredientCommand.convert(result.get());
    }

    @Override
    public void deleteById(Long idIngredient) {

        Optional<Ingredient> result = ingredientRepository.findById(idIngredient);

        if (!result.isPresent()) {
            log.debug("No ingredient found with ID: " + idIngredient);
            throw new RuntimeException("No ingredient found with ID: " + idIngredient);
        }

        ingredientRepository.deleteById(idIngredient);

    }
}
