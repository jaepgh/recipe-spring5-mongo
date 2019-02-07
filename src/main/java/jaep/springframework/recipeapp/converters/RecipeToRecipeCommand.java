package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand notes;
    private final IngredientToIngredientCommand ingredient;
    private final CategoryToCategoryCommand category;

    public RecipeToRecipeCommand(NotesToNotesCommand notes, IngredientToIngredientCommand ingredient, CategoryToCategoryCommand category) {
        this.notes = notes;
        this.ingredient = ingredient;
        this.category = category;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {

        if (recipe == null) {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(recipe.getId());
        command.setCookTime(recipe.getCookTime());
        command.setDescription(recipe.getDescription());
        command.setDifficulty(recipe.getDifficulty());
        command.setDirections(recipe.getDirections());
        command.setImage(recipe.getImage());
        command.setPrepTime(recipe.getPrepTime());
        command.setServings(recipe.getServings());
        command.setSource(recipe.getSource());
        command.setUrl(recipe.getUrl());

        if (recipe.getIngredients() != null && recipe.getIngredients().size() > 0 ) {
            recipe.getIngredients().forEach(ing ->
                    command.getIngredients().add(ingredient.convert(ing)));
        }

        if (recipe.getCategories() != null && recipe.getCategories().size() > 0 ) {
            recipe.getCategories().forEach(cat->
                    command.getCategories().add(category.convert(cat)));
        }

        if (recipe.getNotes() != null) {
            command.setNotes(notes.convert(recipe.getNotes()));
        }

        return command;
    }
}
