package main.service;

import main.tables.Clients;

import java.util.List;

public interface ClientsService {

    List<Clients> listClients();

    List<Clients> findClientsWhoTakeBook(String bookName);

    Clients findClientById(Integer id);

    Clients findClientByFullName(String firstName, String lastName, String patherName);

    boolean existsByPassportSeriaAndPassportNum(String passportSeria, String passportNum);

    Integer clientFine(String firstName, String lastName, String patherName);

    Integer clientDayCount(String firstName, String lastName, String patherName);

    Integer clientNumOfBooks(String firstName, String lastName, String patherName);

    Clients addClients(Clients client);

    void deleteById(Integer id);
}
