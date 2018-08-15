package com.bhargavee.recipe.service;

import com.bhargavee.recipe.Exceptions.RecipeNotFound;
import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RecipeServiceImpl implements RecipeService {


    public RecipeRepository reciperepo;
    @Autowired
    public RecipeServiceImpl(RecipeRepository reciperepo){
        this.reciperepo = reciperepo;
    }
    @Override
    public Set<Recipe> getAllRecipes() {
            Set<Recipe> hashset = new HashSet<>();
            reciperepo.findAll().iterator().forEachRemaining( hashset::add);
            return hashset;
    }

    @Override
    public Recipe getRecipe(Long id) {

        Optional<Recipe> recipe = reciperepo.findById(id);
        if(!recipe.isPresent()){
            throw new RecipeNotFound("Recipe with id "+id+" donot exist");
        }
        return recipe.get();
    }

    @Transactional
    public Recipe addRecipe(Recipe recipe){
        Recipe savedrecipe = reciperepo.save(recipe);
        return savedrecipe;
    }

    @Transactional
    public void deleteRecipe(Long id){
        reciperepo.deleteById(id);
    }
}
