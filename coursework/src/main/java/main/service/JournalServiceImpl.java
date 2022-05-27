package main.service;

import main.repository.BooksRepository;
import main.repository.JournalRepository;
import main.tables.Books;
import main.tables.Clients;
import main.tables.Journal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import java.awt.print.Book;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class JournalServiceImpl implements JournalService {

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private BooksRepository booksRepository;

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
        final Optional<Books> books = booksRepository.findById(journal.getBook().getId());
        if (books.isEmpty()) {
            return journalRepository.save(journal);
        }
        int delta = books.get().getCnt() - journal.getBook().getCnt();
        if (delta < 0) {
            throw new EntityNotFoundException("Too few books!");
        }
        booksRepository.updateCntById(delta, journal.getBook().getId());
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
        final Journal journal = journalRepository.findById(id).orElseThrow(() ->  new EntityExistsException("Journal with this id: " + id + ",does not exist"));
        final Books book = booksRepository.findByName(journal.getBook().getName()).get(0);
        booksRepository.updateCntById(book.getCnt() + journal.getBook().getCnt(), book.getId());
        journalRepository.deleteById(id);
    }
}
