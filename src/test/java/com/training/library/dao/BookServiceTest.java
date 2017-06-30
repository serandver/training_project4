package com.training.library.dao;

import com.training.library.entities.Book;
import com.training.library.services.BookService;
import com.training.library.services.impl.BookServiceImpl;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BookServiceTest {

    private BookService bookService = new BookServiceImpl();
    private Book testBook = new Book.Builder()
            .setId(1)
            .setTitle("Title")
            .setAuthor("Author")
            .setInventoryNumber("104121021").build();

    @Ignore
    @Test
    public void testCreateBook() {
        int expectedIndexFromBookService = bookService.create(testBook);
        int actualIndexFromBook = testBook.getId();
        assertEquals(expectedIndexFromBookService, actualIndexFromBook);
    }

    @Test
    public void testFindAllBooks() {
        List<Book> users = bookService.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
    }
}
