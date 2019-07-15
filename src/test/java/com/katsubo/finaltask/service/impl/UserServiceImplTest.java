package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.connection.PoolException;
import com.katsubo.finaltask.connection.PoolService;
import com.katsubo.finaltask.connection.PoolServiceImpl;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.TransactionFactory;
import com.katsubo.finaltask.dao.impl.TransactionFactoryImpl;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Permission;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserServiceImplTest {
    static PoolService service;

    @Test
    public void findById() throws DaoException {
        String login = "root";
        String password = "user";

        User user = null;
        try {
            UserService userService = new UserServiceImpl();
            TransactionFactory factory = new TransactionFactoryImpl();
            ((UserServiceImpl) userService).setTransaction(factory.getTransaction());
            user = userService.findByLoginAndPassword(login, password);

        } catch (DaoException | ServiceException e) {

        }
        Assert.assertEquals(user.getId(), new Integer(1));

    }

    @Test
    public void findById1() throws DaoException {
        String login = "user";
        String password = "user";

        User user = null;
        try {
            UserService userService = new UserServiceImpl();
            TransactionFactory factory = new TransactionFactoryImpl();
            ((UserServiceImpl) userService).setTransaction(factory.getTransaction());
            user = userService.findByLoginAndPassword(login, password);

        } catch (DaoException | ServiceException e) {

        }
        Assert.assertEquals(user.getId(), new Integer(2));

    }

    @Test
    public void findById2() throws DaoException {
        String login = "user1";
        String password = "user";

        User user = null;
        try {
            UserService service = new UserServiceImpl();
            TransactionFactory factory = new TransactionFactoryImpl();
            ((UserServiceImpl) service).setTransaction(factory.getTransaction());
            user = service.findByLoginAndPassword(login, password);

        } catch (DaoException | ServiceException e) {

        }
        Assert.assertEquals(user.getId(), new Integer(3));

    }
}