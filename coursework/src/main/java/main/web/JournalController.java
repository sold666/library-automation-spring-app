package main.web;

import main.service.BooksService;
import main.service.ClientsService;
import main.service.JournalService;
import main.tables.Books;
import main.tables.Clients;
import main.tables.Journal;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/jc")
public class JournalController {

    @Autowired
    private JournalService journalService;
    @Autowired
    private BooksService booksService;
    @Autowired
    private ClientsService clientsService;

    @GetMapping("/journals")
    public ResponseEntity<List<Journal>> getAllJournals() {
        List<Journal> list = journalService.listJournals();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Journal> getJournal(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(journalService.findJournal(id), HttpStatus.OK);
        } catch (EntityNotFoundException err) {
            System.out.println(err.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @GetMapping("/book/{bookID}")
    public ResponseEntity<List<Journal>> getJournalByBook(@PathVariable("bookID") int id) {
        try {
            Books bookID = booksService.findBooks(id);
            return new ResponseEntity<>(journalService.findByBook(bookID), HttpStatus.OK);
        } catch (EntityNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @GetMapping("/client/{clientID}")
    public ResponseEntity<List<Journal>> getJournalByClient(@PathVariable("clientID") int id) {
        try {
            Clients clientID = clientsService.findClientById(id);
            return new ResponseEntity<>(journalService.findByClient(clientID), HttpStatus.OK);
        } catch (EntityNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @PostMapping(value = "/add journal", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Journal addJournal(@RequestBody Journal newJournal) {
        booksService.addBook(newJournal.getBook());
        clientsService.addClients(newJournal.getClient());
        return journalService.addJournal(newJournal);
    }

    @PatchMapping("/update/{id}/date")
    public ResponseEntity<Integer> updateJournalDateEnd(@PathVariable("id") Integer id, @RequestParam String dateEnd) throws ParseException {
        DateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return new ResponseEntity<>(journalService.updateDateEndById(date.parse(dateEnd), id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteJournal(@PathVariable("id") int id) {
        try {
            journalService.deleteJournalById(id);
        } catch (EntityExistsException err) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
        }
    }

    @DeleteMapping("/client/{clientID}")
    public void deleteJournalByClient(@PathVariable("clientID") int id) {
        try {
            Clients clientID = clientsService.findClientById(id);
            journalService.findByClient(clientID);
            journalService.deleteByClient(clientID);
        } catch (EntityNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        } catch (EntityExistsException err) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
        }
    }
}
