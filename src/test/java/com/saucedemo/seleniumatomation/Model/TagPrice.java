package com.saucedemo.seleniumatomation.Model;

import lombok.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TagPrice {
    private String itemName;
    private Double value;

}
