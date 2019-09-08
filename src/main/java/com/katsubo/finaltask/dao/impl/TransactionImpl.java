package com.katsubo.finaltask.dao.impl;

import com.katsubo.finaltask.dao.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * The type Transaction.
 */
public class TransactionImpl implements Transaction {
    private static final Logger logger = LogManager.getLogger(TransactionImpl.class);

    /**
     * The Connection.
     */
    Connection connection;

    /**
     * Instantiates a new Transaction.
     *
     * @param connection the connection
     */
    TransactionImpl(Connection connection){
        this.connection = connection;

    }

    @Override
    public UserDao getUserDao() {
        UserDao dao = new UserDaoImpl();
        return (UserDao) setConnection(dao);
    }

    @Override
    public EventDao getEventDao() {
        EventDao dao = new EventDaoImpl();
        return (EventDao) setConnection(dao);
    }

    @Override
    public UserInfoDao getUserInfoDao() {
        UserInfoDao dao = new UserInfoDaoImpl();
        return (UserInfoDao) setConnection(dao);
    }

    @Override
    public RegistrationDao getRegistrationDao() {
        RegistrationDao dao = new RegistrationDaoImpl();
        return (RegistrationDao) setConnection(dao);
    }

    /**
     * Gets address dao.
     *
     * @return the address dao
     */
    @Override
    public AddressDao getAddressDao() {
        AddressDao dao = new AddressDaoImpl();
        return (AddressDao) setConnection(dao);
    }

    /**
     * Gets permission dao.
     *
     * @return the permission dao
     */
    @Override
    public PermissionDao getPermissionDao() {
        PermissionDao dao = new PermissionDaoImpl();
        return (PermissionDao) setConnection(dao);
    }

    /**
     * Gets theme dao.
     *
     * @return the theme dao
     */
    @Override
    public ThemeDao getThemeDao() {
        ThemeDao dao = new ThemeDaoImpl();
        return (ThemeDao) setConnection(dao);
    }

    /**
     * Gets role dao.
     *
     * @return the role dao
     */
    @Override
    public RoleDao getRoleDao() {
        RoleDao dao = new RoleDaoImpl();
        return (RoleDao) setConnection(dao);
    }

    private Dao setConnection(Dao dao) {
        ((BaseDaoImpl) dao).setConnection(connection);
        return dao;
    }

    @Override
    public void commit() throws DaoException {
        try{
            connection.commit();
        } catch (SQLException e){
            logger.log(Level.ERROR, "Error while commit transaction", e);
            throw new DaoException();
        }
    }

    @Override
    public void rollback() throws DaoException {
        try{
            connection.rollback();
        } catch (SQLException e){
            logger.log(Level.ERROR, "Error while rollback transaction", e);
            throw new DaoException();
        }
    }
}
