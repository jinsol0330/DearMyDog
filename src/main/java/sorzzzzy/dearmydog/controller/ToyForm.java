package sorzzzzy.dearmydog.controller;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ToyForm {
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    private String component;
    private String size;
}
