package com.katsubo.finaltask.util.repair;

import com.katsubo.finaltask.entity.UserInfo;

import java.util.Date;

/**
 * UserInfoRecover convert null fields info empty fields
 */
public class UserInfoRecover implements Recover<UserInfo> {
    /**
     * @param entity to recover
     * @return recovered entity
     */
    @Override
    public UserInfo recover(UserInfo entity) {
        if (entity == null) {
            entity = new UserInfo();
        }

        if (entity.getName() == null) {
            entity.setName("");
        }

        if (entity.getSurname() == null) {
            entity.setSurname("");
        }

        if (entity.getEmail() == null) {
            entity.setEmail("");
        }
        if (entity.getAbout() == null) {
            entity.setAbout("");
        }
        if (entity.getPictureLink() == null){
            entity.setPictureLink("");
        }
        if (entity.getDateOfRegistration() == null){
            entity.setDateOfRegistration(new Date());
        }
        if (entity.getDateOfBirth() == null){
            entity.setDateOfBirth(new Date());
        }

        return entity;
    }
}
