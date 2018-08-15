package com.bhargavee.recipe.bootstrap;

import com.bhargavee.recipe.model.*;
import com.bhargavee.recipe.repository.CategoryRepository;
import com.bhargavee.recipe.repository.IngredientRepository;
import com.bhargavee.recipe.repository.RecipeRepository;
import com.bhargavee.recipe.repository.UnitOfMeasureRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import java.util.EventListener;

@Component
@Profile("default")
public class PopulateTables implements ApplicationListener<ContextRefreshedEvent> {
  CategoryRepository catrepo;
  RecipeRepository reciperepo;
  UnitOfMeasureRepository uomrepo;

   PopulateTables(CategoryRepository catrepo, RecipeRepository reciperepo, UnitOfMeasureRepository uomrepo){
       this.catrepo = catrepo;
       this.reciperepo = reciperepo;
       this.uomrepo = uomrepo;
   }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
           updatetables();

    }
    public void updatetables(){
        Recipe deviledeggs = new Recipe();

       //6 Eggs each
        Optional<UnitOfMeasure> uomeachoptional = uomrepo.findByDescription("each");
        if(!uomeachoptional.isPresent()){
            throw new RuntimeException( "UOM each Not fount" );
        }
        Ingredient egg = new Ingredient("Egg",new BigDecimal(6),uomeachoptional.get(),deviledeggs );

        // 1/4 cup Mayonnaise
        Optional<UnitOfMeasure> uomcupoptional = uomrepo.findByDescription("cup");
        if(!uomcupoptional.isPresent()){
            throw new RuntimeException( "UOM cup Not fount" );
        }
        Ingredient mayo = new Ingredient("Mayonnaise",new BigDecimal(0.25),uomcupoptional.get(),deviledeggs);
        //1/8 teaspoon of salt

        Optional<UnitOfMeasure> uomteaspoonoptional = uomrepo.findByDescription("teaspoon");
        if(!uomteaspoonoptional.isPresent()){
            throw new RuntimeException( "UOM teaspoon Not fount" );
        }
        Ingredient salt = new Ingredient("salt",new BigDecimal(0.125),uomteaspoonoptional.get(),deviledeggs);

        //pinch of pepper
        Optional<UnitOfMeasure> uompinchoptional = uomrepo.findByDescription("pinch");
        if(!uompinchoptional.isPresent()){
            throw new RuntimeException( "UOM pinch Not fount" );
        }
        Ingredient pepper = new Ingredient("pepper",new BigDecimal(1),uompinchoptional.get(),deviledeggs);


        Set<Ingredient> ingredients = new HashSet<>();
        ingredients.add(egg);
        ingredients.add(mayo);
        ingredients.add(salt);
        ingredients.add(pepper);

        //CategoryRepo  - American
        Optional<Category> american = catrepo.findByDescription("american");
        if(!american.isPresent())
        {
            throw new RuntimeException( "American Category not found");
        }

        //CatRepo - Mexican
        Optional<Category> mexican = catrepo.findByDescription("mexican");
        if(!mexican.isPresent())
        {
            throw new RuntimeException( "mexican Category not found");
        }
        Set<Category> categories = new HashSet<>(  );
        categories.add( american.get());
        categories.add(mexican.get());

        //Notes
        Notes note = new Notes();
        note.setNote( "Add paprika for garnish" );



        //Recipe
        deviledeggs.setName( "Deviled Eggs" );
        deviledeggs.setIngredients(ingredients);
        deviledeggs.setPrepTime( 20 );
        deviledeggs.setCookTime( 15 );
        deviledeggs.setServings( 4 );
        deviledeggs.setCategories( categories );
        deviledeggs.setDifficulty( Difficulty.EASY );
        deviledeggs.setDirections( "Place eggs in a single layer in a saucepan and cover with enough water that there's 1 1/2 inches of water above the eggs. Heat on high until water begins to boil, then cover, turn the heat to low, and cook for 1 minute. Remove from heat and leave covered for 14 minutes, then rinse under cold water continuously for 1 minute." );
        deviledeggs.setNotes( note );
        reciperepo.save( deviledeggs );





    }
}
