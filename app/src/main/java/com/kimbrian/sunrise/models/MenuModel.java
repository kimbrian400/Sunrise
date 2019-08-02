package com.kimbrian.sunrise.models;

public class MenuModel {

    public String dish;
    public String price;

    public MenuModel(){

    }

    public MenuModel(String dish, String price){
        this.dish = dish;
        this.price = price;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
