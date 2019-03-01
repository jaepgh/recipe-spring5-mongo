package jaep.springframework.recipeapp.controllers;

import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.domain.Recipe;
import jaep.springframework.recipeapp.exceptions.NotFoundException;
import jaep.springframework.recipeapp.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RecipeControllerTest {

    @Mock
    RecipeService service;

    RecipeController controller;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ControllerExceptionHandler())
                .build();
    }

    @Test
    public void showById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId("4L");

        when(service.getRecipeById(anyString())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }


    @Test
    public void showByIdNotFound() throws Exception {

        when(service.getRecipeById(anyString())).thenThrow(NotFoundException.class);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isNotFound())
                .andExpect(view().name("errors/404error"));
    }


    @Test
    public void newRecipe() throws Exception {

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void updateRecipe() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId("4L");

        when(service.findRecipeCommandById(anyString())).thenReturn(command);

        mockMvc.perform(get("/recipe/4/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeform"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void saveOrUpdate() throws Exception {
        RecipeCommand command = new RecipeCommand();
        command.setId("4L");

        when(service.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "2")
                .param("description", "something")
                .param("directions", "bla bla bla")
                .param("url", "https://www.google.com/")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/4L/show"));

    }

    @Test
    public void deleteRecipe() throws Exception {
        mockMvc.perform(get("/recipe/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/"));

        verify(service, times(1)).deleteById(anyString());
    }
}