package com.katsubo.finaltask.service;

import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;

/**
 * The interface User info service.
 */
public interface UserInfoService extends Service {
    /**
     * Find user info by user.
     *
     * @param user the user
     * @return the user info
     * @throws ServiceException the service exception
     */
    UserInfo findByUser(User user) throws ServiceException;

    /**
     * Save userInfo in database
     *
     * @param userInfo the user info
     * @return the integer
     * @throws ServiceException the service exception
     */
    Integer save(UserInfo userInfo) throws ServiceException;

    /**
     * Delete userInfo by id    
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    void delete(Integer id) throws ServiceException;
}
