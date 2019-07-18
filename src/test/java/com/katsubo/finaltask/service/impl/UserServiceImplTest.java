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
    private static final String LOGIN = "qwerty1234";
    private static final String PASSWORD = "sdfvggbvcxf";

    @Test
    public void findByLoginAndPassword() throws DaoException, ServiceException {
        User expectedUser = new User();
        expectedUser.setLogin(LOGIN);
        expectedUser.setPassword(PASSWORD);
        expectedUser.setPermission(Permission.USER);

        UserService userService = new UserServiceImpl();
        expectedUser.setId(userService.save(expectedUser));
        User actualUser = userService.findByLoginAndPassword(LOGIN, PASSWORD);

        Assert.assertEquals(expectedUser, actualUser);

    }

    @Test
    public void findById1() throws DaoException, ServiceException {
        String login = "user";
        String password = "user";

        UserService userService = new UserServiceImpl();
        User user = userService.findByLoginAndPassword(login, password);

        Assert.assertEquals(user.getId(), new Integer(2));

    }

    @Test
    public void findById2() throws DaoException, ServiceException {
        String login = "user1";
        String password = "user";

        UserService service = new UserServiceImpl();
        User user = service.findByLoginAndPassword(login, password);

        Assert.assertNotNull(user.getId());

    }

    @Test
    public void createUser() throws DaoException, ServiceException {
        User user = new User();
        user.setLogin("TestUser");
        user.setPassword("pass");
        user.setPermission(Permission.USER);

        UserService service = new UserServiceImpl();
        Integer id = service.save(user);

        Assert.assertNotNull(id);

    }

    @After
    public void clean() throws DaoException, ServiceException {
        UserService service = new UserServiceImpl();
        User user = service.findByLoginAndPassword("TestUser", "pass");
        if (user != null) {
            service.delete(user.getId());
        }

    }
}