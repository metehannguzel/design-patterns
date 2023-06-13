package bim492;

import java.util.List;

public class Recipe {
    private String title;
    private List<String> ingredients;
    private String cookingInstructions;
    private String servingSize;
    private List<String> categories;
    private List<String> tags;
    private float rating;
    private int rated = 1;

    public Recipe() {

    }

    public Recipe(String title, List<String> ingredients, String cookingInstructions, String servingSize, List<String> categories, List<String> tags, int rating) {
        this.title = title;
        this.ingredients = ingredients;
        this.cookingInstructions = cookingInstructions;
        this.servingSize = servingSize;
        this.categories = categories;
        this.tags = tags;
        this.rating=rating;
    }

    public int getRated() {
        this.rated = this.rated + 1;
        return this.rated;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getIngredients() {
        return this.ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getInstructions() {
        return this.cookingInstructions;
    }

    public void setInstructions(String cookingInstructions) {
        this.cookingInstructions = cookingInstructions;
    }

    public void setRating(float rating){
        this.rating=rating;
    }

    public float getRating() {
        return this.rating;
    }

    public String getServingSize() {
        return this.servingSize;
    }

    public void setServingSize(String servingSize) {
        this.servingSize = servingSize;
    }

    public List<String> getCategories() {
        return this.categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

}





























