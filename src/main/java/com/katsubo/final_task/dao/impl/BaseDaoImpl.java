package com.katsubo.final_task.dao.impl;

import java.sql.Connection;

abstract public class BaseDaoImpl {
    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
