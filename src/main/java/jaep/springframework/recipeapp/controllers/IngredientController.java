package jaep.springframework.recipeapp.controllers;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.services.IngredientService;
import jaep.springframework.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService service;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService service, IngredientService ingredientService) {
        this.service = service;
        this.ingredientService = ingredientService;
    }


    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getAllIngredients(@PathVariable String id, Model model){

        log.debug("Getting ingredients for reciped ID: " + id);
        model.addAttribute("recipe",service.findRecipeCommandById(Long.valueOf(id)));

        return "recipe/ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String findIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

        log.debug("Getting ingredients for reciped ID: " + recipeId);
        model.addAttribute("ingredient", ingredientService
                .findIngredientCommandById(Long.valueOf(ingredientId)));

        return "recipe/ingredient/detail";
    }

    @PostMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String saveOrUpdateIngredient(@PathVariable String recipeId,@PathVariable String ingredientId,
                                         @ModelAttribute IngredientCommand command){

        IngredientCommand saved = ingredientService.saveIngredientCommand(command);

        return "/recipe/" + recipeId + "/ingredients";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId){

        log.debug("Deleting ingredient ID: " + ingredientId + " for recipe ID: " + recipeId);
        ingredientService.deleteById(Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}

