package com.nisum.app;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        log.info("Entering into getAllBooks method");
        return this.bookRepository.findAll();
    }

    public Book addBook(BookInput bookInput) {
        log.info("Entering into addBook: {}",bookInput);
        Book book = Book.builder()
                .title(bookInput.title())
                .author(bookInput.author())
                .build();
        return bookRepository.save(book);
    }

    public Book findBookById(Long bookId) {
        log.info("Entering into a findBookById : {}",bookId);
        return this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book is not available with is id" + bookId));
    }

    public Book updateBookById(Long bookId, BookInput bookInput) {
        log.info("Entering into updateBookById Method: {}",bookId);
        Book existingBook = this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book is not available with is id" + bookId));
        existingBook.setId(bookId);
        existingBook.setTitle(bookInput.title());
        existingBook.setAuthor(bookInput.author());
        return this.bookRepository.save(existingBook);

    }

    public String deleteBookById(Long bookId) {
        this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book is not available this id: " + bookId));
        this.bookRepository.deleteById(bookId);
        return "Book is Deleted successfully with id: " + bookId;
    }
}
