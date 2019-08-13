package com.katsubo.finaltask.entity;

import java.util.Date;
import java.util.Objects;

/**
 * The type User info.
 */
public class UserInfo extends Entity {
    private User user;
    private String name;
    private String surname;
    private String about;
    private String pictureLink;
    private String email;
    private Date dateOfBirth;
    private Date dateOfRegistration;

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

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
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets about.
     *
     * @return the about
     */
    public String getAbout() {
        return about;
    }

    /**
     * Sets about.
     *
     * @param about the about
     */
    public void setAbout(String about) {
        this.about = about;
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
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets date of registration.
     *
     * @return the date of registration
     */
    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

    /**
     * Sets date of registration.
     *
     * @param dateOfRegistration the date of registration
     */
    public void setDateOfRegistration(Date dateOfRegistration) {
        this.dateOfRegistration = dateOfRegistration;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        UserInfo userInfo = (UserInfo) o;
        return Objects.equals(user, userInfo.user) &&
                Objects.equals(name, userInfo.name) &&
                Objects.equals(surname, userInfo.surname) &&
                Objects.equals(about, userInfo.about) &&
                Objects.equals(pictureLink, userInfo.pictureLink) &&
                Objects.equals(email, userInfo.email) &&
                Objects.equals(dateOfBirth, userInfo.dateOfBirth) &&
                Objects.equals(dateOfRegistration, userInfo.dateOfRegistration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), user, name, surname, about, pictureLink, email, dateOfBirth, dateOfRegistration);
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "user=" + user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", about='" + about + '\'' +
                ", pictureLink='" + pictureLink + '\'' +
                ", email='" + email + '\'' +
                ", dateOfBirth=" + new Date(dateOfBirth.getTime()) +
                ", dateOfRegistration=" + new Date(dateOfRegistration.getTime()) +
                '}';
    }
}