package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.training.library.controller.utils.Attribute.USERS_LIST;

public class LoadUserManagementPageCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadUserManagementPageCommand.class);

    private UserService userService;

    public LoadUserManagementPageCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        List allRegisteredUsers = userService.findAll();
        request.setAttribute(USERS_LIST, allRegisteredUsers);
        return PathManager.getInstance().getProperty(PathManager.USERS_PAGE);
    }
}
