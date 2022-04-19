package main.service;

import main.repository.ClientsRepository;
import main.tables.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class ClientsServiceImpl implements ClientsService {

    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    public List<Clients> listClients() {
        return (List<Clients>) clientsRepository.findAll();
    }

    @Override
    public Clients findClientById(Integer id) {
        Optional<Clients> clients = clientsRepository.findById(id);
        if (clients.isEmpty()) {
            throw new EntityNotFoundException("Clients not found");
        }
        return clients.get();
    }

    @Override
    public Clients findClientByFullName(String firstName, String lastName, String patherName) {
        return clientsRepository.findByFirstNameAndLastNameAndPatherName(firstName, lastName, patherName);
    }

    @Override
    public List<Clients> findClientsWhoTakeBook(String bookName) {
        return clientsRepository.findClientsWhoTakesBook(bookName);
    }

    @Override
    public boolean existsByPassportSeriaAndPassportNum(String passportSeria, String passportNum) {
        return clientsRepository.existsByPassportSeriaAndPassportNum(passportSeria, passportNum);
    }

    @Override
    public Integer clientFine(String firstName, String lastName, String patherName) {
        return clientsRepository.clientFine(firstName, lastName, patherName);
    }

    @Override
    public Integer clientDayCount(String firstName, String lastName, String patherName) {
        return clientsRepository.clientDayCount(firstName, lastName, patherName);
    }

    @Override
    public Integer clientNumOfBooks(String firstName, String lastName, String patherName) {
        return clientsRepository.clientNumOfBooks(firstName, lastName, patherName);
    }

    @Override
    public Clients addClients(Clients clients) {
        return clientsRepository.save(clients);
    }

    @Override
    public void deleteById(Integer id) {
        if (!clientsRepository.existsById(id)) {
            throw new EntityExistsException("There is no such client");
        }
        clientsRepository.deleteById(id);
    }
}
