package jaep.springframework.recipeapp.controllers;

import jaep.springframework.recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class IngredientController {

    private final RecipeService service;

    public IngredientController(RecipeService service) {
        this.service = service;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getAllIngredients(@PathVariable String id, Model model){

        log.debug("Getting ingredients for reciped ID: " + id);
        model.addAttribute("recipe",service.findRecipeCommandById(Long.valueOf(id)));

        return "recipe/ingredient/list";
    }
}
