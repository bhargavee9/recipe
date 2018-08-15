package com.bhargavee.recipe.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int prepTime;
    private int cookTime;
    private int servings;
    private String source;
    private String url;
    @Lob
    private String directions;
    @Lob
    private Byte[] image;
    private Difficulty difficulty;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    private Set<Ingredient> ingredients;

    @ManyToMany(fetch=FetchType.EAGER)
    private Set<Category> categories;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    public Notes getNotes() {
        return notes;
    }

    public void setNotes(Notes notes) {
            this.notes = notes;
            this.notes.setRecipe( this );

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {

        this.categories = categories;
    }

    public int getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(int prepTime) {
        this.prepTime = prepTime;
    }

    public int getCookTime() {
        return cookTime;
    }

    public void setCookTime(int cookTime) {
        this.cookTime = cookTime;
    }

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDirections() {
        return directions;
    }

    public void setDirections(String directions) {
        this.directions = directions;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(Set<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Recipe addIngredient(Ingredient ingredient){
        this.ingredients.add( ingredient );
        ingredient.setRecipe( this );
        return this;
    }
}
