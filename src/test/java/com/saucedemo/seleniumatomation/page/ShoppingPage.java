package com.saucedemo.seleniumatomation.page;

import com.saucedemo.seleniumatomation.annotation.Page;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;
@Page
public class ShoppingPage extends SharedComponents {

  @FindBy(className = "product_sort_container")
    WebElement sortMenu;
  @FindBy(className = "title")
    WebElement title;
  @FindBy(className = "inventory_item")
    List<WebElement> items;



  public List<ItemPageComponent> getItems(){
    return items.stream().map(item->new ItemPageComponent(item)).collect(Collectors.toList());
  }
  public void sortBy(String by){
    select(sortMenu,by);
  }



  @Override
  public boolean pageIsLoaded() {
    return waitForElement.until((e)->title.isDisplayed());
  }
}
