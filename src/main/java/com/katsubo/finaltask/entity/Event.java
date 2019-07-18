package com.katsubo.finaltask.entity;

import com.katsubo.finaltask.entity.enums.Status;
import com.katsubo.finaltask.entity.enums.Theme;

import java.util.Date;
import java.util.Objects;

public class Event extends Entity {
    private String name;
    private String description;
    private Theme theme;
    private Date date;
    private Address address;
    private String countryCode;
    private Status status;
    private Integer capacity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) &&
                Objects.equals(description, event.description) &&
                theme == event.theme &&
                Objects.equals(date, event.date) &&
                Objects.equals(address, event.address) &&
                Objects.equals(countryCode, event.countryCode) &&
                status == event.status &&
                Objects.equals(capacity, event.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, theme, date, address, countryCode, status, capacity);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", theme=" + theme +
                ", date=" + date +
                ", address=" + address +
                ", countryCode='" + countryCode + '\'' +
                ", status=" + status +
                ", capacity=" + capacity +
                '}';
    }
}
