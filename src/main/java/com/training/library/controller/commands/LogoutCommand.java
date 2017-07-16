package com.training.library.controller.commands;


import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(LogoutCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(false);
        session.invalidate();
        return PathManager.getInstance().getProperty(PathManager.START_PAGE);
    }
}
