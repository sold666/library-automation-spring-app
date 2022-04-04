package main.service;

import main.repository.BooksTypeRepository;
import main.tables.BooksType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<BooksType> findBooks(Integer id) {
        return booksTypeRepository.findById(id);
    }

    @Override
    public BooksType findByName(String name) {
        return booksTypeRepository.findByName(name);
    }

    @Override
    public int updateDayCountById(Integer dayCount, Integer id) {
        return booksTypeRepository.updateDayCountById(dayCount, id);
    }

    @Override
    public int updateCntById(Integer cnt, Integer id) {
        return booksTypeRepository.updateCntById(cnt, id);
    }

    @Override
    public int deleteByName(String name) {
        return booksTypeRepository.deleteByName(name);
    }
}
