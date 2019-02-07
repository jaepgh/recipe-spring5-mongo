package jaep.springframework.recipeapp.converters;

import jaep.springframework.recipeapp.commands.RecipeCommand;
import jaep.springframework.recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategory commandToCategory;
    private final IngredientCommandToIngredient commandToIngredient;
    private final NotesCommandToNotes commandToNotes;

    public RecipeCommandToRecipe(CategoryCommandToCategory commandToCategory,
                                 IngredientCommandToIngredient commandToIngredient,
                                 NotesCommandToNotes commandToNotes) {
        this.commandToCategory = commandToCategory;
        this.commandToIngredient = commandToIngredient;
        this.commandToNotes = commandToNotes;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand recipeCommand) {

        if (recipeCommand == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(recipeCommand.getId());
        recipe.setCookTime(recipeCommand.getCookTime());
        recipe.setDescription(recipeCommand.getDescription());
        recipe.setDifficulty(recipeCommand.getDifficulty());
        recipe.setDirections(recipeCommand.getDirections());
        recipe.setImage(recipeCommand.getImage());
        recipe.setPrepTime(recipeCommand.getPrepTime());
        recipe.setServings(recipeCommand.getServings());
        recipe.setSource(recipeCommand.getSource());
        recipe.setUrl(recipeCommand.getUrl());

        if (recipeCommand.getCategories() != null && recipeCommand.getCategories().size() > 0) {
            recipeCommand.getCategories().forEach(comCat ->
                    recipe.getCategories().add(commandToCategory.convert(comCat)));
        }


        if (recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0) {
            recipeCommand.getIngredients().forEach(comIn ->
                    recipe.getIngredients().add(commandToIngredient.convert(comIn)));
        }

        if (recipeCommand.getNotes() != null) {
            recipe.setNotes(commandToNotes.convert(recipeCommand.getNotes()));
        }

        return recipe;
    }
}
