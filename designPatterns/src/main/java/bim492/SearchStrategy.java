package bim492;

import java.util.List;

public interface SearchStrategy {
    List<Recipe> search(List<Recipe> recipes, String keyword);
}
