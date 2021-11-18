package com.saucedemo.seleniumatomation.page;

import com.saucedemo.seleniumatomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Page
public class CheckoutStepOnePage extends SharedComponents {
    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
   private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(id = "cancel")
    private WebElement cancel;

    @FindBy(xpath = "//*[@data-test='error']")
   private WebElement error;

   public void fillInForm(String firstName,String lastName,String postalCode){
       waitPageToDisplay(this.firstName).sendKeys(firstName);
       this.lastName.sendKeys(lastName);
       this.postalCode.sendKeys(postalCode);
   }
   public WebElement error(){
       return error ;
   }
   public void clickContinueButton(){
       clickRetry(continueButton);
   }
    public void clickCancelButton(){
        clickRetry(cancel);
    }

    @Override
    public boolean pageIsLoaded() {
       return true;
    }
}
