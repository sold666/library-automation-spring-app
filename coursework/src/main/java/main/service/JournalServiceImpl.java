package main.service;

import main.repository.JournalRepository;
import main.tables.Books;
import main.tables.Clients;
import main.tables.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Override
    public List<Journal> listJournals() {
        return (List<Journal>) journalRepository.findAll();
    }

    @Override
    public Optional<Journal> findJournal(Integer id) {
        return journalRepository.findById(id);
    }

    @Override
    public List<Journal> findByBook(Books book) {
        return journalRepository.findByBook(book);
    }

    @Override
    public List<Journal> findByClient(Clients client) {
        return journalRepository.findByClient(client);
    }

    @Override
    public int updateDateEndById(Instant dateEnd, Integer id) {
        return journalRepository.updateDateEndById(dateEnd, id);
    }

    @Override
    public boolean existsByClient(Clients client) {
        return journalRepository.existsByClient(client);
    }

    @Override
    public int deleteByClient(Clients client) {
        return journalRepository.deleteByClient(client);
    }

    @Override
    public void deleteByIdInJournal(Integer id) {
        journalRepository.deleteById(id);
    }
}
