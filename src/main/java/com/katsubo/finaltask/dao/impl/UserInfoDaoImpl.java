package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.UserInfoDao;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.entity.UserInfo;
import com.katsubo.finaltask.entity.enums.Gender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {
    public static final String READ_BY_USER = "SELECT `id`, `name`, `surname`, `about`, `picture_link`, `email`, `date_of_birth`, `date_of_registration`, `gender` FROM `user_info` WHERE `user_id` = ?";
    public static final String CREATE = "INSERT INTO `user_info` (`user_id`, `name`, `surname`, `email`, `date_of_registration`) VALUE (?, ?, ?, ?, ?)";
    public static final String READ = "SELECT `user_id`, `name`, `surname`, `about`, `picture_link`, `email`, `date_of_birth`, `date_of_registration`, `gender` FROM `user_info` WHERE `id` = ?";
    public static final String UPDATE = "UPDATE `user_info` SET `user_id` = ?, `name` = ?, `surname` = ?, `about` = ?, `picture_link` = ?, `email` = ?, `date_of_birth` = ?, `gender` = ? WHERE `id` = ?";
    public static final String DELETE = "DELETE FROM `user_info` WHERE `id` = ?";
    private static final Logger logger = LogManager.getLogger(UserInfoDaoImpl.class);

    @Override
    public UserInfo read(User user) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_BY_USER)) {
            statement.setInt(1, user.getId());
            resultSet = statement.executeQuery();
            UserInfo info = null;
            if (resultSet.next()) {
                info = new UserInfo();
                info.setId(resultSet.getInt("id"));
                info.setUser(user);
                info.setName(resultSet.getString("name"));
                info.setSurname(resultSet.getString("surname"));
                info.setAbout(resultSet.getString("about"));
                info.setPictureLink(resultSet.getString("picture_link"));
                info.setEmail(resultSet.getString("email"));
                info.setDateOfBirth(resultSet.getDate("date_of_birth"));
                info.setDateOfRegistration(resultSet.getDate("date_of_registration"));
                info.setGender(Gender.valueOf(resultSet.getString("gender").toUpperCase()));
            }
            return info;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read userInfo");
            throw new DaoException(e + "Can't read userInfo");
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
    public Integer create(UserInfo entity) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, entity.getUser().getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSurname());
            statement.setString(4, entity.getEmail());
            statement.setDate(5, new Date(entity.getDateOfRegistration().getTime()));
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't create userInfo");
            throw new DaoException(e + "Can't create userInfo");
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
    public UserInfo read(Integer id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            UserInfo info = null;
            if (resultSet.next()) {
                info = new UserInfo();
                info.setId(id);
                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                info.setUser(user);
                info.setName(resultSet.getString("name"));
                info.setSurname(resultSet.getString("surname"));
                info.setAbout(resultSet.getString("about"));
                info.setPictureLink(resultSet.getString("picture_link"));
                info.setEmail(resultSet.getString("email"));
                info.setDateOfBirth(resultSet.getDate("date_of_birth"));
                info.setDateOfRegistration(resultSet.getDate("date_of_registration"));
                info.setGender(Gender.valueOf(resultSet.getString("gender").toUpperCase()));
            }
            return info;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't read userInfo with id = " + id);
            throw new DaoException(e + "Can't read userInfo with id = " + id);
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
    public void update(UserInfo entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setInt(1, entity.getUser().getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSurname());
            statement.setString(4, entity.getAbout());
            statement.setString(5, entity.getPictureLink());
            statement.setString(6, entity.getEmail());
            statement.setDate(7, new Date(entity.getDateOfBirth().getTime()));
            statement.setInt(8, entity.getGender().getFieldCode());
            statement.setInt(9, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't update userInfo");
            throw new DaoException(e + "Can't update userInfo");
        }
    }

    @Override
    public void delete(Integer id) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't delete userInfo");
            throw new DaoException(e + "Can't update userInfo");
        }
    }
}
