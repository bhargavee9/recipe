package com.bhargavee.recipe.controllers;

import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.service.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {

    private RecipeService recipeservice;
    @Autowired
   public IndexController(RecipeService recipeservice){
        this.recipeservice = recipeservice;
    }


    @RequestMapping(path={"/","index"})
    public String home(Model model){
        model.addAttribute("recipes",recipeservice.getAllRecipes() );
        return "index";
    }


}
