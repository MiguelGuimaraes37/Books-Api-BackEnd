package io.deli.home.converters;

import io.deli.home.dtos.BookDto;
import io.deli.home.model.Book;
import io.deli.home.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookDtoToBook implements Converter<BookDto, Book> {

    BookService bookService;

    @Autowired
    public void setUserService(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public Book convert(BookDto bookDto) {
        Book book = (bookDto.getId() !=null ? bookService.get(bookDto.getId()) : new Book());

        book.setId(bookDto.getId());
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setPrice(bookDto.getPrice());
        book.setISBN(bookDto.getISBN());
        book.setPublishedDate(bookDto.getPublishedDate());

        return book;
    }

}
