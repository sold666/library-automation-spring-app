package main.service;

import main.repository.JournalRepository;
import main.tables.Books;
import main.tables.Clients;
import main.tables.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.util.Date;
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
    public Journal findJournal(Integer id) {
        Optional<Journal> journal = journalRepository.findById(id);
        if (journal.isEmpty()) {
            throw new EntityNotFoundException("Journal not found");
        }
        return journal.get();
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
    public Journal addJournal(Journal journal) {
        return journalRepository.save(journal);
    }
    @Override
    public int updateDateEndById(Date dateEnd, Integer id) {
        return journalRepository.updateDateEndById(dateEnd, id);
    }

    @Override
    public boolean existsByClient(Clients client) {
        return journalRepository.existsByClient(client);
    }

    @Override
    public int deleteByClient(Clients client) {
        if (!journalRepository.existsByClient(client)) {
            throw new EntityExistsException("There is no such client in the journal");
        }
        return journalRepository.deleteByClient(client);
    }

    @Override
    public void deleteJournalById(Integer id) {
        if (!journalRepository.existsById(id)) {
            throw new EntityExistsException("Journal with this id: " + id + ",does not exist");
        }
        journalRepository.deleteById(id);
    }
}
