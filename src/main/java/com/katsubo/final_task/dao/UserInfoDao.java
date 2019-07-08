package com.katsubo.final_task.dao;

import com.katsubo.final_task.entity.User;
import com.katsubo.final_task.entity.UserInfo;
import com.katsubo.final_task.exception.DaoException;

public interface UserInfoDao extends Dao<UserInfo> {
    UserInfo read(User user) throws DaoException;
}
