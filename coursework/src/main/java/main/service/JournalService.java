package main.service;

import main.tables.Books;
import main.tables.Clients;
import main.tables.Journal;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface JournalService {

    List<Journal> listJournals();

    Optional<Journal> findJournal(Integer id);

    List<Journal> findByBook(Books book);

    List<Journal> findByClient(Clients client);

    int updateDateEndById(Instant dateEnd, Integer id);

    boolean existsByClient(Clients client);

    void deleteByIdInJournal(Integer integer);

    int deleteByClient(Clients client);
}
