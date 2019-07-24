package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserInfoService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class UserInfoServiceImplTest {

    @Test
    public void save() throws ServiceException, DaoException {
        UserInfo info = new UserInfo();
        User user = new User();
        user.setId(12);
        info.setUser(user);
        info.setName("name");
        info.setSurname("surname");
        info.setEmail("email");
        info.setDateOfRegistration(new Date());


        UserInfoService service = new UserInfoServiceImpl();
        service.save(info);

        UserInfo actual = service.findByUser(user);
        Assert.assertNotNull(actual);
    }
}