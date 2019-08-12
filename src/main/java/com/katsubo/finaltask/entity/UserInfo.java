package com.katsubo.finaltask.entity;

import java.util.Date;
import java.util.Objects;

public class UserInfo extends Entity {
    private User user;
    private String name;
    private String surname;
    private String about;
    private String pictureLink;
    private String email;
    private Date dateOfBirth;
    private Date dateOfRegistration;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPictureLink() {
        return pictureLink;
    }

    public void setPictureLink(String pictureLink) {
        this.pictureLink = pictureLink;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Date getDateOfRegistration() {
        return dateOfRegistration;
    }

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