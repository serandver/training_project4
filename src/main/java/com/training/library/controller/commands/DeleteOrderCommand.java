package com.training.library.controller.commands;

import com.training.library.controller.utils.PathManager;
import com.training.library.exceptions.ServiceException;
import com.training.library.services.BookOrderService;
import com.training.library.services.impl.BookOrderServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.training.library.controller.utils.Attribute.ORDER_ID;

public class DeleteOrderCommand implements Command {

    private static final Logger LOGGER = Logger.getLogger(DeleteOrderCommand.class);

    private BookOrderService bookOrderService;

    public DeleteOrderCommand(BookOrderService bookOrderService) {
        this.bookOrderService = bookOrderService;
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String pageToGo = PathManager.getInstance().getProperty(PathManager.ERROR_PAGE);
        int orderIdForDeleting = Integer.parseInt(request.getParameter(ORDER_ID));
        bookOrderService.delete(orderIdForDeleting);
        pageToGo = new LoadOrdersManagementPageCommand(BookOrderServiceImpl.getInstance()).execute(request, response);
        return pageToGo;
    }
}
