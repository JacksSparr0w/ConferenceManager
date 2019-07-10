package com.katsubo.final_task.dao.impl;

import com.katsubo.final_task.dao.UserDao;
import com.katsubo.final_task.entity.User;
import com.katsubo.final_task.entity.enums.Permission;
import com.katsubo.final_task.exception.DaoException;
import com.sun.org.apache.bcel.internal.generic.DADD;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.zip.Adler32;

import static org.junit.Assert.*;

public class UserDaoImplTest {
    UserDao dao;

    @Before
    public void setUp() throws Exception {
        dao = new UserDaoImpl();
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