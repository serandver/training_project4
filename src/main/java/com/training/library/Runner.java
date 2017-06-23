package com.training.library;

import com.training.library.model.entities.Book;
import com.training.library.model.services.BookService;
import com.training.library.model.services.impl.BookServiceImpl;

import java.util.List;
import java.util.Optional;


public class Runner {
    public static void main(String[] args) {
        BookService bookService = new BookServiceImpl();
        Book testBook = new Book.Builder()
            .setTitle("Title")
            .setAuthor("Author")
            .setInventoryNumber("104121021").build();
//        int id = bookService.create(testBook);
//        Book bookFromDB = bookService.find(id).get();
//        System.out.println(bookFromDB);
//        System.out.println(bookFromDB.getTitle().equals(testBook.getTitle()));
//        System.out.println(bookFromDB.getAuthor().equals(testBook.getAuthor()));
//        System.out.println(bookFromDB.getInventoryNumber().equals(testBook.getInventoryNumber()));
//        List<Book> books = bookService.findAll();
//        for (Book book: books) {
//            System.out.println(book);
//        }
//        Book bookFromDB = bookService.findByAuthor("Author").get();
//        System.out.println(bookFromDB.getId());
//        bookFromDB.setAuthor("Bradberry");
//        bookService.update(bookFromDB);
//        System.out.println(bookService.find(25).get());
        System.out.println(bookService.find(25).get());
        bookService.delete(25);
        bookService.find(25).get();


    }
}
