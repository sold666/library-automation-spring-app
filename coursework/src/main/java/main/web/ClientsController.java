package main.web;

import main.service.ClientsService;
import main.tables.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping("/cc")
public class ClientsController {
    @Autowired
    private ClientsService clientsService;

    @GetMapping("/clients")
    public ResponseEntity<List<Clients>> getAllClients() {
        List<Clients> list = clientsService.listClients();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Clients> getClient(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(clientsService.findClientById(id), HttpStatus.OK);
        } catch (EntityNotFoundException err) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, err.getMessage());
        }
    }

    @GetMapping("/full name")
    public ResponseEntity<Clients> getClientByFullName(@RequestParam(value = "firstname") String firstName, @RequestParam(value = "lastname") String lastName, @RequestParam(value = "pathername") String patherName) {
        return new ResponseEntity<>(clientsService.findClientByFullName(firstName, lastName, patherName), HttpStatus.OK);
    }

    @GetMapping("/full name/fine")
    public ResponseEntity<Integer> getClientFine(@RequestParam(value = "firstname") String firstName, @RequestParam(value = "lastname") String lastName, @RequestParam(value = "pathername") String patherName) {
        return new ResponseEntity<>(clientsService.clientFine(firstName, lastName, patherName), HttpStatus.OK);
    }

    @GetMapping("/full name/days")
    public ResponseEntity<Integer> getClientDaysCount(@RequestParam(value = "firstname") String firstName, @RequestParam(value = "lastname") String lastName, @RequestParam(value = "pathername") String patherName) {
        return new ResponseEntity<>(clientsService.clientDayCount(firstName, lastName, patherName), HttpStatus.OK);
    }

    @GetMapping("/full name/num")
    public ResponseEntity<Integer> getClientNumOfBooks(@RequestParam(value = "firstname") String firstName, @RequestParam(value = "lastname") String lastName, @RequestParam(value = "pathername") String patherName) {
        return new ResponseEntity<>(clientsService.clientNumOfBooks(firstName, lastName, patherName), HttpStatus.OK);
    }

    @PostMapping(value = "/add client", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Clients addClient (@RequestBody Clients newClient) {
        return clientsService.addClients(newClient);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable("id") int id) {
        try {
            clientsService.deleteById(id);
        } catch (EntityExistsException err) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, err.getMessage());
        }
    }
}
