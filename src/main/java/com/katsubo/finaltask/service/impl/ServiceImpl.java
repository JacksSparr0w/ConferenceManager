package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.Transaction;
import com.katsubo.finaltask.dao.TransactionFactory;
import com.katsubo.finaltask.dao.impl.TransactionFactoryImpl;
import com.katsubo.finaltask.service.Service;

public class ServiceImpl implements Service {
    Transaction transaction;

    ServiceImpl() throws DaoException {
        this.transaction = TransactionFactoryImpl.getInstance().getTransaction();
    }

}
