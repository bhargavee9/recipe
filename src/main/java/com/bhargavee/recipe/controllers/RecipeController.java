package com.bhargavee.recipe.controllers;

import com.bhargavee.recipe.Exceptions.RecipeNotFound;
import com.bhargavee.recipe.model.Ingredient;
import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

@Controller
public class RecipeController {


    RecipeService recipeservice;

    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.recipeservice = recipeService;

    }

    @RequestMapping(value = "/recipe/{id}/show", method = RequestMethod.GET)
    public String getRecipe(@PathVariable Long id, Model model){
        Recipe recipe = recipeservice.getRecipe(id);
        model.addAttribute( "recipe",recipe );
        return "recipes/show";
    }

    @RequestMapping(value = "/recipe/{id}/update", method = RequestMethod.GET)
    public String updateRecipe(@PathVariable Long id, Model model){
        Recipe recipe = recipeservice.getRecipe(id);
        model.addAttribute( "recipe",recipe );
        return "recipes/new";
    }
    @RequestMapping(value = "/recipe/new", method = RequestMethod.GET)
    public String recipeNew(Model model)
    {
        model.addAttribute( "recipe",new Recipe() );
        return "recipes/new";
    }

    @RequestMapping(value = "/recipe/", method = RequestMethod.POST)
    public String recipeNew(@ModelAttribute Recipe recipe, Model model)
    {
        Recipe savedrecipe =  recipeservice.addRecipe(recipe);

        return "redirect:"+savedrecipe.getId()+"/show";
    }

    @RequestMapping(value="/recipe/{id}/delete",method=RequestMethod.GET)
    public String deleteRecipe(@PathVariable Long id, Model model)
    {
        recipeservice.deleteRecipe(id);
        return "redirect:/";
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(RecipeNotFound.class)
    public ModelAndView recipeNotFound(Exception e){
        ModelAndView modelandview = new ModelAndView(  );
        modelandview.setViewName( "404Error" );
        modelandview.addObject( "exception",e );
        return modelandview;
    }
}
