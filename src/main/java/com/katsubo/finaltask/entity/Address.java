package com.katsubo.finaltask.entity;

import java.util.Objects;

/**
 * The type Address.
 */
public class Address {
    private String country;
    private String city;
    private String street;
    private int building;

    /**
     * Instantiates a new Address.
     *
     * @param address the address
     */
    public Address(String address) {
        String[] strings = address.split(", ");
        this.country = strings[0];
        this.city = strings[1];
        this.street = strings[2];
        this.building = Integer.valueOf(strings[3]);
    }

    /**
     * Instantiates a new Address.
     *
     * @param country  the country
     * @param city     the city
     * @param street   the street
     * @param building the building
     */
    public Address(String country, String city, String street, int building) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.building = building;
    }

    /**
     * Gets country.
     *
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * Gets city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets city.
     *
     * @param city the city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets street.
     *
     * @return the street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Sets street.
     *
     * @param street the street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Gets building.
     *
     * @return the building
     */
    public int getBuilding() {
        return building;
    }

    /**
     * Sets building.
     *
     * @param building the building
     */
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
