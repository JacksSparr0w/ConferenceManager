package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.PermissionDao;
import com.katsubo.finaltask.entity.Permission;
import com.katsubo.finaltask.entity.Rule;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.katsubo.finaltask.entity.Rule.*;

public class PermissionDaoImpl extends BaseDaoImpl implements PermissionDao {

    private static final String CREATE = "INSERT INTO `permission` (name, modify_any_event, change_user_permission, all_users, delete_user, add_theme, add_role, add_permission) VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ = "SELECT `name`, modify_any_event, change_user_permission, all_users, delete_user, add_theme, add_role, add_permission FROM `permission` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `permission` SET `name` = ?, modify_any_event = ?, change_user_permission = ?, all_users = ?, delete_user = ?, add_theme = ?, add_role = ?, add_permission = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `permission` WHERE `id` = ?";
    private static final Logger logger = LogManager.getLogger(PermissionDaoImpl.class);

    /**
     * Create integer.
     *
     * @param entity the entity
     * @return the integer
     * @throws DaoException the dao exception
     */
    @Override
    public Integer create(Permission entity) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setBoolean(2, entity.checkRule(MODIFY_ANY_EVENT));
            statement.setBoolean(3, entity.checkRule(CHANGE_USER_PERMISSION));
            statement.setBoolean(4, entity.checkRule(ALL_USERS));
            statement.setBoolean(5, entity.checkRule(ADD_THEME));
            statement.setBoolean(6, entity.checkRule(ADD_ROLE));
            statement.setBoolean(7, entity.checkRule(ADD_PERMISSION));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't create new permission");
            throw new DaoException(e + "Can't create new permission");
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
    public Permission read(Integer id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Permission permission = null;
            if (resultSet.next()) {
                permission = new Permission(resultSet.getString("name"));
                for (Rule rule : Rule.values()) {
                    if (resultSet.getBoolean(rule.getValue())) {
                        permission.addRule(rule);
                    }
                }
            }
            return permission;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read permission by id" + id);
            throw new DaoException(e + "Can't read permission by id = " + id);
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

    private void filterRule(Permission permission, Rule rule) {

    }

    /**
     * Update.
     *
     * @param entity the entity
     * @throws DaoException the dao exception
     */
    @Override
    public void update(Permission entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setBoolean(2, entity.checkRule(MODIFY_ANY_EVENT));
            statement.setBoolean(3, entity.checkRule(CHANGE_USER_PERMISSION));
            statement.setBoolean(4, entity.checkRule(ALL_USERS));
            statement.setBoolean(5, entity.checkRule(ADD_THEME));
            statement.setBoolean(6, entity.checkRule(ADD_ROLE));
            statement.setBoolean(7, entity.checkRule(ADD_PERMISSION));
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't update permission");
            throw new DaoException(e + "Can't update permission");
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
            logger.log(Level.ERROR, "Can't delete permission with id = " + id);
            throw new DaoException(e + "Can't delete permission with id = " + id);
        }
    }
}
