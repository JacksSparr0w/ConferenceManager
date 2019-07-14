package com.katsubo.finaltask.service.impl;

import com.katsubo.finaltask.dao.Transaction;
import com.katsubo.finaltask.service.Service;

public class ServiceImpl implements Service {
    Transaction transaction;

    public void setTransaction(Transaction transaction){
        this.transaction = transaction;
    }

}
