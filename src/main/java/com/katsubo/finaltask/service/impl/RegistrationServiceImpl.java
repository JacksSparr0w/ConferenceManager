package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.EventDao;
import com.katsubo.finaltask.dao.RegistrationDao;
import com.katsubo.finaltask.dao.UserDao;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.Registration;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.service.RegistrationService;
import com.katsubo.finaltask.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class RegistrationServiceImpl extends ServiceImpl implements RegistrationService {
    private static final Logger logger = LogManager.getLogger(RegistrationServiceImpl.class);

    public RegistrationServiceImpl() throws DaoException {
    }

    @Override
    public List<User> findUsersOnEvent(Integer eventId) throws ServiceException {
        if (eventId >= 0) {
            List<Registration> registrations = null;
            List<User> users = new ArrayList<>();
            RegistrationDao registrationDao = transaction.getRegistrationDao();
            UserDao userDao = transaction.getUserDao();
            try {
                registrations = registrationDao.readUsersOnEvent(eventId);
                for (Registration registration : registrations){
                    User user = userDao.read(registration.getUserId());
                    users.add(user);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }

            return users;
        } else {
            logger.log(Level.ERROR, "Parameter - EVENT_ID is inalid");
            throw new ServiceException("Parameter - EVENT_ID is invalid");
        }
    }

    @Override
    public List<Event> findUserEvents(Integer userId) throws ServiceException {
        if (userId >= 0) {
            List<Registration> registrations = null;
            List<Event> events = new ArrayList<>();
            RegistrationDao registrationDao = transaction.getRegistrationDao();
            EventDao eventDao = transaction.getEventDao();
            try {
                registrations = registrationDao.readUserEvents(userId);
                for (Registration registration : registrations){
                    Event event = eventDao.read(registration.getEventId());
                    events.add(event);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }

            return events;
        } else {
            logger.log(Level.ERROR, "Parameter - USER_ID is inalid");
            throw new ServiceException("Parameter - USER_ID is invalid");
        }
    }

    @Override
    public Registration read(Integer id) throws ServiceException {
        if (id >= 0) {
            Registration registration = null;
            RegistrationDao dao = transaction.getRegistrationDao();
            try {
                registration = dao.read(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }

            return registration;
        } else {
            logger.log(Level.ERROR, "Parameter - ID is inalid");
            throw new ServiceException("Parameter - ID is invalid");
        }

    }

    @Override
    public Integer save(Registration registration) throws ServiceException {
        Integer id;
        if (registration != null) {
            RegistrationDao dao = transaction.getRegistrationDao();
            try {
                if (registration.getId() != null) {
                    id = registration.getId();
                    dao.update(registration);
                } else {
                    id = dao.create(registration);
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

            return id;
        } else {
            logger.log(Level.ERROR, "Parameter - REGISTRATION is invalid");
            throw new ServiceException("Parameter - REGISTRATION is invalid");
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        if (id >= 0) {
            RegistrationDao dao = transaction.getRegistrationDao();
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

    @Override
    public Registration readByUserAndEvent(Integer eventId, Integer userId) throws ServiceException {
        if (eventId >= 0 && userId >= 0){
            Registration registration = null;
            RegistrationDao dao = transaction.getRegistrationDao();
            try {
                registration = dao.read(eventId, userId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }

            return registration;
        } else {
            logger.log(Level.ERROR, "Parameter - userId or eventId is inalid");
            throw new ServiceException("Parameter - userId or eventId is invalid");
        }
    }
}
