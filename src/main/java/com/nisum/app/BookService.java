package com.nisum.app;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return this.bookRepository.findAll();
    }

    public Book addBook(BookInput bookInput) {
        Book book = Book.builder()
                .title(bookInput.title())
                .author(bookInput.author())
                .build();
        return bookRepository.save(book);
    }

    public Book findBookById(Long bookId) {
        return this.bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book is not available with is id" + bookId));
    }

    public Book updateBookById(Long bookId, BookInput bookInput) {
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
