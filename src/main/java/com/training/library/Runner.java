package com.training.library;

import com.training.library.model.Book;
import com.training.library.model.BookOrder;
import com.training.library.services.BookOrderService;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookOrderServiceImpl;
import com.training.library.services.impl.BookServiceImpl;

import java.util.List;


public class Runner {
    public static void main(String[] args) {
        BookService bookService = BookServiceImpl.getInstance();
//        User user = new User.Builder()
//                .setId(1)
//                .setFirstName("Иван")
//                .setLastName("Иванов").build();
//        Book book = new Book.Builder()
//                .setTitle("test")
//                .setAuthor("test")
//                .setInventoryNumber("1040005555")
//                .setBookStatus(Book.BookStatus.AVAILABLE).build();

        List<Book> list = bookService.findByAuthor("Досто");
        for (Book book: list) {
            System.out.println(book);
        }
//        System.out.println(book);
//        book.setBookStatus(Book.BookStatus.UNAVAILABLE);
//        bookService.update(book);
//        System.out.println(book);

//        BookOrder bookOrder = new BookOrder.Builder()
//                .setUser(user)
//                .setBook(book)
//                .setDateOfReceive(new Date())
//                .setDateOfReturn(null)
//                .setPlace(BookOrder.ReadingPlace.SUBSCRIPTION).build();
//        bookOrderService.create(bookOrder);


//        List<BookOrder> bookOrders = bookOrderService.findByReadingPlace(BookOrder.ReadingPlace.READING_ROOM);
//        for (BookOrder bo: bookOrders) {
//            System.out.println(bo);
//        }
//        BookOrder bookOrder = bookOrderService.find(1).get();
//        System.out.println(bookOrder);
//        bookOrder.setPlace(BookOrder.ReadingPlace.SUBSCRIPTION);
//        bookOrderService.update(bookOrder);
//        BookOrder bookOrderFromDB = bookOrderService.find(1).get();
//        System.out.println(bookOrderFromDB);
    }
}
