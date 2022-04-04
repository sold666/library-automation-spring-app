package main.tables;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "books_types")
@Table(name = "books_types")
public class BooksType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "cnt")
    private Integer cnt;

    @Column(name = "fine")
    private Integer fine;

    @Column(name = "day_count")
    private Integer dayCount;

    @OneToMany(mappedBy = "type")
    private Collection<Books> books;

    public BooksType() {

    }

    public BooksType(String name, Integer cnt, Integer fine, Integer dayCount) {
        this.name = name;
        this.cnt = cnt;
        this.fine = fine;
        this.dayCount = dayCount;
    }

    public Integer getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public Integer getFine() {
        return fine;
    }

    public void setFine(Integer fine) {
        this.fine = fine;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BooksType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnt=" + cnt +
                ", fine=" + fine +
                ", dayCount=" + dayCount +
                '}';
    }
}
