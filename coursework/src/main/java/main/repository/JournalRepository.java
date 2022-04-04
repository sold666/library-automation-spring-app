package main.repository;

import main.tables.Books;
import main.tables.Clients;
import main.tables.Journal;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

public interface JournalRepository extends CrudRepository<Journal, Integer> {

    @Query("select j from journal j where j.client = ?1")
    List<Journal> findByClient(Clients client);

    @Query("select j from journal j where j.book = ?1")
    List<Journal> findByBook(Books book);

    @Transactional
    @Modifying
    @Query("update journal j set j.dateEnd = ?1 where j.id = ?2")
    int updateDateEndById(Instant dateEnd, Integer id);

    @Query("select (count(j) > 0) from journal j where j.client = ?1")
    boolean existsByClient(Clients client);

    @Transactional
    @Modifying
    @Query("delete from journal j where j.client = ?1")
    int deleteByClient(Clients client);
}
