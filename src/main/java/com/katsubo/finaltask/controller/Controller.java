package com.katsubo.finaltask.controller;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;
import com.katsubo.finaltask.command.factory.CommandType;
import com.katsubo.finaltask.command.factory.RequestType;
import com.katsubo.finaltask.service.ServiceException;
import com.katsubo.finaltask.service.impl.ServiceImpl;
import com.katsubo.finaltask.util.ResourceManager;
import com.katsubo.finaltask.command.action.Command;
import com.katsubo.finaltask.command.factory.CommandFactory;
import com.katsubo.finaltask.connection.ConnectionPool;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The type Controller.
 */
@WebServlet("/controller")
@MultipartConfig
public class Controller extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(Controller.class);
    private static final String COMMAND = "command";
    private static final String ERROR = "error";

    @Override
    public void destroy() {
        ConnectionPool.getInstance().destroy();
    }

    @Override
    public void init(){
            try {
                new ServiceImpl();
            } catch (ServiceException e) {
                logger.log(Level.WARN, "ops, optimize fail");
            }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, RequestType.GET);
        }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response, RequestType.POST);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, RequestType requestType) throws ServletException, IOException {
        String command = request.getParameter(COMMAND);
        CommandType commandType = CommandType.of(command);
        Command action = CommandFactory.create(commandType);

        CommandResult result;
        try {
            if (commandType.getRequestType() != requestType){
                throw new CommandException("Invalid type of request!");
            }
            result = action.execute(request, response);
        } catch (CommandException e) {
            logger.log(Level.ERROR, e.getMessage(), e);
            request.setAttribute(ERROR, e.getMessage());
            result = new CommandResult(ResourceManager.getProperty("page.error404"));
        }

        String page = result.getPage();
        if (result.isRedirect()) {
            redirect(response, page);
        } else {
            forward(request, response, page);
        }
    }

    private void forward(HttpServletRequest request, HttpServletResponse response, String page) throws ServletException, IOException {
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/" + page);
        dispatcher.forward(request, response);
    }

    private void redirect(HttpServletResponse response, String page) throws IOException {
        response.sendRedirect(page);

    }
}
