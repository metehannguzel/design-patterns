package bim492;

import java.util.ArrayList;
import java.util.List;

class ModifyRecipeCommand implements Command {
    private Recipe recipe;
    private String newName;
    private String oldName;
    private List<String> newIngredients;
    private List<String> oldIngredients;
    private String newInstructions;
    private String oldInstructions;
    private String newSize;
    private String oldSize;
    private List<String> newCategories;
    private List<String> oldCategories;
    private List<String> newTags;
    private List<String> oldTags;


    public ModifyRecipeCommand(Recipe recipe, String newName, String newSize, List<String> newIngredients, String newInstructions,
                               List<String> newCategories, List<String> newTags) {
        this.recipe = recipe;
        this.newName = newName;
        this.newSize = newSize;
        this.newIngredients = newIngredients;
        this.newInstructions = newInstructions;
        this.newCategories = newCategories;
        this.newTags = newTags;

        // Backup old values for undo operation
        this.oldName = recipe.getTitle();
        this.oldIngredients = new ArrayList<>(recipe.getIngredients());
        this.oldInstructions = recipe.getInstructions();
        this.oldSize = recipe.getServingSize();
        this.oldCategories = new ArrayList<>(recipe.getCategories());
        this.oldTags = new ArrayList<>(recipe.getTags());
    }

    @Override
    public void execute() {
        // Modify the recipe with new values
        recipe.setTitle(newName);
        recipe.setServingSize(newSize);
        recipe.setIngredients(newIngredients);
        recipe.setInstructions(newInstructions);
        recipe.setCategories(newCategories);
        recipe.setTags(newTags);
    }

    @Override
    public void undo() {
        // Revert the recipe to its previous state
        recipe.setTitle(oldName);
        recipe.setServingSize(oldSize);
        recipe.setIngredients(oldIngredients);
        recipe.setInstructions(oldInstructions);
        recipe.setCategories(oldCategories);
        recipe.setTags(oldTags);
    }
}