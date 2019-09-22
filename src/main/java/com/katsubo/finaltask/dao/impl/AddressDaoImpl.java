package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.AddressDao;
import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.entity.Address;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AddressDaoImpl extends BaseDaoImpl implements AddressDao {

    private static final String READ_ALL = "SELECT `id`, `country`, `city`, `street`, `building` FROM `address` ORDER BY `country`, `city`, `street`, `building`";
    private static final String CREATE = "INSERT INTO `address` (`country`, `city`, `street`, `building`) VALUE (?, ?, ?, ?)";
    private static final String READ = "SELECT `country`, `city`, `street`, `building` FROM `address` WHERE `id` = ?";
    private static final String UPDATE = "UPDATE `address` SET `country` = ?, `city` = ?, `street` = ?, `building` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `address` WHERE `id` = ?";
    private static Logger logger = LogManager.getLogger(UserDaoImpl.class);

    /**
     * Create integer.
     *
     * @param entity the entity
     * @return the integer
     * @throws DaoException the dao exception
     */
    @Override
    public Integer create(Address entity) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, entity.getCountry());
            statement.setString(2, entity.getCity());
            statement.setString(3, entity.getStreet());
            statement.setString(4, entity.getBuilding());
            statement.executeUpdate();
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                throw new SQLException();
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't create new address!");
            throw new DaoException(e + "Can't create new address!");
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
    public Address read(Integer id) throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            Address address = null;
            if (resultSet.next()) {
                address = new Address(resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getString("building"));
                address.setId(id);
            }
            return address;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't readByID address by id" + id);
            throw new DaoException(e + "Can't readByID address  by id = " + id);
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
    public void update(Address entity) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, entity.getCountry());
            statement.setString(2, entity.getCity());
            statement.setString(3, entity.getStreet());
            statement.setString(4, entity.getBuilding());
            statement.setInt(5, entity.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can't update user");
            throw new DaoException(e + "Can't update user");
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
            logger.log(Level.ERROR, "Can't delete address with id = " + id);
            throw new DaoException(e + "Can't delete address with id = " + id);
        }
    }

    @Override
    public List<Address> read() throws DaoException {
        ResultSet resultSet = null;
        try (PreparedStatement statement = connection.prepareStatement(READ_ALL)) {
            resultSet = statement.executeQuery();
            List<Address> addresses = new ArrayList<>();
            Address address = null;
            while (resultSet.next()) {
                address = new Address(resultSet.getString("country"),
                        resultSet.getString("city"),
                        resultSet.getString("street"),
                        resultSet.getString("building"));
                address.setId(resultSet.getInt("id"));
                addresses.add(address);
            }
            return addresses;
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Can'r readByID all users");
            throw new DaoException(e + "Can'r readByID all users");
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
