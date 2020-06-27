package com.example.orderpizza;

import java.io.Serializable;

public class Address implements Serializable {
    private String Description;
    private String address;

    public Address(String description, String address) {
        Description = description;
        this.address = address;
    }
    public String getDesc() {
        return Description;
    }

    public String getAddress() {
        return address;
    }
}
