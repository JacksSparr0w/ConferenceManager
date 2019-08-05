package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.EventDao;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.service.EventService;
import com.katsubo.finaltask.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class EventServiceImpl extends ServiceImpl implements EventService {
    private static final Logger logger = LogManager.getLogger(EventServiceImpl.class);

    public EventServiceImpl() throws ServiceException {
    }

    @Override
    public List<Event> findAll() throws ServiceException {
        List<Event> events = null;
        EventDao dao = transaction.getEventDao();
        try {
            events = dao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return events;
    }

    @Override
    public Event findById(Integer id) throws ServiceException {
        if (id >= 0) {
            Event event = null;
            EventDao dao = transaction.getEventDao();
            try {
                event = dao.read(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }

            return event;
        } else {
            logger.log(Level.ERROR, "Parameter - ID is inalid");
            throw new ServiceException("Parameter - ID is invalid");
        }
    }

    @Override
    public void save(Event event) throws ServiceException {
        if (event != null) {
            EventDao dao = transaction.getEventDao();
            try {
                if (event.getId() != null) {
                    dao.update(event);
                } else {
                    dao.create(event);
                }
                transaction.commit();
            } catch (DaoException e) {
                try {
                    transaction.rollback();
                } catch (DaoException e1) {
                    throw new ServiceException(e1);
                }
                throw new ServiceException(e);
            }
        } else {
            logger.log(Level.ERROR, "Parameter - EVENT is invalid");
            throw new ServiceException("Parameter - EVENT is invalid");
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        if (id >= 0) {
            EventDao dao = transaction.getEventDao();
            try {
                dao.delete(id);
                transaction.commit();
            } catch (DaoException e) {
                try {
                    transaction.rollback();
                } catch (DaoException e1) {
                    throw new ServiceException(e1);
                }
                throw new ServiceException(e);
            }
        } else {
            logger.log(Level.ERROR, "Parameter - ID is inalid");
            throw new ServiceException("Parameter - ID is invalid");
        }
    }
}
