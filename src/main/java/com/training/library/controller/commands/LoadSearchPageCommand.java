package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.training.library.controller.utils.PathManager.SEARCH_PAGE;

public class LoadSearchPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return PathManager.getInstance().getProperty(SEARCH_PAGE);
    }
}
