package main.service;

import main.tables.Books;
import main.tables.Clients;
import main.tables.Journal;

import java.util.Date;
import java.util.List;

public interface JournalService {

    List<Journal> listJournals();

    Journal findJournal(Integer id);

    List<Journal> findByBook(Books book);

    List<Journal> findByClient(Clients client);

    Journal addJournal(Journal journal);

    int updateDateEndById(Date dateEnd, Integer id);

    boolean existsByClient(Clients client);

    void deleteJournalById(Integer integer);

    int deleteByClient(Clients client);
}
