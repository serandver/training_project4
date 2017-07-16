package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.model.BookOrder;
import com.training.library.model.User;
import com.training.library.services.BookOrderService;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

import static com.training.library.controller.utils.Attribute.*;

public class LoadMyOrdersCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(LoadMyOrdersCommand.class);

    private BookOrderService bookOrderService;

    public LoadMyOrdersCommand(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        HttpSession session = request.getSession(true);
        User currentUser = (User)session.getAttribute(USER);
        int currentUserId = currentUser.getId();

        List<BookOrder> currentUserOrders = bookOrderService.findByUserId(currentUserId);
        request.setAttribute(ORDERS_LIST, currentUserOrders);

        return PathManager.getInstance().getProperty(PathManager.MY_ORDERS_PAGE);
    }
}
