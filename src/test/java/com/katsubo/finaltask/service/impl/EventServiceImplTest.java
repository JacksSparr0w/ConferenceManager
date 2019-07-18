package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.ServiceException;
import org.junit.Test;

public class EventServiceImplTest {

    @Test
    public void findById() throws DaoException, ServiceException {
        EventService service = new EventServiceImpl();
        Event actual = service.findById(1);
        System.out.println(actual);

    }

}