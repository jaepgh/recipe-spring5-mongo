package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.converters.IngredientCommandToIngredient;
import jaep.springframework.recipeapp.converters.IngredientToIngredientCommand;
import jaep.springframework.recipeapp.domain.Ingredient;
import jaep.springframework.recipeapp.domain.Recipe;
import jaep.springframework.recipeapp.repositories.IngredientRepository;
import jaep.springframework.recipeapp.repositories.RecipeRepository;
import jaep.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {


    private final RecipeRepository repository;
    private final IngredientRepository ingredientRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final IngredientCommandToIngredient toIngredient;
    private final IngredientToIngredientCommand toIngredientCommand;

    public IngredientServiceImpl(RecipeRepository repository, IngredientRepository ingredientRepository,
                                 UnitOfMeasureRepository unitOfMeasureRepository, IngredientCommandToIngredient toIngredient,
                                 IngredientToIngredientCommand toIngredientCommand) {
        this.repository = repository;
        this.ingredientRepository = ingredientRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.toIngredient = toIngredient;
        this.toIngredientCommand = toIngredientCommand;
    }

    @Override
    public IngredientCommand saveIngredientCommand(IngredientCommand command, Long recipeId) {
        Optional<Recipe> optionalRecipe = repository.findById(recipeId);

        if (! optionalRecipe.isPresent()) {
            log.error("No recipe found for ID: " + recipeId);
            return new IngredientCommand();
        } else {
            Recipe  recipe = optionalRecipe.get();
            Optional<Ingredient> ingredientOptional = ingredientRepository.findById(command.getId());

            if(!ingredientOptional.isPresent()) {
                recipe.addIngredient(ingredientOptional.get());
            } else {
                Ingredient ingredient = ingredientOptional.get();

                ingredient.setAmount(command.getAmount());
                ingredient.setDescription(command.getDescription());
                ingredient.setUom(unitOfMeasureRepository.findById(command.getUom()
                        .getId()).orElseThrow(()->new RuntimeException("UOM not found!")));

            }

            Recipe savedRecipe = repository.save(recipe);

            return toIngredientCommand.convert(ingredientRepository.findById(command.getId()).get());
        }

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
