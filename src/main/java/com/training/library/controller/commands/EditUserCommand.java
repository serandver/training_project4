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

public class EditUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditUserCommand.class);
    private UserService userService = UserServiceImpl.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);

        String userId = request.getParameter("userId");
        String userRoleString = request.getParameter("role");
        User.Role newUserRole = User.Role.valueOf(userRoleString);

        Optional<User> optional = userService.find(Integer.parseInt(userId));
        if(optional.isPresent()) {
            User user = optional.get();
            user.setRole(newUserRole);
            int result = userService.update(user);
            if (result > 0) {
                pageToGo = new LoadUserManagementPageCommand().execute(request, response);
            }
        }
        return pageToGo;
    }
}
