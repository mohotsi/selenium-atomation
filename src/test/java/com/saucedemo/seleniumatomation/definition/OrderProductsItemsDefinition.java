package com.saucedemo.seleniumatomation.definition;

import com.saucedemo.seleniumatomation.Model.Item;
import com.saucedemo.seleniumatomation.config.GlobalVariable;
import com.saucedemo.seleniumatomation.page.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrderProductsItemsDefinition {


    @Autowired
    @Lazy
    ShoppingPage shoppingPage;
    @Autowired
    @Lazy
    CartPage cartPage;
    @Autowired
    @Lazy
    CheckoutStepOnePage checkoutStepOnePage;
    @Autowired
    @Lazy
    CheckoutStepTwoPage checkoutStepTwoPage;

    @Value("${application.url}")
    private String url;




    @And("Add item {string} to cart")
    public void addItemToCart(String itemName) throws Exception {
        ItemPageComponent itemPageComponent=shoppingPage.getItems().stream().
                filter(x->x.getName().getText().equalsIgnoreCase(itemName))
                .findFirst().get();
        shoppingPage.clickRetry(itemPageComponent.getAddToCartButton());
        String url=itemPageComponent.getImage().getAttribute("src");
        System.out.println("their url"+url);
        val builer= Item.builder();

        val item=builer.description(itemPageComponent.getDescription().getText())
                .image(new Item().readImageFromUrl(url)).name(itemPageComponent.getName().getText())
                .price(itemPageComponent.getPriceValue()).build();
        GlobalVariable.addedToCart.put(itemName,item);
    }

    @And("Navigate to Cart page")
    public void navigateToCartPage() {
     shoppingPage.navigateToShoppingCart();

    }

    @Then("Verify that only items which were added to the cart appear")
    public void verifyThatOnlyItemsWhichWereAddedToTheCartAppear() {
        val itemsOnCartPage=cartPage.getItems().stream().map(itm->
                Item.builder().name(itm.getName().getText()).price(itm.getPriceValue())
                        .description(itm.getDescription().getText()).build()
                ).collect(Collectors.toList());
        /**
         * You need to exclude image , re map without the image. reason is because the image is not displayed in
         * the cart page
         */
     val itemsWhichWereAddedToTheCart=GlobalVariable.addedToCart.values().stream()
             .map(x->Item.builder().price(x.getPrice()).name(x.getName()).description(x.getDescription()).build()).collect(Collectors.toList());
     assertThat("the items on cart page are not equal to items which were added to cart",itemsOnCartPage,equalTo(itemsWhichWereAddedToTheCart));
     System.out.println("");
    }
    public static Item toItem(ItemPageComponent itemPageComponent) throws Exception {
        val builer= Item.builder();
        return builer.description(itemPageComponent.getDescription().getText())
                .image(new Item().readImageFromUrl(itemPageComponent.getImage().getAttribute("attribute")))
                .price(itemPageComponent.getPriceValue()).build();
    }

    @And("Click {string} button")
    public void clickButton(String buttonText) {
       if(buttonText.equalsIgnoreCase("checkout"))
           cartPage.clickCheckout();
       if(buttonText.equalsIgnoreCase("continue"))
           checkoutStepOnePage.clickContinueButton();
       if(buttonText.equalsIgnoreCase("finish"))
           checkoutStepTwoPage.clickFinishButton();
    }

    @And("fill checkout form with the following details")
    public void fillCheckoutFormWithTheFollowingDetails(DataTable dataTable) {
        val input=dataTable.asMaps().stream().findFirst().orElse(null);
       checkoutStepOnePage.fillInForm(input.get("FirstName"),input.get("LastName"),input.get("postalCode"));

    }
}
