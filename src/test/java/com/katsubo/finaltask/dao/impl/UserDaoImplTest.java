package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.connection.ConnectionPool;
import com.katsubo.finaltask.dao.UserDao;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Permission;
import com.katsubo.finaltask.dao.DaoException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class UserDaoImplTest {
    static ConnectionPool pool;
    UserDao dao;

    @BeforeClass
    public static void preparePool(){
        pool = ConnectionPool.getInstance();
    }
    @Before
    public void setUp() throws Exception {
        dao = new UserDaoImpl();
        ((UserDaoImpl) dao).setConnection(pool.getConnection());
    }

    @Test
    public void read() throws DaoException {
        String login = "root";
        String password = "1234";
        User actual = dao.read(login, password);
        User expected = new User();
        expected.setId(1);
        expected.setLogin(login);
        expected.setPassword(password);
        expected.setPermission(Permission.ADMINISTRATOR);

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void read1() {
    }

    @Test
    public void create() {
    }

    @Test
    public void read2() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }
}