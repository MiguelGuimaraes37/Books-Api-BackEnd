package io.deli.home.converters;

import io.deli.home.dtos.BookDto;
import io.deli.home.model.Book;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BookToBookDto implements Converter<Book, BookDto> {

    @Override
    public BookDto convert(Book book) {
        BookDto bookDto = new BookDto();

        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setPrice(book.getPrice());

        return bookDto;
    }
}
