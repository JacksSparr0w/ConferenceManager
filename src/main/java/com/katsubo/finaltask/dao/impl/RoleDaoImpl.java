package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.RoleDao;
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

public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {

    private static final String READ_ALL = "SELECT `id`, `value` FROM `user_role` ORDER BY `id`";
    private static final String CREATE = "INSERT INTO `user_role` (`value`) VALUE (?)";
    private static final String READ = "SELECT `value` FROM `user_role` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `user_role` SET `value` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `user_role` WHERE `id` = ?";
    private static final Logger logger = LogManager.getLogger(RoleDaoImpl.class);

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
            logger.log(Level.ERROR, "Can't create new role");
            throw new DaoException(e + "Can't create new role");
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
            Value role = null;
            if (resultSet.next()) {
                role = new Value(id);
                role.setId(id);
                role.setValue(resultSet.getString("value"));
            }
            return role;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID role by id" + id);
            throw new DaoException(e + "Can't readByID role by id = " + id);
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
            logger.log(Level.ERROR, "Can't update role");
            throw new DaoException(e + "Can't update role");
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
            logger.log(Level.ERROR, "Can't delete role with id = " + id);
            throw new DaoException(e + "Can't delete role with id = " + id);
        }
    }

    @Override
    public List<Value> read() throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL)) {
            resultSet = statement.executeQuery();
            List<Value> roles = new ArrayList<>();
            Value role = null;
            while (resultSet.next()) {
                role = new Value(resultSet.getInt("id"));
                role.setValue(resultSet.getString("value"));
                roles.add(role);
            }
            return roles;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID all roles");
            throw new DaoException(e + "Can't readByID all roles");
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
