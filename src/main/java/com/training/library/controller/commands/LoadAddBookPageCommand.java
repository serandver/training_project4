package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoadAddBookPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadAddBookPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return PathManager.getInstance().getProperty(PathManager.ADD_BOOK_PAGE);
    }
}
