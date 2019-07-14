package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.EventDao;
import com.katsubo.finaltask.dao.UserDao;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Role;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl extends ServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users = null;
        UserDao dao = transaction.getUserDao();
        try {
            users = dao.read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return users;
    }

    @Override
    public User findById(Integer id) throws ServiceException {
        if (id >= 0) {
            User user = null;
            UserDao dao = transaction.getUserDao();
            try {
                user = dao.read(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }

            return user;
        } else {
            logger.log(Level.ERROR, "Parameter - ID is inalid");
            throw new ServiceException("Parameter - ID is invalid");
        }
    }

    @Override
    public Map<Event, Role> findUserEvents(Integer id) throws ServiceException {
        if (id >= 0) {
            Map<Event, Role> events = new HashMap<>();
            UserDao userDao = transaction.getUserDao();
            EventDao eventDao = transaction.getEventDao();
            try {
                Map<Integer, Role> eventsId;
                eventsId = userDao.readAllUserEvents(id);
                for (Map.Entry<Integer, Role> entry : eventsId.entrySet()) {
                    events.put(eventDao.read(entry.getKey()), entry.getValue());
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            return events;
        } else {
            logger.log(Level.ERROR, "Parameter - ID is invalid");
            throw new ServiceException("Parameter - ID is invalid");
        }
    }

    @Override
    public User findByLoginAndPassword(String login, String password) throws ServiceException {
        if (login != null && !login.equals("")) {
            if (password != null && !password.equals("")) {
                User user = null;
                UserDao dao = transaction.getUserDao();
                try {
                    user = dao.read(login, DigestUtils.md5Hex(password));
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
                return user;
            } else {
                logger.log(Level.ERROR, "Parameter - PASSWORD is invalid");
                throw new ServiceException("Parameter - PASSWORD is invalid");
            }
        } else {
            logger.log(Level.ERROR, "Parameter - LOGIN is invalid");
            throw new ServiceException("Parameter - LOGIN is invalid");
        }
    }


    @Override
    public void save(User user) throws ServiceException {
        if (user != null) {
            UserDao dao = transaction.getUserDao();
            user.setPassword(DigestUtils.md5Hex(user.getPassword()));
            try {
                if (user.getId() != null) {
                    dao.update(user);
                } else {
                    dao.create(user);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            logger.log(Level.ERROR, "Parameter - USER is invalid");
            throw new ServiceException("Parameter - USER is invalid");
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        if (id >= 0) {
            UserDao dao = transaction.getUserDao();
            try {
                dao.delete(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            logger.log(Level.ERROR, "Parameter - ID is inalid");
            throw new ServiceException("Parameter - ID is invalid");
        }
    }
}
