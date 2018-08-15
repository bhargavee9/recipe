package com.bhargavee.recipe.service;

import com.bhargavee.recipe.model.Ingredient;

public interface IngredientService {
    public Ingredient getIngredientById(Long id);
    public void updateIngredient(Long recipe_id,Ingredient ingredient);
    public void deleteIngredient(Long recipe_id,Long ing_id);
}
