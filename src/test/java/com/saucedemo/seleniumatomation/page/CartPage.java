package com.saucedemo.seleniumatomation.page;

import com.saucedemo.seleniumatomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class CartPage extends SharedComponents {


    @FindBy(id = "checkout")
    WebElement checkout;
    @FindBy(id = "continue-shopping")
    WebElement continueShopping;
    @FindBy(className = "cart_quantity_label")
    WebElement cart_quantity_label;
    @FindBy(className = "cart_desc_label")
    WebElement description;

    public String getQuantityLabel() {
        return cart_quantity_label.getText();
    }

    public String getDescriptionLabel() {
        return cart_quantity_label.getText();
    }



    public void clickCheckout() {
        clickRetry(checkout);
    }

    public void clickContinueShopping() {
        clickRetry(continueShopping);
    }


    @Override
    public boolean pageIsLoaded() {
        return true;
    }
}
