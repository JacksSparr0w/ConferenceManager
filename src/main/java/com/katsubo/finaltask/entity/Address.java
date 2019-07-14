package com.katsubo.finaltask.entity;

import java.util.Objects;

public class Address {
    private String country;
    private String city;
    private String street;
    private int building;

    public Address(String address) {
        String[] strings = address.split(", ");
        this.country = strings[0];
        this.city = strings[1];
        this.street = strings[2];
        this.building = Integer.valueOf(strings[3]);
        //todo add table
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getBuilding() {
        return building;
    }

    public void setBuilding(int building) {
        this.building = building;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address adress = (Address) o;
        return building == adress.building &&
                Objects.equals(country, adress.country) &&
                Objects.equals(city, adress.city) &&
                Objects.equals(street, adress.street);
    }

    @Override
    public int hashCode() {
        return Objects.hash(country, city, street, building);
    }

    @Override
    public String toString() {
        return country + ", " + city + ", " + street + ", " + building;
    }
}
