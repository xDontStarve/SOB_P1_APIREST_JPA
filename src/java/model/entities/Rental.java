package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Jialiang Chen
 */
@XmlRootElement
@Entity
@NamedQuery(name="addRental",
        query = "INSERT INTO Rental(price, date) VALUES (:price, :date) RETURNING id, price, date;")

public class Rental implements Serializable{
    @Id
    @SequenceGenerator(name="Rental_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Rental_Gen") 
    private long id;
    private float price;
    private Date date;
    @ManyToOne
    private Customer customer;
    @OneToMany(mappedBy="rental")
    private List<Game> games;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
}
