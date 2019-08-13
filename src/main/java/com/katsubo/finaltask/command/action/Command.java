package com.katsubo.finaltask.command.action;

import com.katsubo.finaltask.command.CommandException;
import com.katsubo.finaltask.command.CommandResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The interface Command.
 */
public interface Command {
    /**
     * Execute command result.
     *
     * @param request  the request
     * @param response the response
     * @return the command result
     * @throws CommandException the command exception
     */
    CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws CommandException;
}
