package bim492;

import java.util.ArrayList;
import java.util.List;

public class SearchByIngredientsStrategy implements SearchStrategy {
    @Override
    public List<Recipe> search(List<Recipe> recipes, String keyword) {
        String lowercaseKeyword = keyword.toLowerCase();
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipes) {
            List<String> ingredients = recipe.getIngredients();
            boolean found = false;
            for (String ingredient : ingredients) {
                if (ingredient.toLowerCase().contains(lowercaseKeyword)) {
                    found = true;
                    break;
                }
            }
            if (found) {
                searchResults.add(recipe);
            }
        }
        return searchResults;
    }
}