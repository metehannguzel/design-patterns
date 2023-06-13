package bim492;

import java.util.ArrayList;
import java.util.List;

public class RecipeRating {
    private List<RecipeRatingObserver> observers;
    private float rating;

    public RecipeRating() {
        observers = new ArrayList<>();
        rating = 0;
    }

    public void addObserver(RecipeRatingObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(RecipeRatingObserver observer) {
        observers.remove(observer);
    }

    public void setRating(Recipe recipe, float rating) {
        this.rating = rating;
        float oldRating = recipe.getRating();
        recipe.setRating((rating + oldRating) / recipe.getRated());
        notifyObservers();
    }

    private void notifyObservers() {
        for (RecipeRatingObserver observer : observers) {
            observer.update(rating);
        }
    }
}
