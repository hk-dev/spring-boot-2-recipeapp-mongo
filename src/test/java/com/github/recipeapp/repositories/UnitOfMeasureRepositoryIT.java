package com.github.recipeapp.repositories;

import com.github.recipeapp.bootstrap.RecipeBootstrap;
import com.github.recipeapp.models.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class UnitOfMeasureRepositoryIT {

    @Autowired
    private UnitOfMeasureRepository unitOfMeasureRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RecipeRepository recipeRepository;

    @Before
    public void setUp() throws Exception {
        categoryRepository.deleteAll();
        recipeRepository.deleteAll();
        unitOfMeasureRepository.deleteAll();

        RecipeBootstrap bootstrap = new RecipeBootstrap(categoryRepository, recipeRepository, unitOfMeasureRepository);
        bootstrap.onApplicationEvent(null);
    }

    @Test
    public void findByDescription() throws Exception {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Teaspoon");
        assertEquals("Teaspoon", uom.get().getDescription());
    }

    @Test
    public void findByDescriptionCup() throws Exception {
        Optional<UnitOfMeasure> uom = unitOfMeasureRepository.findByDescription("Cup");
        assertEquals("Cup", uom.get().getDescription());
    }

}