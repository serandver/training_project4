package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.services.UserService;
import com.training.library.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoadUserManagementPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadUserManagementPageCommand.class);
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List allRegisteredUsers = userService.findAll();
        request.setAttribute("userList", allRegisteredUsers);
        return PathManager.getInstance().getProperty(PathManager.USERS_PAGE);
    }
}
