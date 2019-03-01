package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.converters.IngredientCommandToIngredient;
import jaep.springframework.recipeapp.converters.IngredientToIngredientCommand;
import jaep.springframework.recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import jaep.springframework.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import jaep.springframework.recipeapp.repositories.IngredientRepository;
import jaep.springframework.recipeapp.repositories.RecipeRepository;
import jaep.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class IngredientServiceImplTest {

    IngredientServiceImpl service;

    @Mock
    RecipeRepository repository;
    @Mock
    IngredientRepository ingredientRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        service = new IngredientServiceImpl(repository, ingredientRepository,
                unitOfMeasureRepository, new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure()),
                new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand()));
    }

    @Test
    public void saveIngredientCommand() {
    }

    @Test
    public void findIngredientCommandById() {
    }

    @Test
    public void deleteById() {
        ingredientRepository.deleteById(anyString());

        verify(ingredientRepository, times(1)).deleteById(anyString());
    }
}