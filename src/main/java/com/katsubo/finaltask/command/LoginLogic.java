package com.katsubo.finaltask.command;

import com.katsubo.finaltask.dao.DaoException;
import com.katsubo.finaltask.dao.TransactionFactory;
import com.katsubo.finaltask.dao.impl.TransactionFactoryImpl;
import com.katsubo.finaltask.entity.User;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.UserService;
import com.katsubo.finaltask.service.impl.UserServiceImpl;

public class LoginLogic {
    public static boolean checkLogin(String enterLogin, String enterPass) {
        User user = null;
        try {
            TransactionFactory factory = new TransactionFactoryImpl();
            UserService service = new UserServiceImpl();
            ((UserServiceImpl) service).setTransaction(factory.getTransaction());
            user = service.findByLoginAndPassword(enterLogin, enterPass);

        } catch (DaoException | ServiceException e) {

        }
        if (user == null) return false;
        return user.getId() != null;
    }
}
