package com.saucedemo.seleniumatomation.definition;

import com.google.gson.Gson;
import com.saucedemo.seleniumatomation.page.ItemPageComponent;
import com.saucedemo.seleniumatomation.page.LoginPage;
import com.saucedemo.seleniumatomation.page.ShoppingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.val;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortProductsDefinition {
    @Autowired
    @Lazy
    LoginPage loginPage;

    @Autowired
    @Lazy
    ShoppingPage shoppingPage;

    @Value("${application.url}")
    private String url;
    @Lazy
    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    Gson gson;

   @Autowired
   @Lazy
   WebDriver driver;

    @Given("User is on {string} page")
    public void userIsOnPage(String pageName) {

                 driver.get(url);

    }

    @When("login with username {string}")
    public void loginWithUsername(String username) {
        loginPage.loginWith(username);
    }

    @And("sort by {string}")
    public void sortBy(String order) {
        shoppingPage.sortBy(order);
    }

    @Then("items by {string} in {string}ending order")
    public void itemsAreInInEndingOrder(String element, String order) {
        List<ItemPageComponent> items=shoppingPage.getItems();

        if(element.equalsIgnoreCase("name")){
            List<String> names=items.stream().map(item->{
                val name=  item.getName().getText();
                return name;
            }).collect(Collectors.toList());
            if(order.startsWith("asc")){
                List<String> expected= names.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                assertThat("sort by "+element+" "+order+"ending order not working properly",names,equalTo(expected));
            }
            else {
                List<String> expected= names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                assertThat("sort by "+element+" "+order+"ending order not working properly",names,equalTo(expected));
            }
        }
        if(element.startsWith("price")){
            List<Double> names=items.stream().map(item->{
                val name=  item.getPriceValue();
                return name;
            }).collect(Collectors.toList());

            if(order.startsWith("asc")){
                List<Double> expected= names.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());
                assertThat("sort by "+element+" "+order+"ending order not working properly",names,equalTo(expected));
            }
            else {
                List<Double> expected= names.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
                assertThat("sort by "+element+" "+order+"ending order not working properly",names,equalTo(expected));
            }
        }


    }



}
