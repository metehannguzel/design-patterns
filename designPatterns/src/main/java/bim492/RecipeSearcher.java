package bim492;

import java.util.List;

public class RecipeSearcher {
    private SearchStrategy searchStrategy;

    public void setSearchStrategy(SearchStrategy searchStrategy) {
        this.searchStrategy = searchStrategy;
    }

    public List<Recipe> searchRecipes(List<Recipe> recipes, String keyword) {
        return searchStrategy.search(recipes, keyword);
    }
}
