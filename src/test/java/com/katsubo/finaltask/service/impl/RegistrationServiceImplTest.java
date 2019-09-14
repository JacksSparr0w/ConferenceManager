package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.entity.*;
import com.katsubo.finaltask.service.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrationServiceImplTest {
    private static final String LOGIN_1 = "test1";
    private static final String DESCRIPTION_1 = "description";
    private static final Integer PERMISSION_1 = new Integer(1);
    private static final String PASSWORD_1 = "pass";
    private static final String EVENT_NAME_1 = "event1";
    private static final Value THEME_1 = new Value(1);
    private static final String DESCRIPTION_2 = "description";
    private static final String LOGIN_2 = "test2";
    private static final Integer PERMISSION_2 = new Integer(1);
    private static final String PASSWORD_2 = "pass";
    private static final String EVENT_NAME_2 = "event1";
    private static final Value THEME_2 = new Value(1);


    private static User user1;
    private static User user2;
    private static Event event1;
    private static Event event2;

    private static EventService eventService;
    private static UserService userService;
    private static RegistrationService service;

    @BeforeClass
    public static void prepareService() throws ServiceException {
        eventService = new EventServiceImpl();
        userService = new UserServiceImpl();
        service = new RegistrationServiceImpl();

        List<User> users = userService.findAll();
        for (User user : users) {
            userService.delete(user.getId());
        }

        ThemeService themeService = new ThemeServiceImpl();

        user1 = new User();
        user1.setLogin(LOGIN_1);
        user1.setPassword(PASSWORD_1);
        user1.setPermission(PERMISSION_1);
        user1.setId(userService.save(user1));
        user1.setId(userService.save(user1));

        user2 = new User();
        user2.setLogin(LOGIN_2);
        user2.setPassword(PASSWORD_2);
        user2.setPermission(PERMISSION_2);
        user2.setId(userService.save(user2));
        user2.setId(userService.save(user2));

        event1 = new Event();
        event1.setName(EVENT_NAME_1);
        event1.setDescription(DESCRIPTION_1);
        event1.setTheme(themeService.findById(THEME_1.getId()));
        event1.setPictureLink("");
        event1.setAddress(new Address("country", "city", "street", "100a"));
        event1.setDate(new Date(1565625305000L));
        event1.setCapacity(100);
        event1.setDuration(new Date(3600000));
        event1.setAuthor_id(user1.getId());
        event1.setId(eventService.save(event1));

        event2 = new Event();
        event2.setName(EVENT_NAME_2);
        event2.setDescription(DESCRIPTION_2);
        event2.setTheme(themeService.findById(THEME_2.getId()));
        event2.setPictureLink("");
        event2.setAddress(new Address("country", "city", "street", "200"));
        event2.setDate(new Date(1565625305000L));
        event2.setCapacity(200);
        event2.setDuration(new Date(3600000));
        event2.setAuthor_id(user2.getId());
        event2.setId(eventService.save(event2));
    }

    @AfterClass
    public static void cleanUser() throws ServiceException {
        List<User> users = userService.findAll();
        for (User user : users) {
            userService.delete(user.getId());
        }
    }

    @Test
    public void findUserEvents() throws ServiceException {
        Registration registration = new Registration(user1.getId(), event1.getId(), new Value(1));
        service.save(registration);
        registration.setEventId(event2.getId());
        service.save(registration);

        List<Event> expected = new ArrayList<>();
        expected.add(event1);
        expected.add(event2);

        List<Event> actual = service.findUserEvents(user1.getId());

        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findUsersOnEvent() throws ServiceException {
        Registration registration = new Registration(user1.getId(), event1.getId(), new Value(1));
        service.save(registration);
        registration.setUserId(user2.getId());
        service.save(registration);

        List<User> expected = new ArrayList<>();
        expected.add(user1);
        expected.add(user2);

        List<User> actual = service.findUsersOnEvent(event1.getId());

        Assert.assertEquals(expected, actual);
    }


    @Test
    public void readByUserIdAndEventId() throws ServiceException {
        Registration expected = new Registration(user1.getId(), event1.getId(), new Value(1));
        service.save(expected);
        Registration actual = service.readByUserAndEvent(event1.getId(), user1.getId());

        Assert.assertEquals(expected.toString(), actual.toString());
    }

    @Test(expected = ServiceException.class)
    public void failTest() throws ServiceException {
        service.delete(0);
    }

    @Test
    public void findNothing() throws ServiceException {
        List<Event> events = service.findUserEvents(user1.getId());

        Assert.assertTrue(events.isEmpty());
    }

    @After
    public void clean() throws ServiceException {
        List<User> users = userService.findAll();
        for (User user : users) {
            List<Event> userEvents = service.findUserEvents(user.getId());
            for (Event event : userEvents) {
                Registration registration = service.readByUserAndEvent(event.getId(), user.getId());
                service.delete(registration.getId());
            }
        }
    }

}