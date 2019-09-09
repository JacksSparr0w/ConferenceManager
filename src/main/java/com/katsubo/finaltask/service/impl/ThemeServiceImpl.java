package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Value;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.ThemeService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The type Theme service.
 */
public class ThemeServiceImpl extends ServiceImpl implements ThemeService {
    private static final Logger logger = LogManager.getLogger(ThemeServiceImpl.class);

    /**
     * Instantiates a new Theme service.
     *
     * @throws ServiceException the service exception
     */
    public ThemeServiceImpl() throws ServiceException {

    }

    /**
     * Find all list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    @Override
    public List<Value> findAll() throws ServiceException {
        List<Value> themes;
        try {
            themes = transaction.getThemeDao().read();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }

        return themes;
    }

    /**
     * Save integer.
     *
     * @param theme the theme
     * @return the integer
     * @throws ServiceException the service exception
     */
    @Override
    public Integer save(Value theme) throws ServiceException {
        if (theme != null) {
            Integer id;
            try {
                id = transaction.getThemeDao().create(theme);
                transaction.commit();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
            return id;
        } else {
            logger.log(Level.ERROR, "Argument - THEME is invalid");
            throw new ServiceException("Argument - THEME is invalid");
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
                transaction.getThemeDao().delete(id);
                transaction.commit();
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        } else {
            logger.log(Level.ERROR, "Argument - ID is invalid");
            throw new ServiceException("Argument - ID is invalid");
        }
    }
}
