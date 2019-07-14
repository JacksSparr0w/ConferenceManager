package com.katsubo.finaltask.dao;

import com.katsubo.finaltask.dao.impl.TransactionImpl;

import java.sql.Connection;

public interface TransactionFactory {
    TransactionImpl getTransaction();

    Connection getConnection();
}
