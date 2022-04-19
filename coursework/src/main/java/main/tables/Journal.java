package main.tables;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.Instant;

@Entity(name = "journal")
@Table(name = "journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Integer id;

    @JsonIgnoreProperties("journals")
    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @JsonIgnoreProperties("journals")
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @Column(name = "date_beg")
    private Instant dateBeg;

    @Column(name = "date_end")
    private Instant dateEnd;

    @Column(name = "date_ret")
    private Instant dateRet;

    public Journal() {
    }

    public Journal(Books book, Clients client, Instant dateBeg, Instant dateEnd, Instant dateRet) {
        this.book = book;
        this.client = client;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateRet = dateRet;
    }

    public Instant getDateRet() {
        return dateRet;
    }

    public void setDateRet(Instant dateRet) {
        this.dateRet = dateRet;
    }

    public Instant getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Instant dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Instant getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(Instant dateBeg) {
        this.dateBeg = dateBeg;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", book=" + book +
                ", client=" + client +
                ", dateBeg=" + dateBeg +
                ", dateEnd=" + dateEnd +
                ", dateRet=" + dateRet +
                '}';
    }
}
