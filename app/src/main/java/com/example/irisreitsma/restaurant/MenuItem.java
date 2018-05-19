package com.example.irisreitsma.restaurant;

import java.io.Serializable;

/**
 * Created by Iris Reitsma on 6-5-2018.
 */

public class MenuItem implements Serializable{

    private String name;
    private String description;
    private String imageUrl;
    private double price;
    private String category;

    // getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
