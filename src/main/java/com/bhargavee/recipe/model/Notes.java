package com.bhargavee.recipe.model;

import javax.persistence.*;

@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String note;
    @OneToOne
    private Recipe recipe;

    public String getNote() {
        return note;
    }

    public void setNote(String notes) {
        this.note = notes;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
