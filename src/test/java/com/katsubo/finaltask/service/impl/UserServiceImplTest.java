package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.connection.PoolService;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.TransactionFactory;
import com.katsubo.finaltask.dao.impl.TransactionFactoryImpl;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Permission;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import org.junit.After;
import org.junit.Assert;
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
    public void findById2() throws DaoException, ServiceException {
        String login = "user1";
        String password = "user";

        User user = null;
        UserService service = new UserServiceImpl();
        TransactionFactory factory = new TransactionFactoryImpl();
        ((UserServiceImpl) service).setTransaction(factory.getTransaction());
        user = service.findByLoginAndPassword(login, password);

        Assert.assertNotNull(user.getId());

    }

    @Test
    public void createUser() throws DaoException, ServiceException{
        User user = new User();
        user.setLogin("TestUser");
        user.setPassword("pass");
        user.setPermission(Permission.USER);

        UserService service = new UserServiceImpl();
        TransactionFactory factory = new TransactionFactoryImpl();
        ((UserServiceImpl) service).setTransaction(factory.getTransaction());
        Integer id = service.save(user);

        Assert.assertNotNull(id);

    }

    @After
    public void clean() throws DaoException, ServiceException {
        UserService service = new UserServiceImpl();
        TransactionFactory factory = new TransactionFactoryImpl();
        ((UserServiceImpl) service).setTransaction(factory.getTransaction());
        User user = service.findByLoginAndPassword("TestUser", "pass");
        if (user != null) {
            service.delete(user.getId());
        }

    }
}