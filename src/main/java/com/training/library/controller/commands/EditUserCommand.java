package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.User;
import com.training.library.services.UserService;
import com.training.library.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

import static com.training.library.controller.utils.Attribute.*;

public class EditUserCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(EditUserCommand.class);

    private UserService userService;

    public EditUserCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);

        String userId = request.getParameter(USER_ID);
        String userRoleString = request.getParameter(USER_ROLE);
        User.Role newUserRole = User.Role.valueOf(userRoleString);

        Optional<User> optional = userService.find(Integer.parseInt(userId));
        if(optional.isPresent()) {
            User user = optional.get();
            user.setRole(newUserRole);
            int result = userService.update(user);
            if (result > 0) {
                pageToGo = new LoadUserManagementPageCommand(UserServiceImpl.getInstance()).execute(request, response);
            }
        }
        return pageToGo;
    }
}
