package jaep.springframework.recipeapp.controllers;

import jaep.springframework.recipeapp.domain.Recipe;
import jaep.springframework.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private final RecipeService service;

    public RecipeController(RecipeService service) {
        this.service = service;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe",service.getRecipeById(Long.valueOf(id)));

        return "recipe/show";
    }

}
