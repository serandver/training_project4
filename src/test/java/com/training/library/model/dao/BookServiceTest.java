package com.training.library.model.dao;

import com.training.library.model.entities.Book;
import com.training.library.model.services.BookService;
import com.training.library.model.services.impl.BookServiceImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookServiceTest {
    private BookService bookService = new BookServiceImpl();
    private Book testBook = new Book.Builder()
            .setId(1)
            .setTitle("Title")
            .setAuthor("Author")
            .setInventoryNumber("104121021").build();

    @Test
    public void testCreateBook() throws Exception {
        int expectedIndexFromBookService = bookService.create(testBook);
        int actualIndexFromBook = testBook.getId();
        assertEquals(expectedIndexFromBookService, actualIndexFromBook);
    }
}
