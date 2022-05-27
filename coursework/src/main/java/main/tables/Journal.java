package main.tables;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity(name = "journal")
@Table(name = "journal")
public class Journal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Books book;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Clients client;

    @Column(name = "date_beg")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateBeg;

    @Column(name = "date_end")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateEnd;

    @Column(name = "date_ret")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateRet;

    public Journal() {
    }

    public Journal(Books book, Clients client, Date dateBeg, Date dateEnd, Date dateRet) {
        this.book = book;
        this.client = client;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateRet = dateRet;
    }

    public Date getDateRet() {
        return dateRet;
    }

    public void setDateRet(Date dateRet) {
        this.dateRet = dateRet;
    }

    public Date getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public Date getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(Date dateBeg) {
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
