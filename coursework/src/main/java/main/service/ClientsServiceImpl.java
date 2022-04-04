package main.service;

import main.repository.ClientsRepository;
import main.tables.Clients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Clients> findClientById(Integer id) {
        return clientsRepository.findById(id);
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
    public void deleteById(Integer integer) {
        clientsRepository.deleteById(integer);
    }
}
