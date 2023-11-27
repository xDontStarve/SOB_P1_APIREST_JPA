package model.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Jialiang Chen
 */
@XmlRootElement
@Entity
public class Rental {
    @Id
    @SequenceGenerator(name="Game_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Game_Gen") 
    private long id;
    private float price;
    @XmlElement
    @XmlJavaTypeAdapter(value = service.DateAdapter.class, type = Date.class)
    private Date date;

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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 23 * hash + Float.floatToIntBits(this.price);
        hash = 23 * hash + Objects.hashCode(this.date);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rental other = (Rental) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        return Objects.equals(this.date, other.date);
    }
    
    
}
