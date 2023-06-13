package bim492;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RecipeManagementSystem {
    private static final List<Recipe> recipes = new ArrayList<>();

    public static void mainMenu() {
        RecipeManagementSystem rms = new RecipeManagementSystem();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Main Menu");
            System.out.println("1. Create Recipe");
            System.out.println("2. Search Recipes");
            System.out.println("3. Rate Recipe");
            System.out.println("4. Modify Recipe");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            int option = readIntInput(scanner);
            switch (option) {
                case 1 -> rms.createRecipe();
                case 2 -> rms.searchRecipe();
                case 3 -> rms.rateRecipe();
                case 4 -> rms.modifyRecipe();
                case 5 -> System.exit(0);
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void main(String[] args) {
        Recipe pasta = new Recipe("Pasta Carbonara", List.of("Pasta", "Bacon", "Eggs", "Parmesan", "Black Pepper"), "Boil pasta,Fry bacon,Whisk eggs,Mix everything", "1", List.of("Main Dishes"), List.of("Hot"), 5);
        Recipe chickenStirFry = new Recipe("Chicken Stir-Fry", List.of("Eggs", "Chicken Breast", "Bell Peppers", "Broccoli", "Carrots", "Soy Sauce", "Garlic"), "Slice chicken and vegetables,Heat oil in a pan,Cook chicken until browned,Add vegetables and stir-fry,Add soy sauce and garlic,Cook until vegetables are tender", "2", List.of("Main Dishes"), List.of("Healthy", "Quick and Easy"), 3);
        Recipe berrySmoothie = new Recipe("Berry Smoothie", List.of("Frozen Mixed Berries", "Banana", "Yogurt", "Honey", "Milk"), "Add all ingredients to a blender,Blend until smooth,Pour into glasses and serve", "2", List.of("Beverages", "Breakfast"), List.of("Vegetarian", "Healthy"), 4);
        recipes.add(pasta);
        recipes.add(chickenStirFry);
        recipes.add(berrySmoothie);
        mainMenu();
    }

    private static int readIntInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Please enter an number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public void createRecipe() {
        Scanner scanner = new Scanner(System.in);
        String title = "";
        List<String> ingredients = new ArrayList<>();
        String cookingInstructions = "";
        String servingSize = "";
        List<String> categories = new ArrayList<>();
        List<String> tags = new ArrayList<>();

        while (true) {
            System.out.println("Creating Recipe");
            System.out.println("1. Enter title: " + title);
            System.out.println("2. Enter ingredients: " + ingredients);
            System.out.println("3. Enter cooking instructions: " + cookingInstructions);
            System.out.println("4. Enter serving size(1, 1.5 or 2): " + servingSize);
            System.out.println("5. Enter up to three categories: " + categories);
            System.out.println("6. Enter up to three tags: " + tags);
            System.out.println("7. Save recipe");
            System.out.println("8. Back");
            System.out.print("Select an option: ");
            int option = readIntInput(scanner);

            switch (option) {
                case 1:
                    System.out.print("Enter title: ");
                    title = scanner.nextLine();
                    break;
                case 2:
                    System.out.print("Enter ingredients: ");
                    String ingredient;
                    do {
                        ingredient = scanner.nextLine();
                        if (!ingredient.isEmpty() && !ingredient.equalsIgnoreCase("done")) {
                            ingredients.add(ingredient);
                        }
                    } while (!ingredient.equalsIgnoreCase("done"));
                    break;
                case 3:
                    System.out.print("Enter cooking instructions: ");
                    cookingInstructions = scanner.nextLine();
                    break;
                case 4:
                    System.out.print("Enter serving size(1, 1.5 or 2): ");
                    while (true) {
                        servingSize = scanner.nextLine();
                        if (servingSize.equals("1") || servingSize.equals("1.5") || servingSize.equals("2") || servingSize.equals("1,5")) {
                            break;
                        } else {
                            System.out.print("Try again, Enter serving size(1, 1.5 or 2): ");
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter up to three categories (press Enter after each category, or enter 'done' to finish):");
                    String category;
                    do {
                        category = scanner.nextLine();
                        if (!category.isEmpty() && !category.equalsIgnoreCase("done")) {
                            categories.add(category);
                        }
                    } while (!category.equalsIgnoreCase("done") && categories.size() < 3);
                    break;
                case 6:
                    System.out.println("Enter up to three tags (press Enter after each tag, or enter 'done' to finish):");
                    String tag;
                    do {
                        tag = scanner.nextLine();
                        if (!tag.isEmpty() && !tag.equalsIgnoreCase("done")) {
                            tags.add(tag);
                        }
                    } while (!tag.equalsIgnoreCase("done") && tags.size() < 3);
                    break;
                case 7:
                    if (!title.equals("") && !ingredients.isEmpty() && !cookingInstructions.equals("") && !servingSize.equals("") && !categories.isEmpty() && !tags.isEmpty()) {
                        Recipe recipe = RecipeFactory.createRecipe(title, ingredients, cookingInstructions, servingSize, categories, tags);
                        recipes.add(recipe);
                        System.out.println("Recipe saved successfully!");
                    } else System.out.println("Please enter recipe details before saving.");
                    break;
                case 8:
                    mainMenu();
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public void searchRecipe() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select that you want to search based on what?");
        System.out.println("1. Search based on ingredients");
        System.out.println("2. Search based on categories");
        System.out.println("3. Search based on tags");
        System.out.println("4. Back to main menu");
        String choice = scanner.nextLine();
        int option = Integer.parseInt(choice);

        while (true) {
            switch (option) {
                case 1 -> {
                    System.out.print("Enter keyword: ");
                    RecipeSearcher recipeSearcher = new RecipeSearcher();
                    recipeSearcher.setSearchStrategy(new SearchByIngredientsStrategy());
                    String keyword = scanner.nextLine();
                    List<Recipe> searchResults = recipeSearcher.searchRecipes(recipes, keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No results");
                        searchRecipe();
                    } else {
                        for (int i = 0; i < recipes.size(); i++) {
                            System.out.println((i + 1) + "-" + recipes.get(i).getTitle());
                        }
                        viewDetails(searchResults);
                    }
                }
                case 2 -> {
                    System.out.print("Enter keyword: ");
                    RecipeSearcher recipeSearcher = new RecipeSearcher();
                    recipeSearcher.setSearchStrategy(new SearchByCategoriesStrategy());
                    String keyword = scanner.nextLine();
                    List<Recipe> searchResults = recipeSearcher.searchRecipes(recipes, keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No results");
                        searchRecipe();
                    } else {
                        for (int i = 0; i < recipes.size(); i++) {
                            System.out.println((i + 1) + "-" + recipes.get(i).getTitle());
                        }
                        viewDetails(searchResults);
                    }
                }
                case 3 -> {
                    System.out.print("Enter keyword: ");
                    RecipeSearcher recipeSearcher = new RecipeSearcher();
                    recipeSearcher.setSearchStrategy(new SearchByTagsStrategy());
                    String keyword = scanner.nextLine();
                    List<Recipe> searchResults = recipeSearcher.searchRecipes(recipes, keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("No results");
                        searchRecipe();
                    } else {
                        for (int i = 0; i < recipes.size(); i++) {
                            System.out.println((i + 1) + "-" + recipes.get(i).getTitle());
                        }
                        viewDetails(searchResults);
                    }
                }
                case 4 -> mainMenu();
                default -> System.out.println("Invalid option. Please try again.");

            }
        }
    }

    public void rateRecipe() {
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + "-" + recipes.get(i).getTitle() + "- Rating: " + recipes.get(i).getRating());
        }
        System.out.println("Select the recipe to rate(0 to main menu):");
        String choice = scanner.nextLine();
        int option = Integer.parseInt(choice);
        if (option == 0) {
            mainMenu();
        }
        Recipe selectedRecipe = recipes.get(option - 1);
        RecipeRating recipeRating = new RecipeRating();
        RecipeRatingDisplay recipeRatingDisplay = new RecipeRatingDisplay(recipeRating);
        System.out.println("Enter recipe rating (1-5): ");
        int rating = scanner.nextInt();
        recipeRating.setRating(selectedRecipe, rating);
        rateRecipe();
    }

    public void viewDetails(List<Recipe> searchResults) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select which you want to see details");
        String choice = scanner.nextLine();
        int option = Integer.parseInt(choice);
        Recipe selected = searchResults.get(option - 1);
        System.out.println("Title: " + selected.getTitle());
        System.out.println("Ingredients: " + selected.getIngredients());
        System.out.println("Instructions: " + selected.getInstructions());
        System.out.println("Serving Size: " + selected.getServingSize());
        System.out.println("Categories: " + selected.getCategories());
        System.out.println("Tags: " + selected.getTags());
        System.out.println("Enter 1 to go main menu");
        while (true) {
            int back = readIntInput(scanner);
            if (back == 1) {
                mainMenu();
            }
        }
    }

    public void modifyRecipe() {
        RecipeModificationInvoker invoker = new RecipeModificationInvoker();
        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + "-" + recipes.get(i).getTitle() + "\nIngredients: " + recipes.get(i).getIngredients() + "\nInstructions: " + recipes.get(i).getInstructions() + "\nServing Size: " + recipes.get(i).getServingSize() + "\nCategories: " + recipes.get(i).getCategories() + "\nTags: " + recipes.get(i).getTags() + "\n---------------------");
        }
        System.out.println("Please enter the number of recipe: ");
        String choice = scanner.nextLine();
        int option = Integer.parseInt(choice);

        String title = recipes.get(option - 1).getTitle();
        List<String> ingredients = recipes.get(option - 1).getIngredients();
        String cookingInstructions = recipes.get(option - 1).getInstructions();
        String servingSize = recipes.get(option - 1).getServingSize();
        List<String> categories = recipes.get(option - 1).getCategories();
        List<String> tags = recipes.get(option - 1).getTags();
        while (true) {
            System.out.println("Modifying Recipe");
            System.out.println("1. Enter new title: " + title);
            System.out.println("2. Enter new ingredients: " + ingredients);
            System.out.println("3. Enter new cooking instructions: " + cookingInstructions);
            System.out.println("4. Enter new serving size(1, 1.5 or 2): " + servingSize);
            System.out.println("5. Enter new up to three categories: " + categories);
            System.out.println("6. Enter new up to three tags: " + tags);
            System.out.println("7. Save recipe");
            System.out.println("8. Undo last modifications(If you didn't save, can't undo)");
            System.out.println("9. Back to main menu");
            System.out.print("Select an option: ");
            int option1 = readIntInput(scanner);


            switch (option1) {
                case 1 -> {
                    System.out.print("Enter title: ");
                    title = scanner.nextLine();
                }
                case 2 -> {
                    System.out.print("Enter ingredients: ");
                    String ingredient;
                    do {
                        ingredient = scanner.nextLine();
                        if (!ingredient.isEmpty() && !ingredient.equalsIgnoreCase("done")) {
                            ingredients.add(ingredient);
                        }
                    } while (!ingredient.equalsIgnoreCase("done"));
                }
                case 3 -> {
                    System.out.print("Enter cooking instructions: ");
                    cookingInstructions = scanner.nextLine();
                }
                case 4 -> {
                    System.out.print("Enter serving size(1, 1.5 or 2): ");
                    while (true) {
                        servingSize = scanner.nextLine();
                        if (servingSize.equals("1") || servingSize.equals("1.5") || servingSize.equals("2") || servingSize.equals("1,5")) {
                            break;
                        } else {
                            System.out.print("Try again, Enter serving size(1, 1.5 or 2): ");
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Enter up to three categories (press Enter after each category, or enter 'done' to finish):");
                    String category;
                    do {
                        category = scanner.nextLine();
                        if (!category.isEmpty() && !category.equalsIgnoreCase("done")) {
                            categories.add(category);
                        }
                    } while (!category.equalsIgnoreCase("done") && categories.size() < 3);
                }
                case 6 -> {
                    System.out.println("Enter up to three tags (press Enter after each tag, or enter 'done' to finish):");
                    String tag;
                    do {
                        tag = scanner.nextLine();
                        if (!tag.isEmpty() && !tag.equalsIgnoreCase("done")) {
                            tags.add(tag);
                        }
                    } while (!tag.equalsIgnoreCase("done") && tags.size() < 3);
                }
                case 7 -> {
                    Command modifyCommand = new ModifyRecipeCommand(recipes.get(option - 1), title, servingSize, ingredients, cookingInstructions, categories, tags);
                    invoker.executeCommand(modifyCommand);
                }
                case 8 -> {
                    invoker.undoLastCommand();
                    modifyRecipe();
                }
                case 9 -> mainMenu();
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
