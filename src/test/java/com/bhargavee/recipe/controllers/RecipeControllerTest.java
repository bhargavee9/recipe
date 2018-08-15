package com.bhargavee.recipe.controllers;

import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.repository.RecipeRepository;
import com.bhargavee.recipe.service.RecipeService;
import com.bhargavee.recipe.service.RecipeServiceImpl;
import org.h2.index.Index;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import org.omg.CORBA.ObjectHelper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.mockito.ArgumentCaptor;


public class RecipeControllerTest {

    RecipeController controller;
    @Mock
    Model model;
    @Mock
    RecipeService recipeservice;
    MockMvc mockmvc;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        controller = new RecipeController(recipeservice);
        mockmvc = MockMvcBuilders.standaloneSetup(controller).build();

    }


    @Test
    public void getRecipe() throws Exception {
        Recipe newrecipe = new Recipe();
        newrecipe.setId(1L);
        Long id = new Long(1);
        when(recipeservice.getRecipe(1L)).thenReturn(newrecipe);
        mockmvc.perform(get("/recipe/1/show") )
                .andExpect(status().isOk())
                .andExpect(view().name( "recipes/show" ))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void updateRecipe() throws Exception {
        Long id = new Long(1);
        Recipe r = new Recipe();
        r.setId( id );
     when(recipeservice.getRecipe( id )).thenReturn( r );
     mockmvc.perform(get("/recipe/1/update"))
             .andExpect(status().is2xxSuccessful())
             .andExpect(view().name( "recipes/new" ))
             .andExpect( model().attributeExists( "recipe" ));
    }

    @Test
    public void recipeNew() throws Exception{
        mockmvc.perform(get("/recipe/new"))
                .andExpect( status().is2xxSuccessful() )
                .andExpect(view().name("recipes/new"))
                .andExpect(model().attributeExists( "recipe" ));
    }

    @Test
    public void recipeNew1() throws Exception {
        Recipe r = new Recipe();
        Long id = 2L;
        r.setId(id);
       // r.setDirections( "awwds" );
       // r.setName( "Hello Bujji" );
        when(recipeservice.addRecipe(any())).thenReturn(r);
        mockmvc.perform(post("/recipe/"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:2/show"));
    }

    @Test
    public void deleteRecipe() throws Exception{

        mockmvc.perform(get("/recipe/1/delete"))
                .andExpect( status().is3xxRedirection() )
                .andExpect(view().name("redirect:/"));

        verify(recipeservice,times(1)).deleteRecipe( anyLong() );
    }
}