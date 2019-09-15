package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.service.RoleService;
import com.katsubo.finaltask.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Theme service.
 */
public class RoleServiceImpl extends ServiceImpl implements RoleService {
    private static final Logger logger = LogManager.getLogger(RoleServiceImpl.class);

    /**
     * Instantiates a new Theme service.
     *
     * @throws ServiceException the service exception
     */
    public RoleServiceImpl() throws ServiceException {

    }

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    @Override
    public List<Value> findAll() throws ServiceException {
        List<Value> roles;
        try {
            roles = transaction.getRoleDao().read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return roles;
    }

    /**
     * Find by id value.
     *
     * @param id the id
     * @return the value
     * @throws ServiceException the service exception
     */
    @Override
    public Value findById(Integer id) throws ServiceException {
        Value value;
        if (id != null) {
            try {
                value = transaction.getRoleDao().read(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            return value;
        } else {
            logger.log(Level.ERROR, "Argument - ID is invalid");
            throw new ServiceException("Argument - ID is invalid");
        }
    }

    /**
     * Save integer.
     *
     * @param role the theme
     * @return the integer
     * @throws ServiceException the service exception
     */
    @Override
    public Integer save(Value role) throws ServiceException {
        if (role != null) {
            Integer id = null;
            try {
                if (!isExist(role)){
                    id = transaction.getRoleDao().create(role);
                    transaction.commit();
                } else {
                    logger.log(Level.ERROR, "such role already exist");
                    throw new ServiceException("error.role.exist");
                }
            } catch (DaoException e) {
                try {
                    transaction.rollback();
                } catch (DaoException e1) {
                    throw new ServiceException(e1);
                }
            }
            return id;
        } else {
            logger.log(Level.ERROR, "Argument - ROLE is invalid");
            throw new ServiceException("Argument - ROLE is invalid");
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws ServiceException the service exception
     */
    @Override
    public void delete(Integer id) throws ServiceException {
        if (id != null) {
            try {
                transaction.getRoleDao().delete(id);
                transaction.commit();
            } catch (DaoException e) {
                try {
                    transaction.rollback();
                } catch (DaoException e1) {
                    throw new ServiceException(e1);
                }
            }
        } else {
            logger.log(Level.ERROR, "Argument - ID is invalid");
            throw new ServiceException("Argument - ID is invalid");
        }
    }

    private boolean isExist(Value role) throws ServiceException{
        return findAll().contains(role);
    }
}
