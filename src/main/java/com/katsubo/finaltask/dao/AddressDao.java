package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Address;

import java.util.List;

public interface AddressDao extends Dao<Address> {
    List<Address> read() throws DaoException;
}
