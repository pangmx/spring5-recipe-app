package guru.springframework.controllers;

import guru.springframework.domain.Recipe;
import guru.springframework.services.RecipeService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

public class IndexControllerTest {

    IndexController indexController;

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        indexController = new IndexController(recipeService);
    }

    @Test
    public void getIndexPage() {
        String expectedResult = "index";

        Set<Recipe> resultSet = new HashSet<>();
        resultSet.add(new Recipe());
        Recipe recipe2 = new Recipe();
        recipe2.setId(2L);
        resultSet.add(recipe2);

        when(recipeService.getRecipes()).thenReturn(resultSet);

        // setting up the argument captor to capture argument passed into model.setAttribute(...)
        ArgumentCaptor<Set<Recipe>> argCaptor = ArgumentCaptor.forClass(Set.class) ;

        String result = indexController.getIndexPage(model);

        // check that result string is as expected
        assertEquals(result,expectedResult);

        // verify that recipeService.getRecipes() method has been invoked only once
        verify(recipeService,times(1)).getRecipes();

        // verify that model.addAttribute(...) has been invoked only once
        verify(model,times(1)).addAttribute(eq("recipes"),
                argCaptor.capture());

        // verify that a Set of size 2 is passed to the model.getAttribute(...)
        assertEquals(argCaptor.getValue().size(), 2);
    }
}