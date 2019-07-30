package com.katsubo.finaltask.entity;

import com.katsubo.finaltask.entity.enums.Theme;

import java.util.Date;
import java.util.Objects;

public class Event extends Entity {
    private String name;
    private String description;
    private String pictureLink;
    private Theme theme;
    private Date date;
    private Address address;
    private Integer author_id;
    private Integer capacity;
    private String shortDescription;

    public String getName() {
        return name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    private void setShortDescription() {
        int sizeShortDescription = 140;
        if (description.length() > sizeShortDescription)
        shortDescription = description.substring(0, sizeShortDescription);
        shortDescription += "...";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        setShortDescription();
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

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public Integer getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
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
                Objects.equals(capacity, event.capacity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, theme, date, address, capacity);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", theme=" + theme +
                ", date=" + date +
                ", address=" + address +
                ", capacity=" + capacity +
                '}';
    }
}
