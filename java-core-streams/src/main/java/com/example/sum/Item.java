package com.example.sum;

/**
 * created by Atiye Mousavi
 * Date: 12/17/2021
 * Time: 9:25 AM
 **/


public class Item {
    private int id;
    private Integer price;
    public Item(int id, Integer price) {
        super();
        this.id = id;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
