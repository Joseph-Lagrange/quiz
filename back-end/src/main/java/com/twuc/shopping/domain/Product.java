package com.twuc.shopping.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.*;

public class Product {

    @NotNull
    private String name;

    @NotNull
    private int price;

    @NotNull
    private String unit;

    @NotNull
    private String url;

    public Product(String name, int price, String unit, String url) {
        this.name = name;
        this.price = price;
        this.unit = unit;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
