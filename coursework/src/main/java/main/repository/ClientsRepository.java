package main.repository;

import main.tables.Clients;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ClientsRepository extends CrudRepository<Clients, Integer> {

    @Query("select c from clients c where c.firstName = ?1 and c.lastName = ?2 and c.patherName = ?3")
    Clients findByFirstNameAndLastNameAndPatherName(String firstName, String lastName, String patherName);

    @Query("select distinct j.client from journal j where j.book.name = ?1")
    List<Clients> findClientsWhoTakesBook(String bookName);

    @Query("select (count(c) > 0) from clients c where c.passportSeria = ?1 and c.passportNum = ?2")
    boolean existsByPassportSeriaAndPassportNum(String passportSeria, String passportNum);

    @Query("select sum(j.book.type.fine) from journal j where j.client.firstName = ?1 and j.client.lastName = ?2 and j.client.patherName = ?3")
    Integer clientFine(String firstName, String lastName, String patherName);

    @Query("select j.book.type.dayCount from journal j where j.client.firstName = ?1 and j.client.lastName = ?2 and j.client.patherName = ?3")
    Integer clientDayCount(String firstName, String lastName, String patherName);

    @Query("select sum(j.book.cnt) from journal j where j.client.firstName = ?1 and j.client.lastName = ?2 and j.client.patherName = ?3")
    Integer clientNumOfBooks(String firstName, String lastName, String patherName);
}
