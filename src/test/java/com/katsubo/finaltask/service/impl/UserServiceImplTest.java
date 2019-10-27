package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.service.PermissionService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.*;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImplTest {
    private static final String LOGIN_1 = "test1";
    private static final String PASSWORD_1 = "pass1";
    private static final Permission userPermission = new Permission("user");
    private static final String LOGIN_2 = "test2";
    private static final String PASSWORD_2 = "pass2";
    private static final Permission adminPermission = new Permission("admin");
    private static User user1;
    private static User user2;
    private static UserService service;
    private static PermissionService permissionService;

    @BeforeClass
    public static void prepareService() throws ServiceException {
        permissionService = new PermissionServiceImpl();
        userPermission.setId(permissionService.save(userPermission));
        adminPermission.setId(permissionService.save(adminPermission));

        service = new UserServiceImpl();
        List<User> users = service.findAll();
        for (User user : users) {
            if (service.isExist(user.getLogin())) {
                service.delete(user.getId());
            }
        }
    }

    @AfterClass
    public static void cleanPermissions() throws ServiceException {
        List<Permission> permissions = permissionService.readAll();
        for (Permission permission : permissions) {
            permissionService.delete(permission.getId());
        }
    }

    private String hash(String password) {
        return DigestUtils.md5Hex(password);
    }

    @Before
    public void prepareUsers() {
        user1 = new User();
        user1.setLogin(LOGIN_1);
        user1.setPassword(hash(PASSWORD_1));
        user1.setPermission(userPermission);

        user2 = new User();
        user2.setLogin(LOGIN_2);
        user2.setPassword(hash(PASSWORD_2));
        user2.setPermission(adminPermission);
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
        for (User user : users) {
            if (service.isExist(user.getLogin())) {
                service.delete(user.getId());
            }
        }
    }
}