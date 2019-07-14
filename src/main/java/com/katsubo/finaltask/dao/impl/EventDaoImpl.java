package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.EventDao;
import com.katsubo.finaltask.entity.Address;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.enums.Role;
import com.katsubo.finaltask.entity.enums.Status;
import com.katsubo.finaltask.entity.enums.Theme;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class EventDaoImpl extends BaseDaoImpl implements EventDao {
    private static final String READ_ALL = "SELECT `id`, `name`, `description`, `theme`, `date`, `address`, `status`, `capacity` FROM `event_info` ORDER BY `id`";
    private static final String READ_USERS_ON_EVENT = "SELECT `user_id`, `user_role` FROM `filling` WHERE `event_id` = ?";
    private static final String READ_BY_NAME = "SELECT `id`, `description`, `theme`, `date`, `address`, `status`, `capacity` FROM `event` WHERE `name` LIKE ? ORDER BY `name`";
    private static final String READ_BY_DATE = "SELECT `id`, `description`, `theme`, `address`, `status`, `capacity` FROM `event` WHERE `date` LIKE ? ORDER BY `date";
    private static final String READ_BY_THEME = "SELECT `id`, `name`, `description`, `date`, `address`, `status`, `capacity` FROM `event` WHERE `theme` LIKE ? ORDER BY `theme`";
    private static final String CREATE = "INSERT INTO `event_info` (`name`, `description`, `theme`, `date`, `address`, `status`, `capacity`) VALUE (?, ?, ?, ?, ?, ?, ?)";
    private static final String READ = "SELECT `name`, `description`, `theme`, `date`, `address`, `status`, `capacity` FROM `event_info` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `event_info` SET `name` = ?, `description` = ?, `theme` = ?, `date` = ?, `address` = ?, `status` = ?, `capacity` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `event_info` WHERE `id` = ?";

    private static final Logger logger = LogManager.getLogger(EventDaoImpl.class);

    @Override
    public List<Event> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_ALL);
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setTheme(Theme.getTheme(resultSet.getInt("theme")));
                event.setDate(resultSet.getDate("date"));
                Address address = new Address(resultSet.getString("address"));   //todo
                event.setAddress(address);
                event.setStatus(Status.valueOf(resultSet.getString("status")));
                event.setCapacity(resultSet.getInt("capacity"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read all events");
            throw new DaoException(e + "Can't read all events");
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public Map<Integer, Role> readUsersIdOnEventByEventId(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_USERS_ON_EVENT);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Map<Integer, Role> usersIdAndRole = new HashMap<>();
            while (resultSet.next()) {
                Integer userId = resultSet.getInt("user_id");
                Role role = Role.valueOf(resultSet.getString("role"));
                usersIdAndRole.put(userId, role);
            }
            return usersIdAndRole;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read users on event");
            throw new DaoException(e + "Can't read users on event");
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<Event> readByName(String search) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_BY_NAME);
            statement.setString(1, "%" + search + "%");
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(search);
                event.setDescription(resultSet.getString("description"));
                event.setTheme(Theme.getTheme(resultSet.getInt("theme")));
                event.setDate(resultSet.getDate("date"));
                Address address = new Address(resultSet.getString("address"));   //todo
                event.setAddress(address);
                event.setStatus(Status.valueOf(resultSet.getString("status")));
                event.setCapacity(resultSet.getInt("capacity"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read event with name = " + search);
            throw new DaoException(e + "Can't read event with name = " + search);
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<Event> readByDate(Date search) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_BY_DATE);
            statement.setDate(1, new java.sql.Date(search.getTime()));
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setTheme(Theme.getTheme(resultSet.getInt("theme")));
                event.setDate(search);
                Address address = new Address(resultSet.getString("address"));   //todo
                event.setAddress(address);
                event.setStatus(Status.valueOf(resultSet.getString("status")));
                event.setCapacity(resultSet.getInt("capacity"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read event with date = " + search);
            throw new DaoException(e + "Can't read event with date = " + search);
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<Event> readByTheme(Theme search) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_BY_THEME);
            statement.setInt(1, search.getFieldCode());
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setTheme(search);
                event.setDate(resultSet.getDate("date"));
                Address address = new Address(resultSet.getString("address"));   //todo
                event.setAddress(address);
                event.setStatus(Status.getStatus(resultSet.getInt("status")));
                event.setCapacity(resultSet.getInt("capacity"));
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read event with theme = " + search);
            throw new DaoException(e + "Can't read event with theme = " + search);
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public Integer create(Event entity) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getTheme().getFieldCode());
            statement.setDate(4, new java.sql.Date(entity.getDate().getTime()));
            statement.setString(5, entity.getAddress().toString());
            statement.setString(6, entity.getStatus().toString());
            statement.setInt(7, entity.getCapacity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't create event");
            throw new DaoException(e + "Can't create event");
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public Event read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Event event = null;
            if (resultSet.next()) {
                event = new Event();
                event.setId(id);
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setTheme(Theme.getTheme(resultSet.getInt("theme")));
                event.setDate(resultSet.getDate("date"));
                Address address = new Address(resultSet.getString("address"));   //todo
                event.setAddress(address);
                event.setStatus(Status.getStatus(resultSet.getInt("status")));
                event.setCapacity(resultSet.getInt("capacity"));
            }
            return event;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read event with id = " + id);
            throw new DaoException(e + "Can't read event with id = " + id);
        } finally {
            try {
                if (resultSet == null) {
                    throw new DaoException();
                }
                resultSet.close();
            } catch (SQLException e) {
            }
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void update(Event entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setInt(3, entity.getTheme().getFieldCode());
            statement.setDate(4, new java.sql.Date(entity.getDate().getTime()));
            statement.setString(5, entity.getAddress().toString());
            statement.setString(6, entity.getStatus().toString());
            statement.setInt(7, entity.getCapacity());
            statement.setInt(8, entity.getId());
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't update event");
            throw new DaoException(e + "Can't update event");
        } finally {
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(DELETE);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't delete event");
            throw new DaoException(e + "Can't delete event");
        } finally {
            try {
                if (statement == null) {
                    throw new DaoException();
                }
                statement.close();
            } catch (SQLException e) {
            }
        }
    }
}
