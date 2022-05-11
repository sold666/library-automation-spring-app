package main.service;

import main.repository.BooksTypeRepository;
import main.tables.BooksType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class BooksTypeServiceImpl implements BooksTypeService {

    @Autowired
    private BooksTypeRepository booksTypeRepository;

    @Override
    public List<BooksType> listBooks() {
        return (List<BooksType>) booksTypeRepository.findAll();
    }

    @Override
    public BooksType findBooksType(Integer id) {
        Optional<BooksType> booksType = booksTypeRepository.findById(id);
        if (booksType.isEmpty()) {
            throw new EntityNotFoundException("Books type not found");
        }
        return booksType.get();
    }

    @Override
    public List<BooksType> findByName(String name) {
        return booksTypeRepository.findByName(name);
    }

    @Override
    public int updateDayCountById(Integer dayCount, Integer id) {
        return booksTypeRepository.updateDayCountById(dayCount, id);
    }

    @Override
    public BooksType addBookType(BooksType type) {
        return booksTypeRepository.save(type);
    }

    @Override
    public int updateCntById(Integer cnt, Integer id) {
        return booksTypeRepository.updateCntById(cnt, id);
    }

    @Override
    public int deleteByName(String name) {
        List<BooksType> booksTypesList = booksTypeRepository.findByName(name);
        int count = 0;
        for (var type:booksTypesList) {
            if(type.getBooks().isEmpty()) {
                count++;
                booksTypeRepository.deleteById(type.getId());
            }
        }
        return count;
    }
}
