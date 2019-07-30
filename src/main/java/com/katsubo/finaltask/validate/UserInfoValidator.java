package com.katsubo.finaltask.validate;

import com.katsubo.finaltask.entity.UserInfo;

public class UserInfoValidator implements Validator<UserInfo> {
    @Override
    public boolean isValid(UserInfo entity) {
        if (entity == null) {
            return false;
        }
        return entity.getName() != null &&
                entity.getSurname() != null &&
                entity.getEmail() != null &&
                entity.getAbout() != null &&
                entity.getPictureLink() != null &&
                entity.getDateOfBirth() != null &&
                entity.getDateOfRegistration() != null;
    }
}
