package main.web;

import main.service.BooksService;
import main.tables.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/bc")
public class BooksController {
    @Autowired
    private BooksService booksService;

    @GetMapping("/books")
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> list = booksService.listBooks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBooks(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(booksService.findBooks(id), HttpStatus.OK);
        } catch (EntityNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<Books>> getBooks(@PathVariable("name") String name) {
        return new ResponseEntity<>(booksService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/search_client_book")
    public ResponseEntity<List<Books>> getClientBooks(@RequestParam(value = "firstname") String firstName, @RequestParam(value = "lastname") String lastName, @RequestParam(value = "pathername") String patherName) {
        return new ResponseEntity<>(booksService.findClientBooks(firstName, lastName, patherName), HttpStatus.OK);
    }

    @PostMapping(value = "/add_book", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Books addJournal(@RequestBody Books newBook) {
        return booksService.addBook(newBook);
    }

    @PatchMapping("/update/{id}/cnt")
    public ResponseEntity<Integer> updateBooksCount(@PathVariable("id") int id, @RequestParam(value = "cnt") int count) {
        return new ResponseEntity<>(booksService.updateCntById(count, id), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteBooks(@PathVariable("name") String name) {
        booksService.deleteByName(name);
    }
}
