package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.Transaction;
import com.katsubo.finaltask.dao.TransactionFactory;
import com.katsubo.finaltask.dao.impl.TransactionFactoryImpl;
import com.katsubo.finaltask.service.Service;

public class ServiceImpl implements Service {
    Transaction transaction;

    /*public void setTransaction(Transaction transaction){
        this.transaction = transaction;
    }*/

    ServiceImpl() throws DaoException {
        TransactionFactory factory = new TransactionFactoryImpl();
        this.transaction = factory.getTransaction();
    }

}
