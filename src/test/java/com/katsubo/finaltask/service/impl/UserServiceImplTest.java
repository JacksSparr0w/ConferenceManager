package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
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
    public void findById() throws DaoException, ServiceException {
        User expectedUser = new User();
        expectedUser.setLogin(LOGIN);
        expectedUser.setPassword(PASSWORD);
        expectedUser.setPermission(Permission.USER);

        UserService service = new UserServiceImpl();
        Integer id = service.save(expectedUser);
        expectedUser.setId(id);

        User actualUser = service.findById(id);
        Assert.assertEquals(expectedUser, actualUser);

    }

    @Test
    public void isExist() throws DaoException, ServiceException {
        User user = new User();
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setPermission(Permission.USER);

        UserService service = new UserServiceImpl();
        service.save(user);
        boolean exist = service.isExist(LOGIN);
        Assert.assertTrue(exist);

    }

    @Test
    public void createUser() throws DaoException, ServiceException {
        User user = new User();
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setPermission(Permission.USER);

        UserService service = new UserServiceImpl();
        Integer id = service.save(user);
        Assert.assertNotNull(id);

    }

    @After
    public void clean() throws DaoException, ServiceException {
        UserService service = new UserServiceImpl();
        User user = service.findByLoginAndPassword(LOGIN, PASSWORD);
        if (user != null) {
            service.delete(user.getId());
        }

    }
}