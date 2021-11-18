package com.saucedemo.seleniumatomation.page;

import com.saucedemo.seleniumatomation.Model.Item;
import com.saucedemo.seleniumatomation.annotation.Page;
import lombok.val;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;

@Page
public class InventoryItemPage extends SharedComponents{

@FindBy(className = "inventory_details_img")
    WebElement image;
@FindBy(className = "inventory_details_price")
WebElement price;
@FindBy(className = "inventory_details_name")
WebElement name;
@FindBy(className = "inventory_details_desc")
WebElement description;
@FindBy(xpath = "//button[text()='Add to cart']")
WebElement addToCart;

@FindBy(xpath = "//button[text()='Remove']")
WebElement remove;


    @Override
    public boolean pageIsLoaded() {
        return true;
    }
    public void clickAddToCart(){
        clickRetry(addToCart);
    }
    public byte[] getImage() throws Exception {
        val path=image.getAttribute("src");
        System.out.println("");
       return new Item().readImageFromUrl(path);
    }
    public Double getPrice(){
        return  Double.parseDouble(this.price.getText().replaceAll("\\$([0-9\\.]+)","$1"));
    }
    public String getName(){
        return this.name.getText();
    }
    public String getDescription(){
        return this.description.getText();
    }
}
