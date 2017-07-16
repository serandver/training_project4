package com.training.library.controller.commands;


import com.training.library.controller.commands.Command;
import com.training.library.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class NoCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    private static final String START_PAGE ="/index.jsp";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return START_PAGE;
    }
}
