package main.service;

import main.tables.BooksType;

import java.util.List;
import java.util.Optional;

public interface BooksTypeService {

    List<BooksType> listBooks();

    Optional<BooksType> findBooks(Integer id);

    BooksType findByName(String name);

    int updateDayCountById(Integer dayCount, Integer id);

    int updateCntById(Integer cnt, Integer id);

    int deleteByName(String name);
}
