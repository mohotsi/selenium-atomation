package com.saucedemo.seleniumatomation.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ItemPageComponent {
    WebElement item;

    public ItemPageComponent(WebElement item) {
    this.item=item;
    }
    public WebElement getName(){
       return item.findElement(By.className("inventory_item_name"));
    }
    public WebElement getImage(){
        return  item.findElement(By.tagName("img"));
    }
    public WebElement getDescription(){
        return item.findElement(By.className("inventory_item_desc"));
    }
    public WebElement getPrice(){
        return item.findElement(By.className("inventory_item_price"));
    }
    public Double getPriceValue(){
        return Double.parseDouble(getPrice().getText().replaceAll("\\$([0-9\\.]+)","$1"));
    }
    private String getImageUrl(){
       return getImage().getAttribute("scr");
    }

    public WebElement getAddToCartButton(){
        return  item.findElement(By.tagName("button"));
    }
    public WebElement getRemoveButton(){
        return  item.findElement(By.tagName("button"));
    }
    public WebElement getQuantity(){return  item.findElement(By.className("cart_quantity"));}
}
