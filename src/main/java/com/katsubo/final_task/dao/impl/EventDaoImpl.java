package com.katsubo.final_task.dao.impl;

import com.katsubo.final_task.dao.EventDao;
import com.katsubo.final_task.entity.Event;
import com.katsubo.final_task.entity.enums.Role;
import com.katsubo.final_task.entity.enums.Status;
import com.katsubo.final_task.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class EventDaoImpl extends BaseDaoImpl implements EventDao {
    private static final Logger logger = LogManager.getLogger(EventDaoImpl.class);

    @Override
    public List<Event> read() throws DaoException {
        return null;
    }

    @Override
    public Map<Integer, Role> readUsersIdOnEventByEventId(Integer id) throws DaoException {
        String sql = "SELECT `filling`, `user_role` FROM `filling` WHERE `event_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
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
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<Event> readByName(String search) throws DaoException {
        String sql = "SELECT `id`, `description`, `date`, `status`, `capacity` FROM `event` WHERE `name` LIKE ? ORDER BY `name`";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, "%" + search + "%");
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(search);
                event.setDescription(resultSet.getString("description"));
                event.setDate(resultSet.getDate("date"));
                event.setStatus(Status.valueOf(resultSet.getString("status")));
                event.setCapacity(resultSet.getInt("capacity"));
                events.add(event);
                //todo create normal enum
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public List<Event> readByDate(Date search) throws DaoException {
        String sql = "SELECT `id`, `description`, `date`, `status`, `capacity` FROM `event` WHERE `date` LIKE ? ORDER BY `date";

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setDate(1, new java.sql.Date(search.getTime()));
            resultSet = statement.executeQuery();
            List<Event> events = new ArrayList<>();
            Event event = null;
            while (resultSet.next()) {
                event = new Event();
                event.setId(resultSet.getInt("id"));
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setDate(search);
                event.setStatus(Status.valueOf(resultSet.getString("status")));
                event.setCapacity(resultSet.getInt("capacity"));
                events.add(event);
                //todo create normal enum
            }
            return events;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Integer create(Event entity) throws DaoException {
        String sql = "INSERT INTO `event_info` (`name`, `description`, `date`, `status`, `capacity`) VALUE (?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, new java.sql.Date(entity.getDate().getTime()));
            statement.setString(4, entity.getStatus().toString());
            statement.setInt(5, entity.getCapacity());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                logger.log(Level.ERROR, "userDao create error!(?)");
                //todo
                throw new DaoException();
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public Event read(Integer id) throws DaoException {
        String sql = "SELECT `name`, `description`, `date`, `status`, `capacity` FROM `event` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Event event = null;
            if (resultSet.next()) {
                event = new Event();
                event.setId(id);
                event.setName(resultSet.getString("name"));
                event.setDescription(resultSet.getString("description"));
                event.setDate(resultSet.getDate("date"));
                event.setStatus(Status.valueOf(resultSet.getString("status")));
                event.setCapacity(resultSet.getInt("capacity"));
                //todo create normal enum
            }
            return event;
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                resultSet.close();
            } catch (SQLException | NullPointerException e) {
            }
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void update(Event entity) throws DaoException {
        String sql = "UPDATE `event_info` SET `name` = ?, `description` = ?, `date` = ?, `status` = ?, `capacity` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1, entity.getName());
            statement.setString(2, entity.getDescription());
            statement.setDate(3, new java.sql.Date(entity.getDate().getTime()));
            statement.setString(4, entity.getStatus().toString());
            statement.setInt(5, entity.getCapacity());
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        String sql = "DELETE FROM `event_info` WHERE `id` = ?";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException | NullPointerException e) {
            }
        }
    }
}
