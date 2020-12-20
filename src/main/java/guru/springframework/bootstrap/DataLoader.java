package guru.springframework.bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repository.CategoryRepository;
import guru.springframework.repository.RecipeRepository;
import guru.springframework.repository.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {


    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        log.debug("Data Loader running...");
        Optional<Category> italian = categoryRepository.findByDescription("Italian");

        Optional<UnitOfMeasure> pound = unitOfMeasureRepository.findByDescription("Pound");
        Optional<UnitOfMeasure> cup = unitOfMeasureRepository.findByDescription("Cup");
        Optional<UnitOfMeasure> ounce = unitOfMeasureRepository.findByDescription("Ounce");
        Optional<UnitOfMeasure> teaspoon = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional<UnitOfMeasure> clove = unitOfMeasureRepository.findByDescription("Clove");
        Optional<UnitOfMeasure> tableSpoon = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional<UnitOfMeasure> chopped = unitOfMeasureRepository.findByDescription("Chopped");

        // RECIPE: Spicy Sausage Pizza
        log.debug("Creating Spicy Sausage Pizza Recipe...");
        Recipe spicySausagePizza = new Recipe();
        spicySausagePizza.setDescription("Spicy Sausage Pizza");
        spicySausagePizza.setPrepTime(10);
        spicySausagePizza.setCookTime(4);
        spicySausagePizza.setServings(8);
        spicySausagePizza.setSource("Simply Recipes");
        spicySausagePizza.setUrl("https://www.simplyrecipes.com/recipes/spicy_sausage_pizza/");
        spicySausagePizza.setDirections("");
        spicySausagePizza.setDifficulty(Difficulty.MODERATE);

        Ingredient pizzaDough = new Ingredient();
        pizzaDough.setDescription("Pizza Dough");
        pizzaDough.setAmount(new BigDecimal(2));
        pizzaDough.setUom(pound.get());
        spicySausagePizza.addIngredient(pizzaDough);

        Ingredient marinaraSauce = new Ingredient();
        marinaraSauce.setDescription("Marinara Sauce");
        marinaraSauce.setAmount(new BigDecimal(1));
        marinaraSauce.setUom(cup.get());
        spicySausagePizza.addIngredient(marinaraSauce);

        Ingredient mozzarella = new Ingredient();
        mozzarella.setDescription("Mozzarella Cheese");
        mozzarella.setAmount(new BigDecimal(1));
        mozzarella.setUom(pound.get());
        spicySausagePizza.addIngredient(mozzarella);

        Ingredient spicyItalianSausage = new Ingredient();
        spicyItalianSausage.setDescription("Spicy Italian Sausage");
        spicyItalianSausage.setAmount(new BigDecimal(8));
        spicyItalianSausage.setUom(ounce.get());
        spicySausagePizza.addIngredient(spicyItalianSausage);

        Ingredient redPepperFlake = new Ingredient();
        redPepperFlake.setDescription("Red Pepper Flakes");
        redPepperFlake.setAmount(new BigDecimal(2));
        redPepperFlake.setUom(teaspoon.get());
        spicySausagePizza.addIngredient(redPepperFlake);

        spicySausagePizza.setCategories(Collections.singleton(italian.get()));
        log.debug("Saving Recipe...");
        recipeRepository.save(spicySausagePizza);

        // RECIPE: Instant Pot Mashed Potatoes
        log.debug("Creating Instant Pot Mashed Potatoes Recipe...");

        Recipe instantPotMashedPotatoes = new Recipe();
        instantPotMashedPotatoes.setDescription("Instant Pot Mashed Potatoes");
        instantPotMashedPotatoes.setPrepTime(10);
        instantPotMashedPotatoes.setCookTime(20);
        instantPotMashedPotatoes.setServings(7);
        instantPotMashedPotatoes.setSource("Simply Recipes");
        instantPotMashedPotatoes.setUrl("https://www.simplyrecipes.com/recipes/pressure_cooker_garlic_mashed_potatoes/");
        instantPotMashedPotatoes.setDirections("");
        instantPotMashedPotatoes.setDifficulty(Difficulty.EASY);


        Set<Ingredient> instantPotMashePotatoesIngredients = new HashSet<>();

        Ingredient cupOfWater = new Ingredient();
        cupOfWater.setDescription("Cup of Water");
        cupOfWater.setAmount(new BigDecimal(1));
        cupOfWater.setUom(cup.get());
        cupOfWater.setRecipe(instantPotMashedPotatoes);
        instantPotMashePotatoesIngredients.add(cupOfWater);

        Ingredient russetPotatoes = new Ingredient();
        russetPotatoes.setDescription("Russed Potatoes, peeled and sliced 10inch thick");
        russetPotatoes.setAmount(new BigDecimal(3.5));
        russetPotatoes.setUom(pound.get());
        russetPotatoes.setRecipe(instantPotMashedPotatoes);
        instantPotMashePotatoesIngredients.add(russetPotatoes);

        Ingredient garlic = new Ingredient();
        garlic.setDescription("Garlic, peeled (optional)");
        garlic.setAmount(new BigDecimal(4));
        garlic.setUom(clove.get());
        garlic.setRecipe(instantPotMashedPotatoes);
        instantPotMashePotatoesIngredients.add(garlic);

        Ingredient milk = new Ingredient();
        milk.setDescription("Whole MIlk");
        milk.setAmount(new BigDecimal(0.75));
        milk.setUom(cup.get());
        milk.setRecipe(instantPotMashedPotatoes);
        instantPotMashePotatoesIngredients.add(milk);

        Ingredient unsaltedButter = new Ingredient();
        unsaltedButter.setDescription("Unsalted Butter");
        unsaltedButter.setAmount(new BigDecimal(3));
        unsaltedButter.setUom(tableSpoon.get());
        unsaltedButter.setRecipe(instantPotMashedPotatoes);
        instantPotMashePotatoesIngredients.add(unsaltedButter);

        Ingredient kosherSalt = new Ingredient();
        kosherSalt.setDescription("Kosher Salt");
        kosherSalt.setAmount(new BigDecimal(1.5));
        kosherSalt.setUom(teaspoon.get());
        kosherSalt.setRecipe(instantPotMashedPotatoes);
        instantPotMashePotatoesIngredients.add(kosherSalt);

        Ingredient blackPepper = new Ingredient();
        blackPepper.setDescription("Frshly ground black pepper");
        blackPepper.setAmount(new BigDecimal(0.5));
        blackPepper.setUom(teaspoon.get());
        blackPepper.setRecipe(instantPotMashedPotatoes);
        instantPotMashePotatoesIngredients.add(blackPepper);

        Ingredient chivesOrParsely = new Ingredient();
        chivesOrParsely.setDescription("Freash chives of Parsley, for garnish (Optional)");
        chivesOrParsely.setAmount(new BigDecimal(1));
        chivesOrParsely.setUom(chopped.get());
        chivesOrParsely.setRecipe(instantPotMashedPotatoes);
        instantPotMashePotatoesIngredients.add(chivesOrParsely);

        instantPotMashedPotatoes.setIngredients(instantPotMashePotatoesIngredients);

        log.debug("Saving Recipe...");
        recipeRepository.save(instantPotMashedPotatoes);

    }
}
