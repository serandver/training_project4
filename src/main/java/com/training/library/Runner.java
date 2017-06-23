package com.training.library;

import com.training.library.model.entities.Book;
import com.training.library.model.entities.BookOrder;
import com.training.library.model.entities.User;
import com.training.library.model.services.BookOrderService;
import com.training.library.model.services.BookService;
import com.training.library.model.services.impl.BookOrderServiceImpl;
import com.training.library.model.services.impl.BookServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


public class Runner {
    public static void main(String[] args) {
        BookOrderService bookOrderService = new BookOrderServiceImpl();
//        User user = new User.Builder()
//                .setId(1)
//                .setFirstName("Иван")
//                .setLastName("Иванов").build();
//        Book book = new Book.Builder()
//                .setId(1)
//                .setTitle("Преступление и наказание")
//                .setAuthor("Достоевский Ф.М.")
//                .setInventoryNumber("1040001").build();
//        BookOrder bookOrder = new BookOrder.Builder()
//                .setUser(user)
//                .setBook(book)
//                .setDateOfReceive(new Date())
//                .setDateOfReturn(null)
//                .setPlace(BookOrder.ReadingPlace.SUBSCRIPTION).build();
//        bookOrderService.create(bookOrder);


        List<BookOrder> bookOrders = new ArrayList<>();
        bookOrders = bookOrderService.findAll();
        for (BookOrder bo: bookOrders) {
            System.out.println(bo);
        }
    }
}
