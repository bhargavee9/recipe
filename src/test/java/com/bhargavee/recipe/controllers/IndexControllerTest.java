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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.mockito.ArgumentCaptor;

public class IndexControllerTest {

    @Mock
    RecipeService recipeservice;
    @Mock
    Model model;

    IndexController controller;


    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        controller = new IndexController(recipeservice);
    }

    @Test
    public void testMockMVC() throws Exception {
        MockMvc mockmvc = MockMvcBuilders.standaloneSetup(controller).build();
        mockmvc.perform( get("/") )
                .andExpect( status().isOk() ).andExpect(view().name("index") );

    }

    @Test
    public void home() {

        Recipe r1 = new Recipe();
        Recipe r2 = new Recipe();
        Set<Recipe> recipes = new HashSet<>();
        recipes.add(r1);
        recipes.add(r2);

        ArgumentCaptor<Set<Recipe>> captor = ArgumentCaptor.forClass(Set.class);
        when(recipeservice.getAllRecipes()).thenReturn(recipes);
        String actual = controller.home(model);

        verify(recipeservice,times(1)).getAllRecipes();
        verify(model, times(1)).addAttribute(eq("recipes"), captor.capture());
        assertEquals("index",actual );
        Set<Recipe> setInController = captor.getValue();
        assertEquals(2, setInController.size());

    }
}