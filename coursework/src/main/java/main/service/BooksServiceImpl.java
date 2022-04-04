package main.service;

import main.repository.BooksRepository;
import main.tables.Books;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<Books> findBooks(Integer id) {
        return booksRepository.findById(id);
    }

    @Override
    public Books findByName(String name) {
        return booksRepository.findByName(name);
    }

    @Override
    public List<Books> findClientBooks(String firstName, String lastName, String patherName) {
        return booksRepository.findClientBooks(firstName, lastName, patherName);
    }

    @Override
    public int updateCntById(Integer cnt, Integer id) {
        return booksRepository.updateCntById(cnt, id);
    }

    @Override
    public int deleteByName(String name) {
        return booksRepository.deleteByName(name);
    }
}
