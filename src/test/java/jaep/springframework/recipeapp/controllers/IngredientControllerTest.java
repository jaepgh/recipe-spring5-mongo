package jaep.springframework.recipeapp.controllers;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.domain.UnitOfMeasure;
import jaep.springframework.recipeapp.services.IngredientService;
import jaep.springframework.recipeapp.services.RecipeService;
import jaep.springframework.recipeapp.services.UnitOfMeasureService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IngredientControllerTest {

    @Mock
    RecipeService service;

    @Mock
    IngredientService ingredientService;

    @Mock
    UnitOfMeasureService unitOfMeasureService;

    IngredientController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new IngredientController(service, ingredientService, unitOfMeasureService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void getAllIngredients() throws Exception {
        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(2L);

        when(service.findRecipeCommandById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/2/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(service, times(1)).findRecipeCommandById(anyLong());
    }

    @Test
    public void findIngredientById() throws Exception {
        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(2L);

        when(ingredientService.findIngredientCommandById(anyLong())).thenReturn(ingredient);

        mockMvc.perform(get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/detail"))
                .andExpect(model().attributeExists("ingredient"));

        verify(ingredientService, times(1)).findIngredientCommandById(anyLong());
    }

    @Test
    public void deleteIngredient() throws Exception {

        mockMvc.perform(get("/recipe/1/ingredient/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients"));

        verify(ingredientService, times(1)).deleteById(anyLong());

    }

    @Test
    public void updateIngredient() throws Exception {
        //Given
        IngredientCommand command = new IngredientCommand();

        //When
        when(ingredientService.findIngredientCommandById(anyLong()))
                .thenReturn(command);
        when(unitOfMeasureService.getAllUnitOfMeasurement())
                .thenReturn(new HashSet<>());

        //Then
        mockMvc.perform(get("/recipe/1/ingredient/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(model().attributeExists("recipeId"));
    }

    @Test
    public void newIngredient() throws Exception {
        //Given
        IngredientCommand command = new IngredientCommand();

        //When
        when(ingredientService.findIngredientCommandById(anyLong()))
                .thenReturn(command);
        when(unitOfMeasureService.getAllUnitOfMeasurement())
                .thenReturn(new HashSet<>());

        //Then
        mockMvc.perform(get("/recipe/1/ingredient/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/ingredient/ingredientform"))
                .andExpect(model().attributeExists("ingredient"))
                .andExpect(model().attributeExists("uomList"))
                .andExpect(model().attributeExists("recipeId"));


    }

    @Test
    public void saveOrUpdateIngredient() throws Exception {
        mockMvc.perform(post("/recipe/1/ingredient/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/1/ingredients"));

        verify(ingredientService, times(1))
                .saveIngredientCommand(any(), anyLong());

    }
}