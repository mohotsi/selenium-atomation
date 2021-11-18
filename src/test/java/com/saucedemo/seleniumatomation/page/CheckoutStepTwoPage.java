package com.saucedemo.seleniumatomation.page;

import com.google.common.collect.Streams;
import com.saucedemo.seleniumatomation.Model.TagPrice;
import com.saucedemo.seleniumatomation.annotation.Page;
import lombok.val;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
@Page
public class CheckoutStepTwoPage extends SharedComponents {
    @FindBy(className = "summary_info_label")
    List<WebElement> summary_info_label;
    @FindBy(className = "summary_value_label")
    List<WebElement> summary_value_label;

    @FindBy(className = "summary_subtotal_label")
    WebElement summary_subtotal_label;

    @FindBy(className = "summary_tax_label")
    WebElement summary_tax_label;
    @FindBy(className = "summary_total_label")
    WebElement summary_total_label;
    @FindBy(id="finish")
    WebElement finish;


    public HashMap<String,String> getSummary(){
        HashMap mMap = new HashMap();
        Streams.zip(summary_info_label.stream().map(WebElement::getText),
                summary_value_label.stream().map(WebElement::getText),(info,value)-> mMap.put(info, value));
        return mMap;
    }
    public void clickFinishButton(){
        clickRetry(finish);
    }
    public TagPrice getItemTotal(){
        return getPrice(summary_subtotal_label.getText());
    }
    public TagPrice getTotal(){
        return getPrice(summary_total_label.getText());
    }
    public TagPrice getTax(){
        return getPrice(summary_tax_label.getText());
    }
    private TagPrice getPrice(final String txt){
        TagPrice tagPrice = new TagPrice();
        val extractValues=txt.replaceAll("([a-zA-Z]+):\\s\\$([0-9\\.]+)","$1,$2").split(",");
        tagPrice.setItemName(extractValues[0]);
        tagPrice.setValue(Double.parseDouble(extractValues[1]));
      return  tagPrice;
    }

    @Override
    public boolean pageIsLoaded() {
        return false;
    }
}
