package main.service;

import main.tables.Clients;

import java.util.List;
import java.util.Optional;

public interface ClientsService {

    List<Clients> listClients();

    List<Clients> findClientsWhoTakeBook(String bookName);

    Optional<Clients> findClientById(Integer id);

    Clients findClientByFullName(String firstName, String lastName, String patherName);

    boolean existsByPassportSeriaAndPassportNum(String passportSeria, String passportNum);

    Integer clientFine(String firstName, String lastName, String patherName);

    Integer clientDayCount(String firstName, String lastName, String patherName);

    Integer clientNumOfBooks(String firstName, String lastName, String patherName);

    void deleteById(Integer integer);
}
