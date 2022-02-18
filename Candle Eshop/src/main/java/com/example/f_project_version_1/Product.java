package com.example.f_project_version_1;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class Product {

    @Id
    @GeneratedValue
    private Integer productID;

    private String name ;
    private String scent;
    private int scentID;
    private String color;
    private int colorID;
    private int price;
    private int uniqueID;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScent() {
        return scent;
    }

    public void setScent(String scent) {
        this.scent = scent;
    }

    public int getScentID() {
        return scentID;
    }

    public void setScentID(int scentID) {
        this.scentID = scentID;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getColorID() {
        return colorID;
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Product(String name, String scent, int scentID, String color, int colorID, int price, int uniqueID) {
        this.name = name;
        this.scent = scent;
        this.scentID = scentID;
        this.color = color;
        this.colorID = colorID;
        this.price = price;
        this.uniqueID = uniqueID;
    }

    public Product() {
    }
}
