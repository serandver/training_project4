package com.training.library;

import com.training.library.entities.BookOrder;
import com.training.library.services.BookOrderService;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookOrderServiceImpl;
import com.training.library.services.impl.BookServiceImpl;

import java.util.List;


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


        List<BookOrder> bookOrders = bookOrderService.findByReadingPlace(BookOrder.ReadingPlace.READING_ROOM);
        for (BookOrder bo: bookOrders) {
            System.out.println(bo);
        }
//        BookOrder bookOrder = bookOrderService.find(1).get();
//        System.out.println(bookOrder);
//        bookOrder.setPlace(BookOrder.ReadingPlace.SUBSCRIPTION);
//        bookOrderService.update(bookOrder);
//        BookOrder bookOrderFromDB = bookOrderService.find(1).get();
//        System.out.println(bookOrderFromDB);
    }
}
