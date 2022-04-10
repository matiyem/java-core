package com.example.nestedHashMaps;

/*
    Create by Atiye Mousavi 
    Date: 4/9/2022
    Time: 12:34 PM
**/
public class Address {
    private Integer addressId;
    private String addressLocation;

    public Address() {
    }

    public Address(Integer addressId, String addressLocation) {
        this.addressId = addressId;
        this.addressLocation = addressLocation;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(String addressLocation) {
        this.addressLocation = addressLocation;
    }
}
