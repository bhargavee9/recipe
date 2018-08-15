package com.bhargavee.recipe.repository;

import com.bhargavee.recipe.model.Ingredient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface IngredientRepository extends CrudRepository<Ingredient,Long> {
}
