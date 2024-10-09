package io.deli.home.rest;

import io.deli.home.converters.BookDtoToBook;
import io.deli.home.converters.BookToBookDto;
import io.deli.home.dtos.BookDto;
import io.deli.home.model.Book;
import io.deli.home.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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

    @RequestMapping(method = RequestMethod.GET, path = "id/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable Integer id) {
        Book book = bookService.get(id);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookToBookDto.convert(book), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = {"/", ""})
    public ResponseEntity<List<BookDto>> getBooks() {
        List<BookDto> bookDtos = bookService.list().stream()
                .map(book -> bookToBookDto.convert(book))
                .collect(Collectors.toList());

        return new ResponseEntity<>(bookDtos, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.DELETE,
            path = "/delete/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<HttpStatus> deleteBookById(@PathVariable Integer id) {
        Book book = bookService.get(id);

        if(book == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        bookService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.PUT,
            path = "edit/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE

    )
    public ResponseEntity<BookDto> updateBookById(@RequestBody BookDto bookDto, BindingResult bindingResult,
                                                        @PathVariable Integer id){
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        bookDto.setId(id);

        Book book = bookService.createOrUpdate(bookDtoToBook.convert(bookDto));

        return new ResponseEntity<>(bookToBookDto.convert(book), HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/create",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        Book book = bookService.createOrUpdate(bookDtoToBook.convert(bookDto));

        return new ResponseEntity<>(bookToBookDto.convert(book), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "isbn/{isbn}")
    public ResponseEntity<BookDto> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByISBN(isbn);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookToBookDto.convert(book), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, path = "book/{isbn}/{id}")
    public ResponseEntity<BookDto> getBookByIsbnWithoutId(@PathVariable String isbn, @PathVariable Integer id) {
        Book book = bookService.getBookWithoutId(id, isbn);

        if (book == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(bookToBookDto.convert(book), HttpStatus.OK);
    }
}
