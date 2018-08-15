package com.bhargavee.recipe.controllers;

import com.bhargavee.recipe.model.Ingredient;
import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.model.UnitOfMeasure;
import com.bhargavee.recipe.service.IngredientService;
import com.bhargavee.recipe.service.RecipeService;
import com.bhargavee.recipe.service.UomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class IngredientController {

    RecipeService recipeservice;
    UomService uomservice;
    IngredientService igservice;
    @Autowired
    public IngredientController(RecipeService recipeservice,UomService uomservice,IngredientService igservice) {
        this.recipeservice = recipeservice;
        this.uomservice = uomservice;
        this.igservice = igservice;
    }

    @GetMapping("recipe/{recipe_id}/ingredients/{ingredient_id}/update")
    public String FormToModifyIngredient(@PathVariable Long recipe_id, @PathVariable Long ingredient_id, Model model){
          Ingredient ingredient = igservice.getIngredientById(ingredient_id);
          Set<UnitOfMeasure> uoms = uomservice.getAllUoms();
          model.addAttribute( "ingredient",ingredient );
          model.addAttribute("uoms",uoms);
          return "ingredients/update";
    }

    @GetMapping("recipe/{recipe_id}/ingredients/{ingredient_id}/delete")
    public String deleteIngredient(@PathVariable Long recipe_id, @PathVariable Long ingredient_id, Model model){
        igservice.deleteIngredient( recipe_id,ingredient_id );
        return "redirect:/recipe/"+recipe_id+"/ingredients";
    }

    @GetMapping(value="/recipe/{id}/ingredients")
    public String getAllIngredients(@PathVariable Long id , Model model){
        Recipe recipe = recipeservice.getRecipe( id );
        Set<Ingredient> ingredients = recipe.getIngredients();
        model.addAttribute( "recipe",recipe );
        model.addAttribute( "ingredients",ingredients );
        return "ingredients/show";

    }
     @GetMapping("/recipe/{recipe_id}/ingredients/new")
     public String newIngredientForm(@PathVariable Long recipe_id, Model model){
        Ingredient ingredient = new Ingredient();
         Set<UnitOfMeasure> uoms = uomservice.getAllUoms();
         model.addAttribute( "recipe_id",recipe_id );
         model.addAttribute("uoms",uoms);
         model.addAttribute( "ingredient",ingredient);
         return "ingredients/update";
     }

    @PostMapping("/recipe/{recipe_id}/ingredients/")
    public String updateIngredient(@ModelAttribute Ingredient ingredient,@PathVariable Long recipe_id ) {
        igservice.updateIngredient(recipe_id,ingredient);
        return "redirect:/recipe/"+recipe_id+"/ingredients";
    }
}
