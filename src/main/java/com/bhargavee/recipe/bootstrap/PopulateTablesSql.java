package com.bhargavee.recipe.bootstrap;

import com.bhargavee.recipe.model.*;
import com.bhargavee.recipe.repository.CategoryRepository;
import com.bhargavee.recipe.repository.IngredientRepository;
import com.bhargavee.recipe.repository.RecipeRepository;
import com.bhargavee.recipe.repository.UnitOfMeasureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

import java.util.EventListener;

@Component
@Profile("dev")
public class PopulateTablesSql implements ApplicationListener<ContextRefreshedEvent> {
    CategoryRepository catrepo;
    UnitOfMeasureRepository uomrepo;


    @Autowired
    public PopulateTablesSql(CategoryRepository catrepo, UnitOfMeasureRepository uomrepo){
        this.catrepo = catrepo;
        this.uomrepo = uomrepo;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        loadCategories();
        loadUom();

    }
    public void loadCategories(){
        Category c = new Category();
        c.setDescription( "American" );
        catrepo.save( c );

        Category c1 = new Category();
        c1.setDescription( "Mexican" );
        catrepo.save( c1 );

        Category c2 = new Category();
        c2.setDescription( "Indian" );
        catrepo.save( c2 );
    }
    public void loadUom(){
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setDescription( "ounce" );
        uomrepo.save(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setDescription( "cup" );
        uomrepo.save(uom2);


        UnitOfMeasure uom3 = new UnitOfMeasure();
        uom3.setDescription( "teaspoon" );
        uomrepo.save(uom3);

        UnitOfMeasure uom4 = new UnitOfMeasure();
        uom4.setDescription( "each" );
        uomrepo.save(uom4);

        UnitOfMeasure uom5 = new UnitOfMeasure();
        uom5.setDescription( "tablespoon" );
        uomrepo.save(uom5);

        UnitOfMeasure uom6 = new UnitOfMeasure();
        uom6.setDescription( "pinch" );
        uomrepo.save(uom6);
    }
}

