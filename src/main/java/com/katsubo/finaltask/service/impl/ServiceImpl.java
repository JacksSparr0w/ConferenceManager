package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.Transaction;
import com.katsubo.finaltask.dao.TransactionFactory;
import com.katsubo.finaltask.dao.impl.TransactionFactoryImpl;
import com.katsubo.finaltask.service.Service;
import com.katsubo.finaltask.service.ServiceException;

/**
 * The type Service.
 */
public class ServiceImpl implements Service {
    /**
     * The Transaction.
     */
    Transaction transaction;

    /**
     * Instantiates a new Service.
     *
     * @throws ServiceException the service exception
     */
    public ServiceImpl() throws ServiceException {
        try {
            this.transaction = TransactionFactoryImpl.getInstance().getTransaction();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
