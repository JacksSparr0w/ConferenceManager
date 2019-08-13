package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;

/**
 * The interface User info dao.
 */
public interface UserInfoDao extends Dao<UserInfo> {
    /**
     * Read user info.
     *
     * @param user the user
     * @return the user info
     * @throws DaoException the dao exception
     */
    UserInfo read(User user) throws DaoException;
}
