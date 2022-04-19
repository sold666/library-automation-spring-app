package main.service;

import main.repository.BooksRepository;
import main.tables.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceImpl implements BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Override
    public List<Books> listBooks() {
        return (List<Books>) booksRepository.findAll();
    }

    @Override
    public Books findBooks(Integer id) {
        Optional<Books> books = booksRepository.findById(id);
        if (books.isEmpty()) {
            throw new EntityNotFoundException("Books type not found");
        }
        return books.get();
    }

    @Override
    public List<Books> findByName(String name) {
        return booksRepository.findByName(name);
    }

    @Override
    public List<Books> findClientBooks(String firstName, String lastName, String patherName) {
        return booksRepository.findClientBooks(firstName, lastName, patherName);
    }

    @Override
    public Books addBook(Books book) {
        return booksRepository.save(book);
    }

    @Override
    public int updateCntById(Integer cnt, Integer id) {
        return booksRepository.updateCntById(cnt, id);
    }

    @Override
    public int deleteByName(String name) {
        List<Books> booksList = booksRepository.findByName(name);
        int count = 0;
        for (var book:booksList) {
            if(book.getJournals().isEmpty()) {
                count++;
                booksRepository.deleteById(book.getId());
            }
        }
        return count;
    }
}
