package com.katsubo.finaltask.entity;

import java.util.Date;
import java.util.Objects;

/**
 * The type Event.
 */
public class Event extends Entity {
    private String name;
    private String description;
    private String pictureLink;
    private Value theme;
    private Date date;
    private Address address;
    private Integer author_id;
    private Integer capacity;
    private Date duration;
    private String shortDescription;

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets short description.
     *
     * @return the short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    private void setShortDescription() {
        int sizeShortDescription = 140;
        if (description.length() > sizeShortDescription) {
            shortDescription = description.substring(0, sizeShortDescription);
            shortDescription += "...";
        } else {
            shortDescription = description;
        }
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
        setShortDescription();
    }

    /**
     * Gets theme.
     *
     * @return the theme
     */
    public Value getTheme() {
        return theme;
    }

    /**
     * Sets theme.
     *
     * @param theme the theme
     */
    public void setTheme(Value theme) {
        this.theme = theme;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets capacity.
     *
     * @return the capacity
     */
    public Integer getCapacity() {
        return capacity;
    }

    /**
     * Sets capacity.
     *
     * @param capacity the capacity
     */
    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    /**
     * Gets picture link.
     *
     * @return the picture link
     */
    public String getPictureLink() {
        return pictureLink;
    }

    /**
     * Sets picture link.
     *
     * @param pictureLink the picture link
     */
    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    /**
     * Gets author id.
     *
     * @return the author id
     */
    public Integer getAuthor_id() {
        return author_id;
    }

    /**
     * Sets author id.
     *
     * @param author_id the author id
     */
    public void setAuthor_id(Integer author_id) {
        this.author_id = author_id;
    }

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Event event = (Event) o;
        return Objects.equals(name, event.name) &&
                Objects.equals(description, event.description) &&
                Objects.equals(pictureLink, event.pictureLink) &&
                Objects.equals(theme, event.theme) &&
                Objects.equals(date, event.date) &&
                Objects.equals(address, event.address) &&
                Objects.equals(author_id, event.author_id) &&
                Objects.equals(capacity, event.capacity) &&
                Objects.equals(duration, event.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description, pictureLink, theme, date, address, author_id, capacity, duration);
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                ", theme=" + theme +
                ", date=" + new Date(date.getTime()) +
                ", address=" + address +
                ", author_id=" + author_id +
                ", capacity=" + capacity +
                ", duration=" + duration.getTime() +
                '}';
    }
}
