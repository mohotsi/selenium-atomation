package com.saucedemo.seleniumatomation.page;

import com.saucedemo.seleniumatomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
@Page
public class CompletePage extends SharedComponents{
    @FindBy(className = "complete-header")
    WebElement header;

    @FindBy(className = "complete-text")
    WebElement complete;



    @FindBy(className = "title")
    WebElement title;

    @FindBy(id = "back-to-products")
    WebElement backHome;

       public String getHeaderText(){
           return header.getText();
       }
    public String getCompleteText(){
        return complete.getText();
    }
    public String getTitle(){
        return title.getText();
    }

    @Override
    public boolean pageIsLoaded() {
        return true;
    }
}
