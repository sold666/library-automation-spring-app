package main.service;

import main.tables.Books;

import java.util.List;

public interface BooksService {

    List<Books> listBooks();

    Books findBooks(Integer id);

    List<Books> findByName(String name);

    List<Books> findClientBooks(String firstName, String lastName, String patherName);

    Books addBook(Books book);

    int updateCntById(Integer cnt, Integer id);

    int deleteByName(String name);
}
