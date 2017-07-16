package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.User;
import com.training.library.services.UserService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.training.library.controller.utils.Attribute.*;

public class LoadUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadUserCommand.class);

    private UserService userService;

    public LoadUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);;

        String userId = request.getParameter(USER_ID);

        Optional<User> result = userService.find(Integer.parseInt(userId));
        if(result.isPresent()) {
            User userForEditing = result.get();
            request.setAttribute(USER, userForEditing);
            pageToGo = PathManager.getInstance().getProperty(PathManager.EDIT_USER_PAGE);
        }
        return pageToGo;
    }
}
