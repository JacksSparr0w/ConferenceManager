package com.katsubo.finaltask.dao;

/**
 * The interface Transaction.
 */
public interface Transaction {
    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    UserDao getUserDao();

    /**
     * Gets event dao.
     *
     * @return the event dao
     */
    EventDao getEventDao();

    /**
     * Gets user info dao.
     *
     * @return the user info dao
     */
    UserInfoDao getUserInfoDao();

    /**
     * Gets registration dao.
     *
     * @return the registration dao
     */
    RegistrationDao getRegistrationDao();

    /**
     * Gets address dao.
     *
     * @return the address dao
     */
    AddressDao getAddressDao();

    /**
     * Gets permission dao.
     *
     * @return the permission dao
     */
    PermissionDao getPermissionDao();

    /**
     * Gets theme dao.
     *
     * @return the theme dao
     */
    ThemeDao getThemeDao();

    /**
     * Gets role dao.
     *
     * @return the role dao
     */
    RoleDao getRoleDao();

    /**
     * Commit.
     *
     * @throws DaoException the dao exception
     */
    void commit() throws DaoException;

    /**
     * Rollback.
     *
     * @throws DaoException the dao exception
     */
    void rollback() throws DaoException;
}
