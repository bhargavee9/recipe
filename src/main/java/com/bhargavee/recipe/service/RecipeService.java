package com.bhargavee.recipe.service;

import com.bhargavee.recipe.model.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeService {

    public Set<Recipe> getAllRecipes();
    public Recipe getRecipe(Long id);
    public Recipe addRecipe(Recipe recipe);
    public void deleteRecipe(Long id);
}
