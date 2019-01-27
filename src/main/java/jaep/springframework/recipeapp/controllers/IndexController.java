package jaep.springframework.recipeapp.controllers;

import jaep.springframework.recipeapp.domain.Category;
import jaep.springframework.recipeapp.domain.UnitOfMeasure;
import jaep.springframework.recipeapp.repositories.CategoryRepository;
import jaep.springframework.recipeapp.repositories.RecipeRepository;
import jaep.springframework.recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {
    private RecipeRepository recipeRepository;

    public IndexController(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    @RequestMapping({"","/","/index"})
    public String getIndexPage(Model model){
        //model.addAttribute("recipes", recipeRepository.findAll());
        return "index";
    }
}
