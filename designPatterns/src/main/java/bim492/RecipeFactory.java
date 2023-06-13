package bim492;

import java.util.List;

public class RecipeFactory {
    public static Recipe createRecipe(String title, List<String> ingredients, String cookingInstructions, String servingSize, List<String> categories, List<String> tags) {
        Recipe recipe = new Recipe();
        recipe.setTitle(title);
        recipe.setIngredients(ingredients);
        recipe.setInstructions(cookingInstructions);
        recipe.setServingSize(servingSize);
        recipe.setCategories(categories);
        recipe.setTags(tags);
        return recipe;
    }
}
