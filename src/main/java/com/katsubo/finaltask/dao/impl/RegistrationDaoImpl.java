package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.RegistrationDao;
import com.katsubo.finaltask.entity.Registration;
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

/**
 * The type Registration dao.
 */
public class RegistrationDaoImpl extends BaseDaoImpl implements RegistrationDao {

    private static final String READ_USERS_ON_EVENT = "SELECT `id`, `user_id`, `user_role` FROM `registrations` WHERE `event_id` = ?";
    private static final String READ_USER_EVENTS = "SELECT `id`, `event_id`, `user_role` FROM `registrations` WHERE `user_id` = ?";
    private static final String CREATE = "INSERT INTO `registrations` (`user_id`, `event_id`, `user_role`) VALUE (?, ?, ?)";
    private static final String READ = "SELECT `user_id`, `event_id`, `user_role` FROM `registrations` WHERE `id` = ?";
    private static final String READ_BY_USER_AND_EVENT = "SELECT `id`, `user_role` FROM `registrations` WHERE `event_id` = ? AND `user_id` = ?";
    private static final String UPDATE = "UPDATE `registrations` SET `user_id` = ?, `event_id` = ?, `user_role` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `registrations` WHERE `id` = ?";
    private static final Logger logger = LogManager.getLogger(RegistrationDaoImpl.class);


    @Override
    public List<Registration> readUsersOnEvent(Integer eventId) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_USERS_ON_EVENT)) {
            statement.setInt(1, eventId);
            resultSet = statement.executeQuery();
            List<Registration> registrations = new ArrayList<>();
            while (resultSet.next()) {
                Integer userId = resultSet.getInt("user_id");
                Value role = new Value(resultSet.getInt("user_role"));
                Registration registration = new Registration(userId, eventId, role);
                registrations.add(registration);
            }
            return registrations;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID users on event");
            throw new DaoException(e + "Can't readByID users on event");
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

    @Override
    public List<Registration> readUserEvents(Integer userId) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_USER_EVENTS)) {
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            List<Registration> registrations = new ArrayList<>();
            while (resultSet.next()) {
                Integer eventId = resultSet.getInt("event_id");
                Value role = new Value(resultSet.getInt("user_role"));
                Registration registration = new Registration(userId, eventId, role);
                registration.setId(resultSet.getInt("id"));

                registrations.add(registration);
            }
            return registrations;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID all user events");
            throw new DaoException(e + "Can't readByID all user events");
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

    @Override
    public Registration read(Integer eventId, Integer userId) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_USER_AND_EVENT)) {
            statement.setInt(1, eventId);
            statement.setInt(2, userId);
            resultSet = statement.executeQuery();
            Registration registration = null;
            if (resultSet.next()) {
                Value role = new Value(resultSet.getInt("user_role"));
                registration = new Registration(userId, eventId, role);
                registration.setId(resultSet.getInt("id"));
            }
            return registration;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID registration by user_id and event_id");
            throw new DaoException(e + "Can't readByID registration by user_id and event_id");
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

    @Override
    public Integer create(Registration entity) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getEventId());
            statement.setInt(3, entity.getRole().getId());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't create new registration!");
            throw new DaoException(e + "Can't create new registration!");
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


    @Override
    public Registration read(Integer id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Registration registration = null;
            if (resultSet.next()) {
                Integer eventId = resultSet.getInt("event_id");
                Integer userId = resultSet.getInt("user_id");
                Value role = new Value(resultSet.getInt("user_role"));
                registration = new Registration(userId, eventId, role);
                registration.setId(resultSet.getInt("id"));
            }
            return registration;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID registration by id" + id);
            throw new DaoException(e + "Can't readByID registration by id = " + id);
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

    @Override
    public void update(Registration entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, entity.getUserId());
            statement.setInt(2, entity.getEventId());
            statement.setInt(3, entity.getRole().getId());
            statement.setInt(4, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't update registration");
            throw new DaoException(e + "Can't update registration");
        }
    }


    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't delete registration id = " + id);
            throw new DaoException(e + "Can't delete registration id = " + id);
        }
    }
}
