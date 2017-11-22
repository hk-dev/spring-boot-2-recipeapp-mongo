package com.github.recipeapp.converters;

import com.github.recipeapp.commands.IngredientCommand;
import com.github.recipeapp.models.Ingredient;
import com.github.recipeapp.models.UnitOfMeasure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientCommandToIngredient implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasure converter;

    @Autowired
    public IngredientCommandToIngredient(UnitOfMeasureCommandToUnitOfMeasure converter) {
        this.converter = converter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientCommand ingredientCommand) {
        if(ingredientCommand == null) {
            return null;
        }

        Ingredient ingredient = new Ingredient();
        ingredient.setId(ingredientCommand.getId());
        ingredient.setDescription(ingredientCommand.getDescription());
        ingredient.setAmount(ingredientCommand.getAmount());

        //convert uomcommand to uom
        UnitOfMeasure unitOfMeasure = converter.convert(ingredientCommand.getUnitOfMeasure());
        ingredient.setUnitOfMeasure(unitOfMeasure);
        return ingredient;
    }
}
