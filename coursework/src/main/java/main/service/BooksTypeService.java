package main.service;

import main.tables.BooksType;
import java.util.List;

public interface BooksTypeService {

    List<BooksType> listBooks();

    BooksType findBooksType(Integer id);

    List<BooksType> findByName(String name);

    BooksType addBookType(BooksType book);

    int updateDayCountById(Integer dayCount, Integer id);

    int updateCntById(Integer cnt, Integer id);

    int deleteByName(String name);
}
