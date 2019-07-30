package com.katsubo.finaltask.validate;

import com.katsubo.finaltask.entity.User;

public class UserValidator implements Validator<User> {
    @Override
    public boolean isValid(User entity) {
        return false;
    }
}
