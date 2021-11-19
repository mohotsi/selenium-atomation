package com.saucedemo.seleniumatomation.definition;

import com.saucedemo.seleniumatomation.Model.Item;
import com.saucedemo.seleniumatomation.page.*;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import lombok.val;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.everyItem;

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
    @Autowired
    @Lazy
    CompletePage completePage;
    @Autowired
    @Lazy
    InventoryItemPage inventoryItemPage;
    public  HashMap<String, Item> addedToCart =new HashMap<String,Item>();
    public  Item selectedItem;
    @Value("${application.url}")
    private String url;
    @Autowired
    @Lazy
    WebDriver driver;

    public static Item toItem(ItemPageComponent itemPageComponent) throws Exception {
        val builer = Item.builder();
        return builer.description(itemPageComponent.getDescription().getText())
                .image(new Item().readImageFromUrl(itemPageComponent.getImage().getAttribute("attribute")))
                .price(itemPageComponent.getPriceValue()).build();
    }


    @And("Add item\\(s) to cart")
    public void addItemToCart(DataTable itemNames) {

        shoppingPage.getItems().stream().
                filter(x ->
                        itemNames.asMaps().stream().map(y->y.get("itemName")).collect(Collectors.toList()).contains( x.getName().getText())
                       ).forEach(itemCom -> {
                    shoppingPage.clickRetry(itemCom.button());
                    String url = itemCom.getImage().getAttribute("src");
                    System.out.println("their url" + url);
                    val builer = Item.builder();

                    try {
                        val item = builer.description(itemCom.getDescription().getText())
                                .image(new Item().readImageFromUrl(url)).name(itemCom.getName().getText())
                                .price(itemCom.getPriceValue()).build();
                        addedToCart.put(itemCom.getName().getText(), item);
                    } catch (Exception e) {

                    }


                }
        );

    }

    @And("Navigate to Cart page")
    public void navigateToCartPage() {
        shoppingPage.navigateToShoppingCart();

    }

    @Then("Verify that only items which were added to the cart appear")
    public void verifyThatOnlyItemsWhichWereAddedToTheCartAppear() {
        val itemsOnCartPage = cartPage.getItems().stream().map(itm ->
                Item.builder().name(itm.getName().getText()).price(itm.getPriceValue())
                        .description(itm.getDescription().getText()).build()
        ).sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
        /**
         * You need to exclude image , re map without the image. reason is because the image is not displayed in
         * the cart page
         */
        val itemsWhichWereAddedToTheCart =addedToCart.values().stream()
                .map(x -> Item.builder().price(x.getPrice()).name(x.getName()).description(x.getDescription())
                        .build()).sorted(Comparator.comparing(Item::getName)).collect(Collectors.toList());
        System.out.println();
        assertThat("the items on cart page are not equal to items which were added to cart", itemsOnCartPage, equalTo(itemsWhichWereAddedToTheCart));

    }

    @And("Click {string} button")
    public void clickButton(String buttonText) {
        if (buttonText.equalsIgnoreCase("checkout"))
            cartPage.clickCheckout();
        if (buttonText.equalsIgnoreCase("continue"))
            checkoutStepOnePage.clickContinueButton();
        if (buttonText.equalsIgnoreCase("finish"))
            checkoutStepTwoPage.clickFinishButton();
    }

    @And("fill checkout form with the following details")
    public void fillCheckoutFormWithTheFollowingDetails(DataTable dataTable) {
        val input = dataTable.asMaps().stream().findFirst().orElse(null);
        checkoutStepOnePage.fillInForm(input.get("FirstName"), input.get("LastName"), input.get("postalCode"));

    }

    @Then("Verify the total Amount charge is correct")
    public void verifyTheTotalAmountChargeIsCorrect() {
        val prices =addedToCart.values().stream();
        val expectedItemTotal = prices.mapToDouble(item -> item.getPrice()).sum();
        val actualItemTotal = checkoutStepTwoPage.getItemTotal();
        val tax = checkoutStepTwoPage.getTax().getValue();
        val total = checkoutStepTwoPage.getTotal().getValue();

        assertThat("Item total is calculated wrong", checkoutStepTwoPage.getItemTotal().getValue(), equalTo(expectedItemTotal));
        assertThat("total is calculated wrong", total, equalTo(Math.round((tax + expectedItemTotal) * 100.0) / 100.0));

    }

    @Then("Confirmation message should displayed with")
    public void confirmationMessageShouldDisplayedWith(DataTable dataTable) {
        val data = dataTable.asMaps().stream().findFirst().get();
        String[] expected = {data.get("complete"), data.get("title"), data.get("header")};
        String[] actual = {completePage.getCompleteText(), completePage.getTitle(), completePage.getHeaderText()};
        assertThat(actual,
                equalTo(expected));

    }

    @And("click item Name {string}")
    public void clickItemName(String name) {
        val itemCom=shoppingPage.getItems().stream()
                .filter(x->shoppingPage.waitPageToDisplay(x.getName()).getText().equalsIgnoreCase(name)).collect(Collectors.toList())
        .stream().findFirst().get();

        String url = itemCom.getImage().getAttribute("src");
        System.out.println("their url" + url);
        val builer = Item.builder();

        try {
            val item = builer.description(itemCom.getDescription().getText())
                    .image(new Item().readImageFromUrl(url)).name(itemCom.getName().getText())
                    .price(itemCom.getPriceValue()).build();
          selectedItem=item;
        } catch (Exception e) {

        }
        shoppingPage.clickRetry(itemCom.getName());


    }

    @Then("Data on inventory item page is the same as item that was clicked")
    public void dataOnInventoryItemPageIsTheSameAsItemThatWasClicked() throws Exception {
        val iventoryItemData=Item.builder().name(inventoryItemPage.getName())
                .price(inventoryItemPage.getPrice()).description(inventoryItemPage.getDescription())
                .image(inventoryItemPage.getImage()).build();
        assertThat(iventoryItemData,equalTo(selectedItem));

    }

    @And("click addCart button on inventoryItem Page")
    public void clickAddCartButtonOnInventoryItemPage() throws Exception {
        inventoryItemPage.clickAddToCart();
        val iventoryItemData=Item.builder().name(inventoryItemPage.getName())
                .price(inventoryItemPage.getPrice()).description(inventoryItemPage.getDescription())
                .image(inventoryItemPage.getImage()).build();
       addedToCart.put(iventoryItemData.getName(),iventoryItemData);

    }
    @And("shopping cart badge increments by number of items added to cart")
    public void shoppingCartBadgeIncrementsByNumberOfItemsAddedToCart() {
        assertThat("shopping cart badge doesn't increments by number of items added to cart",Integer.parseInt(shoppingPage.getShopping_cart_badge().getText()),equalTo(addedToCart.size()));
        ;
    }

    @And("Remove Item from Cart")
    public void removeItemFromCart(DataTable itemNames) throws InterruptedException {
        val myItems=( shoppingPage.getItems().isEmpty())?cartPage.getItems() : shoppingPage.getItems();

        myItems.stream().
                filter(x ->
                        itemNames.asMaps().stream().map(y->y.get("itemName")).collect(Collectors.toList()).contains( x.getName().getText())
                ).forEach(itemCom -> {

            shoppingPage.clickRetry(itemCom.button());
            itemNames.asMaps().stream().map(y->y.get("itemName")).collect(Collectors.toList()).forEach(x->
                    addedToCart.remove(x)    );





                }
        );

    }
    @Then("validate product items {string}")
    public void validateProductItems(String arg0) {
        val itemsCom=( shoppingPage.getItems().isEmpty())?cartPage.getItems() : shoppingPage.getItems();

       val items=  itemsCom.stream().map(i->{
                     String url = i.getImage().getAttribute("src");
                     System.out.println("their url" + url);
                     val builer = Item.builder();

                     try {
                         val item = builer.description(i.getDescription().getText())
                                 .image(new Item().readImageFromUrl(url)).name(i.getName().getText())
                                 .price(i.getPriceValue()).build();
                         return item;
                     } catch (Exception e) {
                      return  new Item();
                     }
                 }


                 );
           if(arg0.equalsIgnoreCase("images are distinct")){
               val images=items.map(x->x.getImage()).collect(Collectors.toList());
               val expected=images.stream().distinct().collect(Collectors.toList());
               assertThat(images,equalTo(expected));
               System.out.println("hallelujah");
           }


    }


}
