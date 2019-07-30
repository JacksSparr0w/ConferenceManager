package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.EventDao;
import com.katsubo.finaltask.entity.Address;
import com.katsubo.finaltask.entity.Event;
import com.katsubo.finaltask.entity.enums.Theme;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventDaoImpl extends BaseDaoImpl implements EventDao {
    private static final String READ_ALL = "SELECT `id`, `name`, `description`, `picture_link`, `theme`, `date`, `address`, `author_id`, `capacity` FROM `event_info` ORDER BY `id`";
    private static final String READ_BY_NAME = "SELECT `id`, `description`, `picture_link`, `theme`, `date`, `address`, `author_id`, `capacity` FROM `event_info` WHERE `name` LIKE ? ORDER BY `name`";
    private static final String READ_BY_DATE = "SELECT `id`, `name`, `description`, `picture_link`, `theme`, `address`, `author_id`, `capacity` FROM `event_info` WHERE `date` LIKE ? ORDER BY `date`";
    private static final String READ_BY_THEME = "SELECT `id`, `name`, `description`, `picture_link`, `date`, `address`, `author_id`, `capacity` FROM `event_info` WHERE `theme` LIKE ? ORDER BY `theme`";
    private static final String CREATE = "INSERT INTO `event_info` (`name`, `description`, `picture_link`, `theme`, `date`, `address`, `author_id`, `capacity`) VALUE (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ = "SELECT `name`, `description`, `picture_link`, `theme`, `date`, `address`, `author_id`, `capacity` FROM `event_info` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `event_info` SET `name` = ?, `description` = ?, `picture_link` = ?, `theme` = ?, `date` = ?, `address` = ?, `author_id` = ?, `capacity` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `event_info` WHERE `id` = ?";

    private static final Logger logger = LogManager.getLogger(EventDaoImpl.class);

    @Override
    public List<Event> read() throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL)) {
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setPictureLink(resultSet.getString("picture_link"));
                event.setTheme(Theme.valueOf(resultSet.getString("theme").toUpperCase()));
                event.setDate(resultSet.getDate("date"));
                Address address = new Address(resultSet.getString("address"));
                event.setAddress(address);
                event.setAuthor_id(resultSet.getInt("author_id"));
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
        }
    }

    @Override
    public List<Event> readByName(String search) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_NAME)) {
            statement.setString(1, "%" + search + "%");
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(search);
                event.setDescription(resultSet.getString("description"));
                event.setPictureLink(resultSet.getString("picture_link"));
                event.setTheme(Theme.valueOf(resultSet.getString("theme").toUpperCase()));
                event.setDate(resultSet.getDate("date"));
                Address address = new Address(resultSet.getString("address"));
                event.setAddress(address);
                event.setAuthor_id(resultSet.getInt("author_id"));
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
        }
    }

    @Override
    public List<Event> readByDate(Date search) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_DATE)) {
            statement.setDate(1, new java.sql.Date(search.getTime()));
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setPictureLink(resultSet.getString("picture_link"));
                event.setTheme(Theme.valueOf(resultSet.getString("theme").toUpperCase()));
                event.setDate(search);
                Address address = new Address(resultSet.getString("address"));
                event.setAddress(address);
                event.setAuthor_id(resultSet.getInt("author_id"));
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
        }
    }

    @Override
    public List<Event> readByTheme(Theme search) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_THEME)) {
            statement.setInt(1, search.getFieldCode());
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setPictureLink(resultSet.getString("picture_link"));
                event.setTheme(search);
                event.setDate(resultSet.getDate("date"));
                Address address = new Address(resultSet.getString("address"));
                event.setAddress(address);
                event.setAuthor_id(resultSet.getInt("author_id"));
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
        }
    }

    @Override
    public Integer create(Event entity) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3,entity.getPictureLink());
            statement.setInt(4, entity.getTheme().getFieldCode());
            statement.setDate(5, new java.sql.Date(entity.getDate().getTime()));
            statement.setString(6, entity.getAddress().toString());
            statement.setInt(7, entity.getAuthor_id());
            statement.setInt(8, entity.getCapacity());
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
        }
    }

    @Override
    public Event read(Integer id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Event event = null;
            if (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setPictureLink(resultSet.getString("picture_link"));
                event.setTheme(Theme.valueOf(resultSet.getString("theme").toUpperCase()));
                event.setDate(resultSet.getDate("date"));
                Address address = new Address(resultSet.getString("address"));
                event.setAddress(address);
                event.setAuthor_id(resultSet.getInt("author_id"));
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
        }
    }

    @Override
    public void update(Event entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setString(3,entity.getPictureLink());
            statement.setInt(4, entity.getTheme().getFieldCode());
            statement.setDate(5, new java.sql.Date(entity.getDate().getTime()));
            statement.setString(6, entity.getAddress().toString());
            statement.setInt(7, entity.getAuthor_id());
            statement.setInt(8, entity.getCapacity());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't update event");
            throw new DaoException(e + "Can't update event");
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't delete event");
            throw new DaoException(e + "Can't delete event");
        }
    }
}
