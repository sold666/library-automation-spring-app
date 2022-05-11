package main.repository;

import main.tables.BooksType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BooksTypeRepository extends CrudRepository<BooksType, Integer> {

    @Query("select b from books_types b where b.name = ?1")
    List<BooksType> findByName(String name);

    @Transactional
    @Modifying
    @Query("update books_types b set b.cnt = ?1 where b.id = ?2")
    int updateCntById(Integer cnt, Integer id);

    @Transactional
    @Modifying
    @Query("update books_types b set b.dayCount = ?1 where b.id = ?2")
    int updateDayCountById(Integer dayCount, Integer id);

    @Transactional
    @Modifying
    @Query("delete from books_types b where b.name = ?1")
    int deleteByName(String name);
}
