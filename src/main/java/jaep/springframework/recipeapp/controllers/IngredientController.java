package jaep.springframework.recipeapp.controllers;

import jaep.springframework.recipeapp.commands.IngredientCommand;
import jaep.springframework.recipeapp.commands.UnitOfMeasureCommand;
import jaep.springframework.recipeapp.services.IngredientService;
import jaep.springframework.recipeapp.services.RecipeService;
import jaep.springframework.recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService service;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService service, IngredientService ingredientService,
                                UnitOfMeasureService unitOfMeasureService) {
        this.service = service;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }


    @GetMapping("/recipe/{id}/ingredients")
    public String getAllIngredients(@PathVariable String id, Model model){

        log.debug("Getting ingredients for reciped ID: " + id);
        model.addAttribute("recipe",service.findRecipeCommandById(id));

        return "recipe/ingredient/list";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/show")
    public String findIngredientById(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

        log.debug("Getting ingredients for reciped ID: " + recipeId);
        model.addAttribute("ingredient", ingredientService
                .findIngredientCommandById(ingredientId));

        return "recipe/ingredient/detail";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId,
                                   Model model){
        model.addAttribute("ingredient", ingredientService
                .findIngredientCommandById(ingredientId));
        model.addAttribute("uomList", unitOfMeasureService.getAllUnitOfMeasurement());
        model.addAttribute("recipeId", recipeId);

        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId,
                                   Model model){
        IngredientCommand command = new IngredientCommand();
        command.setUom(new UnitOfMeasureCommand());

        model.addAttribute("ingredient", command);
        model.addAttribute("uomList", unitOfMeasureService.getAllUnitOfMeasurement());
        model.addAttribute("recipeId", recipeId);

        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient/")
    public String saveOrUpdateIngredient(@ModelAttribute IngredientCommand command, @PathVariable String recipeId){

        IngredientCommand saved = ingredientService.saveIngredientCommand(command, recipeId);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId){

        log.debug("Deleting ingredient ID: " + ingredientId + " for recipe ID: " + recipeId);
        ingredientService.deleteById(ingredientId);

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}

