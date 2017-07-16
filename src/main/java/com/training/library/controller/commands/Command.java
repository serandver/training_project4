package com.training.library.controller.commands;

import com.training.library.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;

    /**
     * handles an exception if it occurs in method execute
     * @param request           HttpServletRequest instance from servlet
     * @param e                 exception occurred in method execute
     * @return                  address to JSP page with message about error
     * @throws                  RuntimeException if method not overridden
     */
    default String doOnError(HttpServletRequest request, Exception e) {
        throw new RuntimeException(e);
    }
}
