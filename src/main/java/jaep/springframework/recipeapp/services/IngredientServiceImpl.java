package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.converters.IngredientCommandToIngredient;
import jaep.springframework.recipeapp.converters.IngredientToIngredientCommand;
import jaep.springframework.recipeapp.domain.Ingredient;
import jaep.springframework.recipeapp.repositories.IngredientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;
    private final IngredientCommandToIngredient toIngredient;
    private final IngredientToIngredientCommand toIngredientCommand;

    public IngredientServiceImpl(IngredientRepository repository, IngredientCommandToIngredient toIngredient, IngredientToIngredientCommand toIngredientCommand) {
        this.repository = repository;
        this.toIngredient = toIngredient;
        this.toIngredientCommand = toIngredientCommand;
    }

    @Override
    public IngredientCommand saveIngredientCommand(Long idRecipe, IngredientCommand testRecipeCommand) {
        return null;
    }

    @Override
    @Transactional
    public IngredientCommand findIngredientCommandById(Long idRecipe, Long idIngredient) {

        Optional<Ingredient> ingredient =  repository.findById(idIngredient);

        if (!ingredient.isPresent()) {
            log.debug("No ingredient found with ID: " + idIngredient);
            throw new RuntimeException("No ingredient found with ID: " + idIngredient);
        }

        return toIngredientCommand.convert(ingredient.get());
    }

    @Override
    public void deleteById(Long idRecipe, Long id) {

    }
}
