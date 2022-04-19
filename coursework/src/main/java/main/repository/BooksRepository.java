package main.repository;

import main.tables.Books;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BooksRepository extends CrudRepository<Books, Integer> {

    @Query("""
            select b from books b inner join b.journals journals
            where journals.client.firstName = ?1 and journals.client.lastName = ?2 and journals.client.patherName = ?3
            order by b.name""")
    List<Books> findClientBooks(String firstName, String lastName, String patherName);

    @Query("select b from books b where b.name = ?1")
    List<Books> findByName(String name);

    @Transactional
    @Modifying
    @Query("update books b set b.cnt = ?1 where b.id = ?2")
    int updateCntById(Integer cnt, Integer id);

    @Transactional
    @Modifying
    @Query("delete from books b where b.name = ?1")
    int deleteByName(String name);
}
