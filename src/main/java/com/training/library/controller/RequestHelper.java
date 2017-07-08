package com.training.library.controller;

import com.training.library.controller.commands.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {
    private static RequestHelper requestHelperInstance = null;
    HashMap<String,Command> commands = new HashMap<String,Command>();

    private RequestHelper() {
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("register", new RegisterCommand());
        commands.put("show-my-orders", new LoadMyOrdersCommand());
        commands.put("catalogue", new LoadBookCataloguePageCommand());
        commands.put("confirmOrder", new ConfirmBookOrderCommand());
        commands.put("editBook", new EditBookCommand());
        commands.put("openBook", new LoadBookPageCommand());
        commands.put("addBook", new AddBookCommand());
        commands.put("goToAddBook", new LoadAddBookPageCommand());
        commands.put("deleteBook", new DeleteBookCommand());
        commands.put("editOrder", new EditOrderCommand());
        commands.put("openOrder", new LoadOrderPageCommand());
        commands.put("newOrder", new LoadNewOrderPageCommand());
        commands.put("orderBook", new OrderBookCommand());
        commands.put("confirmPage", new LoadConfirmPageCommand());
        commands.put("findByTitle", new FindByTitleCommand());
        commands.put("findByAuthor", new FindByAuthorCommand());
    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getRequestHelperInstance(){
        if (requestHelperInstance == null) {
            synchronized (RequestHelper.class) {
                if (requestHelperInstance == null) {
                    requestHelperInstance = new RequestHelper();
                }
            }
        }
        return requestHelperInstance;
    }
}
