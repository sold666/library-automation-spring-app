package main.service;

import main.tables.Books;

import java.util.List;
import java.util.Optional;

public interface BooksService {

    List<Books> listBooks();

    Optional<Books> findBooks(Integer id);

    Books findByName(String name);

    List<Books> findClientBooks(String firstName, String lastName, String patherName);

    int updateCntById(Integer cnt, Integer id);

    int deleteByName(String name);
}
