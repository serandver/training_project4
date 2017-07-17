package com.training.library.controller;

import com.training.library.controller.commands.*;
import com.training.library.services.impl.BookOrderServiceImpl;
import com.training.library.services.impl.BookServiceImpl;
import com.training.library.services.impl.UserServiceImpl;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

public class RequestHelper {

    private static final Logger LOGGER = Logger.getLogger(RequestHelper.class);

    private static RequestHelper instance = null;
    private HashMap<String,Command> commands = new HashMap<String,Command>();

    private RequestHelper() {
        commands.put("login", new LoginCommand(UserServiceImpl.getInstance()));
        commands.put("logout", new LogoutCommand());
        commands.put("register", new RegisterCommand(UserServiceImpl.getInstance()));
        commands.put("myorders", new LoadMyOrdersCommand(BookOrderServiceImpl.getInstance()));
        commands.put("catalogue", new LoadBookCataloguePageCommand(BookServiceImpl.getInstance()));
        commands.put("confirmOrder", new ConfirmBookOrderCommand(BookOrderServiceImpl.getInstance(), BookServiceImpl.getInstance()));
        commands.put("editBook", new EditBookCommand(BookServiceImpl.getInstance()));
        commands.put("openBook", new LoadBookPageCommand());
        commands.put("addBook", new AddBookCommand(BookServiceImpl.getInstance()));
        commands.put("goToAddBook", new LoadAddBookPageCommand());
        commands.put("deleteBook", new DeleteBookCommand(BookServiceImpl.getInstance()));
        commands.put("editOrder", new EditOrderCommand(BookOrderServiceImpl.getInstance(), BookServiceImpl.getInstance(), UserServiceImpl.getInstance()));
        commands.put("openOrder", new LoadOrderPageCommand(BookServiceImpl.getInstance(), UserServiceImpl.getInstance()));
        commands.put("newOrder", new LoadNewOrderPageCommand());
        commands.put("orderBook", new OrderBookCommand(BookOrderServiceImpl.getInstance(), UserServiceImpl.getInstance(), BookServiceImpl.getInstance()));
        commands.put("confirmPage", new LoadConfirmPageCommand());
        commands.put("findByTitle", new FindByTitleCommand(BookServiceImpl.getInstance()));
        commands.put("findByAuthor", new FindByAuthorCommand(BookServiceImpl.getInstance()));
        commands.put("edituser", new EditUserCommand(UserServiceImpl.getInstance()));
        commands.put("openuser", new LoadUserCommand(UserServiceImpl.getInstance()));
        commands.put("local", new ChangeLocalCommand());
        commands.put("librHome", new LibrarianHomePageCommand(BookOrderServiceImpl.getInstance()));
        commands.put("readerHome", new ReaderHomePageCommand(BookServiceImpl.getInstance()));
        commands.put("users", new LoadUserManagementPageCommand(UserServiceImpl.getInstance()));
        commands.put("orders", new LoadOrdersManagementPageCommand(BookOrderServiceImpl.getInstance()));
        commands.put("searchPage", new LoadSearchPageCommand());
        commands.put("deleteOrder", new DeleteOrderCommand(BookOrderServiceImpl.getInstance()));
        commands.put("signuppage", new LoadSignUpPageCommand());
        commands.put("signinpage", new LoadSignInPageCommand());


    }

    public Command getCommand(HttpServletRequest request) {
        String action = request.getParameter("command");
        Command command = commands.get(action);
        if (command == null) {
            command = new NoCommand();
        }
        return command;
    }

    public static RequestHelper getInstance(){
        if (instance == null) {
            synchronized (RequestHelper.class) {
                if (instance == null) {
                    instance = new RequestHelper();
                }
            }
        }
        return instance;
    }
}
