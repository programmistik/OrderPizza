package com.example.orderpizza;

import java.io.Serializable;
import java.util.ArrayList;

public class Profile implements Serializable {
    private String name;
    private String phone;
    private ArrayList<Address> addresses;
    private ArrayList<CartItem> cart;


    public Profile(String name, String phone) {
        this.name = name;
        this.phone = phone;
        addresses = new ArrayList<>();
        cart = new ArrayList<>();
    }

    public void AddAddress(Address address){
        addresses.add(address);
    }

    public void AddItem(CartItem item){
        if (cart.size() == 0) {
            cart.add(item);
        }
        else {
            for (CartItem ci: cart) {
                if(ci.getProduct().isEqual(item.getProduct()) & ci.getSize().equals(item.getSize())) {
                    ci.setCount(ci.getCount()+item.getCount());
                    ci.setSum(ci.getSum()+item.getSum());
                    return;
                }

            }
            cart.add(item);
        }
    }

    public String getName(){
        return this.name;
    }

    public ArrayList<Address> getAddresses() {
        return addresses;
    }


    public ArrayList<CartItem> getCart() {
        return cart;
    }

    public String getPhone() {
        return phone;
    }

    public double getTotal(){
        double total = 0d;
        for (CartItem ci: cart) {
            total = total + ci.getSum();
        }
        return total;
    }
}
