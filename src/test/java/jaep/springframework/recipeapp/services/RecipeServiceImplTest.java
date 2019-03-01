package jaep.springframework.recipeapp.services;

import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.converters.RecipeCommandToRecipe;
import jaep.springframework.recipeapp.converters.RecipeToRecipeCommand;
import jaep.springframework.recipeapp.domain.Recipe;
import jaep.springframework.recipeapp.exceptions.NotFoundException;
import jaep.springframework.recipeapp.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {
    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @Test
    public void getAllRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe>  recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeService.getAllRecipes()).thenReturn(recipesData);

        Set<Recipe> recipes = recipeService.getAllRecipes();
        assertEquals(recipes.size(),1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipeById() {
        Recipe recipe = new Recipe();
        recipe.setId("1L");

        when(recipeRepository.findById(anyString())).thenReturn(Optional.of(recipe));

        Recipe result = recipeService.getRecipeById("1L");
        assertNotNull(result);
        verify(recipeRepository, times(1)).findById(anyString());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void saveRecipeCommand() {
        String id = "2L";
        RecipeCommand command = new RecipeCommand();
        command.setId(id);

        when(recipeService.saveRecipeCommand(any()))
                .thenReturn(command);

        RecipeCommand result = recipeService.saveRecipeCommand(any());

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void findRecipeCommandById() {

    }

    @Test(expected = NotFoundException.class)
    public void getRecipeByIdTestNotFound(){
        Optional<Recipe> recipe = Optional.empty();

        when(recipeRepository.findById(anyString())).thenReturn(recipe);

        RecipeCommand returnRecipe = recipeService.findRecipeCommandById("1L");
    }

    @Test
    public void deleteById() {

        recipeService.deleteById(anyString());
        verify(recipeRepository, times(1)).deleteById(anyString());
    }
}