package com.bhargavee.recipe.repository;

import com.bhargavee.recipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface RecipeRepository extends CrudRepository<Recipe,Long> {

}
