package com.github.recipeapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(exclude = {"notes", "ingredients", "categories"})
public class Recipe {

    private String id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte[] image;
    private Notes notes;
    private Set<Ingredient> ingredients = new HashSet<>();

    private Difficulty difficulty;
    private Set<Category> categories = new HashSet<>();

}
