package sorzzzzy.dearmydog.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClothesForm {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String season;
    private String color;
}
