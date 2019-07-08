package com.katsubo.final_task.dao.impl;

import com.katsubo.final_task.dao.UserInfoDao;
import com.katsubo.final_task.entity.User;
import com.katsubo.final_task.entity.UserInfo;
import com.katsubo.final_task.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {
    private static final Logger logger = LogManager.getLogger(UserInfoDaoImpl.class);
    @Override
    public UserInfo read(User user) throws DaoException {
        String sql = "SELECT `id`, `name`, `surname`, `about`, `picture_link`, `email`, `date_of_birth`, " +
                "`date_of_registration` FROM `users` WHERE `user_id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
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
            }
            return info;
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
    public Integer create(UserInfo entity) throws DaoException {
        String sql = "INSERT INTO `user_info` (`user_id`, `name`, `surname`, `about`, `picture_link`, `date_of_birth`, \" +\n" +
                "                \"`date_of_registration`) VALUE (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, entity.getUser().getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSurname());
            statement.setString(4, entity.getAbout());
            statement.setString(5, entity.getPictureLink());
            statement.setDate(6, new Date(entity.getDateOfBirth().getTime()));
            statement.setDate(7, new Date(entity.getDateOfRegistration().getTime()));
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
    public UserInfo read(Integer id) throws DaoException {
        String sql = "SELECT `user_id`, `name`, `surname`, `about`, `picture_link`, `email`, `date_of_birth`, " +
                "`date_of_registration` FROM `users` WHERE `id` = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement(sql);
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
            }
            return info;
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
    public void update(UserInfo entity) throws DaoException {
        String sql = "UPDATE `user_info` SET `user_id` = ?, `name` = ?, `surname` = ?, `about` = ?, `picture_link` = ?, " +
                "`email` = ?, `date_of_birth` = ?, `date_of_registration` = ?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setInt(1, entity.getUser().getId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getSurname());
            statement.setString(4, entity.getAbout());
            statement.setString(5, entity.getPictureLink());
            statement.setString(6, entity.getEmail());
            statement.setDate(7, new Date(entity.getDateOfBirth().getTime()));
            statement.setDate(8, new Date(entity.getDateOfRegistration().getTime()));
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
        String sql = "DELETE FROM `user_info` WHERE `id` = ?";
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
