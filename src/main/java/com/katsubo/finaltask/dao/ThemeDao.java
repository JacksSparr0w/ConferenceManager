package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.entity.Value;

import java.util.List;

public interface ThemeDao extends Dao<Value> {
    List<Value> read() throws DaoException;
}
