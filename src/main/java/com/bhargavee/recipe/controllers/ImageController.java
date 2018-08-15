package com.bhargavee.recipe.controllers;

import com.bhargavee.recipe.model.Recipe;
import com.bhargavee.recipe.service.RecipeService;
import com.bhargavee.recipe.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImageController {
    private  ImageService imageservice;
    private  RecipeService recipeservice;

    public ImageController(ImageService imageservice, RecipeService recipeservice) {
        this.imageservice = imageservice;
        this.recipeservice = recipeservice;
    }
    @GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable Long id, Model model){
           Recipe recipe = recipeservice.getRecipe( id );
           model.addAttribute( "recipe",recipe );
           return "recipes/imageUploadForm";
    }

    @PostMapping("recipe/{id}/image")

    public String handleImagePost(@PathVariable Long id, @RequestParam("imagefile") MultipartFile file){

        imageservice.saveImageFile(Long.valueOf( id ), file);

        return "redirect:/recipe/" + id + "/show";
    }

    @GetMapping("recipe/{id}/recipeimage")
    public void renderImageFromDB(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Recipe recipe = recipeservice.getRecipe( id );

        if (recipe.getImage() != null) {
            byte[] byteArray = new byte[recipe.getImage().length];
            int i = 0;

            for (Byte wrappedByte : recipe.getImage()){
                byteArray[i++] = wrappedByte; //auto unboxing
            }

            response.setContentType("image/jpeg");
            InputStream is = new ByteArrayInputStream(byteArray);
            IOUtils.copy(is, response.getOutputStream());
        }
    }
}
