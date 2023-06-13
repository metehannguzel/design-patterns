package bim492;

public class RecipeRatingDisplay implements RecipeRatingObserver{
    private RecipeRating recipeRating;

    public RecipeRatingDisplay(RecipeRating recipeRating) {
        this.recipeRating = recipeRating;
        recipeRating.addObserver(this);
    }

    @Override
    public void update(float rating) {
        System.out.println("Recipe rating updated: " + rating);
    }
}
