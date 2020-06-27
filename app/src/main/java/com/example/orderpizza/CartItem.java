package com.example.orderpizza;

import java.io.Serializable;

public class CartItem implements Serializable {
    private Product product;
    private String size;
    private int count;
    private double sum;

    public CartItem(Product product, String size, int count, double sum) {
        this.product = product;
        this.size = size;
        this.count = count;
        this.sum = sum;
    }

    public Product getProduct() {
        return product;
    }

    public String getSize() {
        return size;
    }

    public int getCount() {
        return count;
    }

    public double getSum() {
        return sum;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public void updateSum(){
        double price = 0d;
        if(size.equals("Small"))
            price = product.getPriceSmall();
        if(size.equals("Medium"))
            price = product.getPriceMedium();
        if(size.equals("Large"))
            price = product.getPriceLarge();
        if(size.equals(""))
            price = product.getPriceSmall();

        sum = (double) count * price;
    }
}
