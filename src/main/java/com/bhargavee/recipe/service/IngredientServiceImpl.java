package com.bhargavee.recipe.service;

import com.bhargavee.recipe.model.Ingredient;
import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.repository.IngredientRepository;
import com.bhargavee.recipe.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class IngredientServiceImpl implements IngredientService {
    IngredientRepository irepo;
    RecipeRepository reciperepo;

    @Autowired
    public IngredientServiceImpl(IngredientRepository irepo,RecipeRepository reciperepo)
    {
        this.irepo = irepo;
        this.reciperepo = reciperepo;
    }

    @Override
    public Ingredient getIngredientById(Long id) {
        Ingredient ig = irepo.findById( id ).get();
        return ig;
    }

    @Transactional
    public void updateIngredient(Long recipe_id,Ingredient ingredient){

         if(ingredient.getId()==null){
             Recipe recipe = reciperepo.findById( recipe_id ).get();
             Set<Ingredient> ingredients = recipe.getIngredients();
             ingredients.add( ingredient );
             recipe.setIngredients( ingredients );
             ingredient.setRecipe( recipe ); //***********************************************
             //irepo.save(ingredient);
             return;
         }
         Ingredient ig = getIngredientById(ingredient.getId());
         ig.setDescription( ingredient.getDescription() );
        ig.setAmount( ingredient.getAmount() );
        ig.setUom( ingredient.getUom() );

    }

    @Override
    @Transactional
    public void deleteIngredient(Long recipe_id,Long ing_id) {
           Recipe recipe = reciperepo.findById( recipe_id ).get();
           Ingredient ig = irepo.findById( ing_id ).get();
           recipe.getIngredients().remove(ig);
           irepo.deleteById( ing_id );
           return;

    }


}

