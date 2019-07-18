package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.connection.PoolService;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.TransactionFactory;
import com.katsubo.finaltask.dao.impl.TransactionFactoryImpl;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserInfoService;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class UserInfoServiceImplTest {
    static PoolService service;

    @Test
    public void save() throws ServiceException, DaoException {
        UserInfo info = new UserInfo();
        User user = new User();
        user.setId(1);
        info.setUser(user);
        info.setName("name");
        info.setSurname("surname");
        info.setEmail("email");
        info.setDateOfRegistration(new Date());


        UserInfoService service = new UserInfoServiceImpl();
        TransactionFactory factory = new TransactionFactoryImpl();
        ((UserInfoServiceImpl) service).setTransaction(factory.getTransaction());
        service.save(info);

        UserInfo actual = service.findByUser(user);
        Assert.assertNotNull(actual);
    }
}