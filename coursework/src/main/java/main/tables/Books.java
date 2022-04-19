package main.tables;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "books")
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", length = 20)
    private String name;

    @Column(name = "cnt")
    private Integer cnt;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private BooksType type;

    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Collection<Journal> journals;

    public Books() {

    }

    public Books(String name, Integer cnt, BooksType type) {
        this.name = name;
        this.cnt = cnt;
        this.type = type;
    }

    public BooksType getType() {
        return type;
    }

    public void setType(BooksType type) {
        this.type = type;
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

    public Collection<Journal> getJournals() {
        return journals;
    }

    public void setJournals(Collection<Journal> journals) {
        this.journals = journals;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cnt=" + cnt +
                ", type=" + type +
                '}';
    }
}
