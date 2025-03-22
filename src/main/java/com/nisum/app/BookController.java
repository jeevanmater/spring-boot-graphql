package com.nisum.app;


import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @MutationMapping
    public Book getBookByIdHandle(@Argument Long bookId) {
        return this.bookService.findBookById(bookId);
    }

    @QueryMapping
    public List<Book> getAllBooksHandler(){
        return this.bookService.getAllBooks();
    }

    @MutationMapping
    public Book addBookHandler(@Argument BookInput bookInput){
        return this.bookService.addBook(bookInput);
    }

    @MutationMapping
    public Book updateBookHandler(@Argument Long bookId, @Argument BookInput bookInput){
        return this.bookService.updateBookById(bookId,bookInput);
    }

    @MutationMapping
    public String deleteBookByIdHandler(@Argument Long bookId){
        return this.bookService.deleteBookById(bookId);
    }
}

