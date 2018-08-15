package com.bhargavee.recipe.Exceptions;

public class RecipeNotFound extends RuntimeException {
    public RecipeNotFound(){
        super();
    }
    public RecipeNotFound(String message){
        super(message);
    }

    public RecipeNotFound(String message,Throwable obj){
        super(message,obj);
    }

}
