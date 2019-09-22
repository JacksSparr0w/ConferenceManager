package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.ThemeDao;
import com.katsubo.finaltask.entity.Value;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ThemeDaoImpl extends BaseDaoImpl implements ThemeDao {

    private static final String READ_ALL = "SELECT `id`, `value` FROM `theme` ORDER BY `id`";
    private static final String CREATE = "INSERT INTO `theme` (`value`) VALUE (?)";
    private static final String READ = "SELECT `value` FROM `theme` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `theme` SET `value` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `theme` WHERE `id` = ?";
    private static final Logger logger = LogManager.getLogger(ThemeDaoImpl.class);

    /**
     * Create integer.
     *
     * @param entity the entity
     * @return the integer
     * @throws DaoException the dao exception
     */
    @Override
    public Integer create(Value entity) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getValue());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't create new theme");
            throw new DaoException(e + "Can't create new theme");
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Read type.
     *
     * @param id the id
     * @return the type
     * @throws DaoException the dao exception
     */
    @Override
    public Value read(Integer id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Value theme = null;
            if (resultSet.next()) {
                theme = new Value(id);
                theme.setValue(resultSet.getString("value"));
            }
            return theme;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID theme by id" + id);
            throw new DaoException(e + "Can't readByID theme by id = " + id);
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
        }
    }

    /**
     * Update.
     *
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    @Override
    public void update(Value entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getValue());
            statement.setInt(2, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't update theme");
            throw new DaoException(e + "Can't update theme");
        }
    }

    /**
     * Delete.
     *
     * @param id the id
     * @throws DaoException the dao exception
     */
    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't delete theme with id = " + id);
            throw new DaoException(e + "Can't delete theme with id = " + id);
        }
    }

    @Override
    public List<Value> read() throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL)) {
            resultSet = statement.executeQuery();
            List<Value> themes = new ArrayList<>();
            Value theme = null;
            while (resultSet.next()) {
                theme = new Value(resultSet.getInt("id"));
                theme.setValue(resultSet.getString("value"));
                themes.add(theme);
            }
            return themes;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID all themes");
            throw new DaoException(e + "Can't readByID all themes");
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
        }
    }
}
