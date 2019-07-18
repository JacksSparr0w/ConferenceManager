package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.UserDao;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.enums.Permission;
import com.katsubo.finaltask.entity.enums.Role;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final String READ_BY_LOGIN_AND_PASSWORD = "SELECT `id`, `login`, `password`, `permission` FROM `user` WHERE `login` = ? AND `password` = ?";
    private static final String READ_ALL = "SELECT `login`, `password`, `permission` FROM `user` ORDER BY `login`";
    private static final String READ_USER_EVENTS = "SELECT `event_id`, `user_role` FROM `filling` WHERE `user_id` = ?";
    private static final String CREATE = "INSERT INTO `user` (`login`, `password`, `permission`) VALUE (?, ?, ?)";
    private static final String READ = "SELECT `login`, `password`, `permission` FROM `user` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `user` SET `login` = ?, `password` = ?, `permission` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `user` WHERE `id` = ?";
    public static final String FIND_BY_LOGIN = "SELECT `id`, `login` FROM `user` WHERE `login` = ?";

    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public User read(String login, String password) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_BY_LOGIN_AND_PASSWORD);
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                String string = resultSet.getString("permission");
                Permission permission = Permission.valueOf(string.toUpperCase());
                user.setPermission(permission);
            }
            return user;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read user by this login = " + login + "and password = " + password);
            throw new DaoException(e + "Can't read user by this login = " + login + "and password = " + password);
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
    public Integer find(String login) throws DaoException {
        Integer id = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(FIND_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            return id;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read user by this login = " + login);
            throw new DaoException(e + "Can't read user by this login = " + login);
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
    public List<User> read() throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_ALL);
            resultSet = statement.executeQuery();
            List<User> users = new ArrayList<>();
            User user = null;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getInt("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setPermission(Permission.getPermission(resultSet.getInt("permission")));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can'r read all users");
            throw new DaoException(e + "Can'r read all users");
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
    public Map<Integer, Role> readAllUserEvents(Integer userId) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ_USER_EVENTS);
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            Map<Integer, Role> eventsIdAndRole = new HashMap<>();
            while (resultSet.next()) {
                Integer eventId = resultSet.getInt("event_id");
                Role role = Role.valueOf(resultSet.getString("role"));
                eventsIdAndRole.put(eventId, role);
            }
            return eventsIdAndRole;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read all user events");
            throw new DaoException(e + "Can't read all user events");
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
    public Integer create(User entity) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getPermission().getFieldCode());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't create new user!");
            throw new DaoException(e + "Can't create new user!");
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
    public User read(Integer id) throws DaoException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(READ);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = new User();
                user.setId(id);
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setPermission(Permission.getPermission(resultSet.getInt("permission")));
            }
            return user;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read user by id" + id);
            throw new DaoException(e + "Can't read user by id = " + id);
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
    public void update(User entity) throws DaoException {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(UPDATE);
            statement.setString(1, entity.getLogin());
            statement.setString(2, entity.getPassword());
            statement.setInt(3, entity.getPermission().getFieldCode());
            statement.setInt(4, entity.getId());
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't update user");
            throw new DaoException(e + "Can't update user");
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
            logger.log(Level.ERROR, "Can't delete user id = " + id);
            throw new DaoException(e + "Can't delete user id = " + id);
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
