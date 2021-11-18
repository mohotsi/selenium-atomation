package com.saucedemo.seleniumatomation.config;

import com.saucedemo.seleniumatomation.Model.Item;
import com.saucedemo.seleniumatomation.page.ItemPageComponent;
import lombok.val;

import java.util.HashMap;
import java.util.Map;

public class SaveVariables {
   public static HashMap<String, Item> addedToCart =new HashMap<String,Item>();
   public static Item selectedItem;



}
