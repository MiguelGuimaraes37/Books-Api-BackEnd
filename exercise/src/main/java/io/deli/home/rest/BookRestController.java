package io.deli.home.rest;

import io.deli.home.converters.BookDtoToBook;
import io.deli.home.converters.BookToBookDto;
import io.deli.home.dtos.BookDto;
import io.deli.home.model.Book;
import io.deli.home.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/books")
public class BookRestController {

    private BookService bookService;
    private BookDtoToBook bookDtoToBook;
    private BookToBookDto bookToBookDto;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Autowired
    public void setBookDtoToBook(BookDtoToBook bookDtoToBook) {
        this.bookDtoToBook = bookDtoToBook;
    }

    @Autowired
    public void setBookToBookDto(BookToBookDto bookToBookDto) {
        this.bookToBookDto = bookToBookDto;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public ResponseEntity<BookDto> showBook(@PathVariable Integer id) {

        Book book = bookService.get(id);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookToBookDto.convert(book), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<BookDto>> listBooks() {

        List<BookDto> bookDtos = bookService.list().stream()
                .map(book -> bookToBookDto.convert(book))
                .collect(Collectors.toList());

        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }
}
