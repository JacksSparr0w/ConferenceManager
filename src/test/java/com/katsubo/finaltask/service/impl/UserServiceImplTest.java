package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Permission;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTest {
    private static final String LOGIN_1 = "test1";
    private static final String PASSWORD_1 = "pass1";
    private static final Permission PERMISSION_1 = Permission.USER;

    private static final String LOGIN_2 = "test2";
    private static final String PASSWORD_2 = "pass2";
    private static final Permission PERMISSION_2 = Permission.USER;

    private static User user1;
    private static User user2;
    private static UserService service;

    @BeforeClass
    public static void prepareService() throws ServiceException {
        service = new UserServiceImpl();
    }

    @Before
    public void prepareUsers(){
        user1 = new User();
        user1.setLogin(LOGIN_1);
        user1.setPassword(PASSWORD_1);
        user1.setPermission(PERMISSION_1);

        user2 = new User();
        user2.setLogin(LOGIN_2);
        user2.setPassword(PASSWORD_2);
        user2.setPermission(PERMISSION_2);
    }

    @Test
    public void successCreate() throws ServiceException {
        Integer id = service.save(user1);

        Assert.assertNotNull(id);
    }
    @Test
    public void createAndFindByLoginAndPassword() throws ServiceException {
        User expected = user1;
        service.save(expected);
        User actual = service.findByLoginAndPassword(user1.getLogin(), PASSWORD_1);

        Assert.assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void createAndFindById() throws ServiceException {
        User expected = user1;
        Integer id = service.save(expected);
        User actual = service.findById(id);

        Assert.assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void createAndDelete() throws ServiceException {
        User expected = user1;
        Integer id = service.save(expected);
        service.delete(id);
        User actual = service.findById(id);

        Assert.assertNull(actual);

    }

    @Test
    public void createAndFindAll() throws ServiceException {
        service.save(user1);
        service.save(user2);
        List<User> actual = service.findAll();
        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = ServiceException.class)
    public void invalidInput() throws ServiceException {
        service.findById(user1.getId());
    }

    @Test(expected = ServiceException.class)
    public void invalidSave() throws ServiceException {
        service.save(user1);
        service.save(user1);
    }

    @After
    public void clean() throws ServiceException {
        List<User> users = service.findAll();
        for (User user : users){
            if (service.isExist(user.getLogin())){
                service.delete(user.getId());
            }
        }

    }
}