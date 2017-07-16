package com.training.library.controller.commands;

import com.training.library.config.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.User;
import com.training.library.services.UserService;
import com.training.library.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

public class LoadUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadUserCommand.class);

    private UserService userService;

    public LoadUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);;

        String userId = request.getParameter("userId");

        Optional<User> result = userService.find(Integer.parseInt(userId));
        if(result.isPresent()) {
            User userForEditing = result.get();
            request.setAttribute("user", userForEditing);
            pageToGo = PathManager.getInstance().getProperty(PathManager.EDIT_USER_PAGE);
        }
        return pageToGo;
    }
}
