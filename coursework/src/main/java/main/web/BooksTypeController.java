package main.web;

import main.service.BooksTypeService;
import main.tables.BooksType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/btc")
public class BooksTypeController {
    @Autowired
    private BooksTypeService booksTypeService;

    @GetMapping("/books type")
    public ResponseEntity<List<BooksType>> getAllBooksType() {
        List<BooksType> list = booksTypeService.listBooks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BooksType> getBooksType(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(booksTypeService.findBooksType(id), HttpStatus.OK);
        } catch (EntityNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<List<BooksType>> getBooksTypeByName(@PathVariable("name") String name) {
        return new ResponseEntity<>(booksTypeService.findByName(name), HttpStatus.OK);
    }

    @PostMapping(value = "/add_type", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public BooksType addBooksType (@RequestBody BooksType newType) {
        return booksTypeService.addBookType(newType);
    }

    @PatchMapping("/update/{id}/days")
    public ResponseEntity<Integer> updateBooksTypeDayCount(@PathVariable("id") int id, @RequestParam(value = "days_count") int count) {
        return new ResponseEntity<>(booksTypeService.updateDayCountById(count, id), HttpStatus.OK);
    }

    @PatchMapping("/update/{id}/cnt")
    public ResponseEntity<Integer> updateBooksTypeCount(@PathVariable("id") int id, @RequestParam(value = "cnt") int count) {
        return new ResponseEntity<>(booksTypeService.updateCntById(count, id), HttpStatus.OK);
    }

    @DeleteMapping("/{name}")
    public void deleteBooksType(@PathVariable("name") String name) {
        booksTypeService.deleteByName(name);
    }
}
