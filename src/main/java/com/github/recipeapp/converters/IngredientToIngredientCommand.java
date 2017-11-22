package com.github.recipeapp.converters;

import com.github.recipeapp.commands.IngredientCommand;
import com.github.recipeapp.commands.UnitOfMeasureCommand;
import com.github.recipeapp.models.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientToIngredientCommand implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommand converter;

    @Autowired
    public IngredientToIngredientCommand(UnitOfMeasureToUnitOfMeasureCommand converter) {
        this.converter = converter;
    }

    @Nullable
    @Override
    public IngredientCommand convert(Ingredient ingredient) {
        if(ingredient == null) {
            return null;
        }

        IngredientCommand command = new IngredientCommand();
        command.setId(ingredient.getId());
        command.setDescription(ingredient.getDescription());
        command.setAmount(ingredient.getAmount());

        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(ingredient.getUnitOfMeasure());
        command.setUnitOfMeasure(unitOfMeasureCommand);
        return command;
    }
}
