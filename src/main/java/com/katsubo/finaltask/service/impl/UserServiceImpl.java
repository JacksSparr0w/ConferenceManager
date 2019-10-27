package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.UserDao;
import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.service.PermissionService;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type User service.
 */
public class UserServiceImpl extends ServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

    /**
     * Instantiates a new User service.
     *
     * @throws ServiceException the service exception
     */
    public UserServiceImpl() throws ServiceException {
    }


    @Override
    public List<User> findAll() throws ServiceException {
        List<User> users = null;
        UserDao dao = transaction.getUserDao();
        try {
            users = dao.read();
            readPermission(users);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return users;
    }


    @Override
    public User findById(Integer id) throws ServiceException {
        if (id != null && id > 0) {
            User user = null;
            UserDao dao = transaction.getUserDao();
            try {
                user = dao.read(id);
                if (user != null){
                    readPermission(user);
                }
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
    public boolean isExist(String login) throws ServiceException {
        Integer id;
        if (login != null) {
            UserDao dao = transaction.getUserDao();
            try {
                id = dao.find(login);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            return id != null;
        } else {
            logger.log(Level.ERROR, "Parameter - LOGIN is inalid");
            throw new ServiceException("Parameter - LOGIN is invalid");
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
                    if (user != null) {
                        readPermission(user);
                    } else {
                        throw new ServiceException("user.find.error");
                    }
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
    public Integer save(User user) throws ServiceException {
        Integer id;
        if (user != null) {
            UserDao dao = transaction.getUserDao();
            try {
                if (user.getId() != null) {
                    id = user.getId();
                    if (user.getPassword() == null) {
                        user.setPassword(dao.read(user.getId()).getPassword());
                    }
                    dao.update(user);
                } else {
                    id = dao.create(user);
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
            logger.log(Level.ERROR, "Parameter - USER is invalid");
            throw new ServiceException("Parameter - USER is invalid");
        }
    }

    @Override
    public void delete(Integer id) throws ServiceException {
        if (id != null && id > 0) {
            UserDao dao = transaction.getUserDao();
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

    private void readPermission(List<User> users) throws ServiceException {
        for (User user : users) {
            readPermission(user);
        }
    }

    private void readPermission(User user) throws ServiceException {
        PermissionService service = new PermissionServiceImpl();
        Permission permission = service.readById(user.getPermission().getId());
        user.setPermission(permission);

    }
}
