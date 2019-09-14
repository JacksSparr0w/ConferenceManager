package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.entity.Address;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventServiceImplTest {
    private static final String DESCRIPTION = "description";
    private static final Integer PERMISSION = new Integer(1);
    private static final String PASSWORD = "pass";
    private static final String LOGIN = "test";
    private static final String EVENT_NAME = "event1";
    private static final Value THEME = new Value(1);
    {
        THEME.setValue("business");
    }
    private static User user;
    private static Event event1;
    private static Event event2;

    private static EventService service;
    private static UserService userService;

    @BeforeClass
    public static void prepareService() throws ServiceException {
        service = new EventServiceImpl();
        userService = new UserServiceImpl();

        List<Event> events = service.findAll();
        for (Event event : events) {
            service.delete(event.getId());
        }

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
    }

    @Before
    public void prepareBeans() throws ServiceException {


        event1 = new Event();
        event1.setName(EVENT_NAME);
        event1.setDescription(DESCRIPTION);
        event1.setTheme(THEME);
        event1.setPictureLink("");
        event1.setAddress(new Address("country", "city", "street", "100"));
        event1.setDate(new Date(1565625305000L));
        event1.setCapacity(100);
        event1.setDuration(new Date(3600000));

        event1.setAuthor_id(user.getId());

    }

    @Test
    public void successCreate() throws ServiceException {
        Integer id = service.save(event1);
        service.findById(id);

        Assert.assertNotNull(id);
    }

    @Test
    public void createAndFindById() throws ServiceException {
        Event expected = event1;
        Integer id = service.save(expected);
        Event actual = service.findById(id);
        Assert.assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void createAndDelete() throws ServiceException {
        Event expected = event1;
        Integer id = service.save(expected);
        service.delete(id);
        Event actual = service.findById(id);

        Assert.assertNull(actual);

    }

    @Test
    public void createAndFindAll() throws ServiceException {
        service.save(event1);
        List<Event> actual = service.findAll();
        List<Event> expected = new ArrayList<>();
        expected.add(event1);

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = ServiceException.class)
    public void invalidInput() throws ServiceException {
        service.findById(event1.getId());
    }

    @Test(expected = ServiceException.class)
    public void invalidSave() throws ServiceException {
        service.save(event2);
    }

    @After
    public void clean() throws ServiceException {
        List<Event> events = service.findAll();
        for (Event event : events) {
            service.delete(event.getId());
        }
    }

}