package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.service.PermissionService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserInfoService;
import com.katsubo.finaltask.service.UserService;
import org.junit.*;

import java.util.Date;
import java.util.List;

public class UserInfoServiceImplTest {
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String SOME_MAIL_COM = "some@mail.com";
    private static final Permission PERMISSION = new Permission("user");
    private static final String PASSWORD = "pass";
    private static final String LOGIN = "test";
    private static User user;
    private static UserInfo info1;
    private static UserInfo info2;

    private static UserInfoService service;
    private static UserService userService;
    private static PermissionService permissionService;

    @BeforeClass
    public static void prepareService() throws ServiceException {
        permissionService = new PermissionServiceImpl();
        PERMISSION.setId(permissionService.save(PERMISSION));

        service = new UserInfoServiceImpl();
        userService = new UserServiceImpl();

        List<User> users = userService.findAll();
        for (User user : users) {
            userService.delete(user.getId());
        }

        user = new User();
        user.setLogin(LOGIN);
        user.setPassword(PASSWORD);
        user.setPermission(PERMISSION);
        Integer userId = userService.save(user);
        user.setId(userId);
    }

    @AfterClass
    public static void cleanUser() throws ServiceException {
        List<User> users = userService.findAll();
        for (User user : users) {
            userService.delete(user.getId());
        }

        List<Permission> permissions = permissionService.readAll();
        for (Permission permission : permissions) {
            permissionService.delete(permission.getId());
        }
    }

    @Before
    public void prepareBeans() {
        info1 = new UserInfo();
        info1.setName(NAME);
        info1.setSurname(SURNAME);
        info1.setEmail(SOME_MAIL_COM);
        info1.setDateOfBirth(new Date(1565625300000L));
        info1.setDateOfRegistration(new Date(1565625300000L));
        info1.setUser(user);

    }

    @Test
    public void createAndFindByUser() throws ServiceException {
        UserInfo expected = info1;
        service.save(expected);
        UserInfo actual = service.findByUser(user);


        Assert.assertEquals(expected.toString(), actual.toString());
    }


    @Test
    public void createAndDelete() throws ServiceException {
        Integer id = service.save(info1);
        service.delete(id);
        UserInfo actual = service.findByUser(user);

        Assert.assertNotNull(id);
        Assert.assertNull(actual);

    }


    @Test(expected = ServiceException.class)
    public void invalidInput() throws ServiceException {
        service.delete(info1.getId());
    }

    @Test(expected = ServiceException.class)
    public void invalidSave() throws ServiceException {
        service.save(info2);
    }

    @After
    public void clean() throws ServiceException {
        UserInfo info = service.findByUser(user);
        if (info != null) {
            service.delete(info.getId());
        }
    }
}