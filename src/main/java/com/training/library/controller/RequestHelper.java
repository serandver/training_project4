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
        commands.put("show-my-orders", new ShowMyOrdersCommand());
        commands.put("catalogue", new ViewFullCatalogueCommand());
        commands.put("confirmOrder", new ConfirmBookOrderCommand());
        commands.put("editBook", new EditBookCommand());
        commands.put("openBook", new OpenBookCommand());
        commands.put("addBook", new AddBookCommand());
        commands.put("goToAddBook", new GoToAddBookPageCommand());
        commands.put("deleteBook", new DeleteBookCommand());
        commands.put("editOrder", new EditOrderCommand());
        commands.put("openOrder", new OpenOrderCommand());
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
