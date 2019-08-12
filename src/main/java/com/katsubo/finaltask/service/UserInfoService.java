package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;

public interface UserInfoService extends Service {
    UserInfo findByUser(User user) throws ServiceException;

    Integer save(UserInfo userInfo) throws ServiceException;

    void delete(Integer id) throws ServiceException;
}
