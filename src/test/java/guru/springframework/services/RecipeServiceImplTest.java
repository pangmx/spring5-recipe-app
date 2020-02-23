package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.MockitoFramework;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Set;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getRecipes() {
        HashSet<Recipe> result = new HashSet<>();
        result.add(new Recipe());

        when(recipeRepository.findAll()).thenReturn(result);
        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(),1);
    }
}
