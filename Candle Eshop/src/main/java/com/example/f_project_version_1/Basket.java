package com.example.f_project_version_1;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.criteria.CriteriaBuilder;


@Entity
public class Basket {

    @Id
    @GeneratedValue
    private Integer basketID;


    private String name ;
    private String color;
    private Integer price;

    public Integer getBasketID() {
        return basketID;
    }

    public void setBasketID(Integer basketID) {
        this.basketID = basketID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Basket(String name, String color, Integer price) {
        this.name = name;
        this.color = color;
        this.price = price;
    }

    public Basket() {
    }
}
