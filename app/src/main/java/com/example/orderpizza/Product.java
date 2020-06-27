package com.example.orderpizza;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String description;
    private String price;
    private int image;
    private double priceSmall;
    private double priceMedium;
    private double priceLarge;


    Product(String name, String description, String price, int image,double priceSmall, double priceMedium, double priceLarge) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.priceSmall = priceSmall;
        this.priceMedium = priceMedium;
        this.priceLarge = priceLarge;
    }



    public String getPrice() {
        return this.price;
    }

    public String getDesc() {
        return this.description;
    }

    public String getName() {
        return this.name;
    }

    public int getImage() {
        return this.image;
    }


    public double getPriceSmall() {
        return priceSmall;
    }

    public double getPriceMedium() {
        return priceMedium;
    }

    public double getPriceLarge() {
        return priceLarge;
    }

    public String getSmall() {
        String price = String.format("%.2f", priceSmall) + " AZN";
        return price;
    }

    public String getMedium() {
        String price = String.format("%.2f", priceMedium) + " AZN";
        return price;
    }

    public String getLarge() {
        String price = String.format("%.2f", priceLarge) + " AZN";
        return price;
    }

    public boolean isEqual(Product product){
        if(this.name.equals(product.getName()) &
        this.description.equals(product.getDesc()) &
        this.image == product.getImage() &
        this.priceSmall == product.getPriceSmall() &
        this.priceMedium == product.getPriceMedium() &
        this.priceLarge == product.getPriceLarge())
            return true;
        else
            return false;

    }
}