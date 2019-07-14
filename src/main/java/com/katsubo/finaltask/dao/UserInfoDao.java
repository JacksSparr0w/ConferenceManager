package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;

public interface UserInfoDao extends Dao<UserInfo> {
    UserInfo read(User user) throws DaoException;
}
