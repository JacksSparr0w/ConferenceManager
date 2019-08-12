package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.entity.Address;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Permission;
import com.katsubo.finaltask.entity.enums.Role;
import com.katsubo.finaltask.entity.enums.Theme;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import org.junit.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RegistrationServiceImplTest {
    private static final String LOGIN_1 = "test1";
    private static final String DESCRIPTION_1 = "description";
    private static final Permission PERMISSION_1 = Permission.USER;
    private static final String PASSWORD_1 = "pass";
    private static final String EVENT_NAME_1 = "event1";
    private static final Theme THEME_1 = Theme.BUSINESS;
    private static final String DESCRIPTION_2 = "description";
    private static final String LOGIN_2 = "test2";
    private static final Permission PERMISSION_2 = Permission.USER;
    private static final String PASSWORD_2 = "pass";
    private static final String EVENT_NAME_2 = "event1";
    private static final Theme THEME_2 = Theme.BUSINESS;


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
        event1.setTheme(THEME_1);
        event1.setPictureLink("");
        event1.setAddress(new Address("country", "city", "street", 100));
        event1.setDate(new Date(1565625305000L));
        event1.setCapacity(100);
        event1.setAuthor_id(user1.getId());
        event1.setId(eventService.save(event1));

        event2 = new Event();
        event2.setName(EVENT_NAME_2);
        event2.setDescription(DESCRIPTION_2);
        event2.setTheme(THEME_2);
        event2.setPictureLink("");
        event2.setAddress(new Address("country", "city", "street", 200));
        event2.setDate(new Date(1565625305000L));
        event2.setCapacity(200);
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
        Registration registration = new Registration(user1.getId(), event1.getId(), Role.LISTENER);
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
        Registration registration = new Registration(user1.getId(), event1.getId(), Role.LISTENER);
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
        Registration expected = new Registration(user1.getId(), event1.getId(), Role.LISTENER);
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
        for (User user : users){
            List<Event> userEvents = service.findUserEvents(user.getId());
            for (Event event : userEvents){
                Registration registration = service.readByUserAndEvent(event.getId(), user.getId());
                service.delete(registration.getId());
            }
        }
    }

}