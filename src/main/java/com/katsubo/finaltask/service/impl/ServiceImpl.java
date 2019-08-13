package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.Transaction;
import com.katsubo.finaltask.dao.TransactionFactory;
import com.katsubo.finaltask.dao.impl.TransactionFactoryImpl;
import com.katsubo.finaltask.service.Service;
import com.katsubo.finaltask.service.ServiceException;

public class ServiceImpl implements Service {
    Transaction transaction;

    public ServiceImpl() throws ServiceException {
        try {
            this.transaction = TransactionFactoryImpl.getInstance().getTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
