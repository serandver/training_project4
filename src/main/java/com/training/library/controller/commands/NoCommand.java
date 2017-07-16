package com.training.library.controller.commands;


import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(NoCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return PathManager.getInstance().getProperty(PathManager.START_PAGE);
    }
}
