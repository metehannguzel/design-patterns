package bim492;

import java.util.ArrayList;
import java.util.List;

public class SearchByCategoriesStrategy implements SearchStrategy {
    @Override
    public List<Recipe> search(List<Recipe> recipes, String keyword) {
        String lowercaseKeyword = keyword.toLowerCase();
        List<Recipe> searchResults = new ArrayList<>();
        for (Recipe recipe : recipes) {
            List<String> categories = recipe.getCategories();
            boolean found = false;
            for (String category : categories) {
                if (category.toLowerCase().contains(lowercaseKeyword)) {
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