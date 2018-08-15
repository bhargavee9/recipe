package com.bhargavee.recipe.controllers;

import com.bhargavee.recipe.model.Ingredient;
import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.model.UnitOfMeasure;
import com.bhargavee.recipe.service.IngredientService;
import com.bhargavee.recipe.service.RecipeService;
import com.bhargavee.recipe.service.UomService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

public class IngredientControllerTest {

     IngredientController controller;
     @Mock
     RecipeService recipeservice;
     @Mock
     UomService uomservice;
     @Mock
     IngredientService igservice;
     @Mock
     Model model;
     MockMvc mockmvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks( this );

        controller = new IngredientController(recipeservice,uomservice,igservice );
        mockmvc = MockMvcBuilders.standaloneSetup( controller ).build();
    }

    @Test
    public void formToModifyIngredient() throws Exception {
        Ingredient ingredient = new Ingredient(  );
        Set<UnitOfMeasure> uoms = new HashSet<>( );

        when(igservice.getIngredientById(anyLong())).thenReturn( ingredient );
        when(uomservice.getAllUoms()).thenReturn( uoms );
        mockmvc.perform( get("/recipe/1/ingredients/1/update"))
                .andExpect( view().name("ingredients/update") )
                 .andExpect(model().attributeExists( "ingredient" ) )
                 .andExpect( model().attributeExists( "uoms" ) );
    }

    @Test
    public void deleteIngredient() throws Exception {
        mockmvc.perform(get("/recipe/{recipe_id}/ingredients/{ingredient_id}/delete",2,3)
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/ingredients"));

        verify(igservice, times(1)).deleteIngredient(anyLong(), anyLong());

    }


    @Test
    public void getAllIngredients() throws Exception{
        Recipe recipe = new Recipe();
        Set<Ingredient> ingredients = new HashSet<>(  );
        recipe.setIngredients( ingredients );
      when(recipeservice.getRecipe( anyLong() )).thenReturn(recipe);
      Long id = new Long(1);
      mockmvc.perform(get("/recipe/{id}/ingredients",id))
              .andExpect( view().name("ingredients/show" ))
              .andExpect( model().attributeExists("recipe" ))
              .andExpect( model().attributeExists( "ingredients" ));


    }


    @Test
    public void newIngredientForm() throws Exception {
        Ingredient ingredient = new Ingredient();
        Set<UnitOfMeasure> uoms = new HashSet<>(  );
        when(uomservice.getAllUoms()).thenReturn( uoms );
        mockmvc.perform(get("/recipe/{recipe_id}/ingredients/new",1L))
                .andExpect(model().attributeExists( "recipe_id" ))
                .andExpect( model().attributeExists( "uoms" ) )
                .andExpect( model().attributeExists( "ingredient" ))
                .andExpect( view().name("ingredients/update") );
    }

    @Test
    public void updateIngredient() throws Exception{
        Ingredient ingredient = new Ingredient( );
        mockmvc.perform( post("/recipe/{recipe_id}/ingredients/",1))
                .andExpect(view().name("redirect:/recipe/1/ingredients"));
    }
}