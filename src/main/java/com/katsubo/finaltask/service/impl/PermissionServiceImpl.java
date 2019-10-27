package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.PermissionDao;
import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.service.PermissionService;
import com.katsubo.finaltask.service.ServiceException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Permission service.
 */
public class PermissionServiceImpl extends ServiceImpl implements PermissionService {
    private static final Logger logger = LogManager.getLogger(PermissionServiceImpl.class);

    /**
     * Instantiates a new Permission service.
     *
     * @throws ServiceException the service exception
     */
    public PermissionServiceImpl() throws ServiceException {
    }

    /**
     * Read list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    @Override
    public List<Permission> readAll() throws ServiceException {
        List<Permission> permissions;
        try {
            permissions = transaction.getPermissionDao().read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return permissions;
    }

    /**
     * Save integer.
     *
     * @param permission the permission
     * @return the integer
     * @throws ServiceException the service exception
     */
    @Override
    public Integer save(Permission permission) throws ServiceException {
        Integer id;
        if (permission != null) {
            PermissionDao dao = transaction.getPermissionDao();
            try {
                if (!isExist(permission)) {
                    if (permission.getId() != null) {
                        id = permission.getId();
                        dao.update(permission);
                    } else {
                        id = dao.create(permission);
                    }
                    transaction.commit();
                } else {
                    logger.log(Level.ERROR, "such permission already exist");
                    throw new ServiceException("error.permission.exist");
                }

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
            logger.log(Level.ERROR, "Parameter - PERMISSION is invalid");
            throw new ServiceException("Parameter - PERMISSION is invalid");
        }
    }


    /**
     * Read permission.
     *
     * @param id the id
     * @return the permission
     * @throws ServiceException the service exception
     */
    @Override
    public Permission readById(Integer id) throws ServiceException {
        Permission permission;
        if (id != null) {
            try {
                permission = transaction.getPermissionDao().read(id);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            return permission;
        } else {
            logger.log(Level.ERROR, "Argument - ID is invalid");
            throw new ServiceException("Argument - ID is invalid");
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
                transaction.getPermissionDao().delete(id);
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

    private boolean isExist(Permission permission) throws ServiceException {
        return readAll().contains(permission);
    }
}
