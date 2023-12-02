
package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.SequenceGenerator;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Jialiang Chen
 */
@NamedQuery(
    name = "findGamesByGenreAndConsole",
    query = "SELECT g FROM Game g WHERE (g.genre = :genre) AND (g.console = :console) ORDER BY g.name ASC"
)
@NamedQuery(
    name = "findGamesByGenre",
    query = "SELECT g FROM Game g WHERE (g.genre = :genre) ORDER BY g.name ASC"
)
@NamedQuery(
    name = "findGamesByConsole",
    query = "SELECT g FROM Game g WHERE (g.console = :console) ORDER BY g.name ASC"
)
@NamedQuery(
    name = "findAllGames",
    query= "SELECT g FROM Game g ORDER BY g.name ASC"
)
@NamedQuery(
    name = "findSameGame",
    query = "SELECT g FROM Game g WHERE (g.console = :console) AND (g.genre = :genre) AND (g.address = :address) AND (g.name = :name) AND (g.isAvailable = :isAvailable) AND (g.description = :description) AND (g.price = :price)"
)
@XmlRootElement
@Entity
public class Game implements Serializable{
    
    @Id
    @SequenceGenerator(name="Game_Gen", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Game_Gen") 
    private long id;
    private String name;
    private boolean isAvailable;
    private float price;
    private String description;
    private String address;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    @Enumerated(EnumType.STRING)
    private Console console;
    @ManyToMany
    @JoinTable(
        name = "game_rental",
        joinColumns = @JoinColumn(name = "game_id"),
        inverseJoinColumns = @JoinColumn(name = "rental_id")
    )
    @JsonbTransient
    private List<Rental> rentals;
    public enum Genre{
        ACTION,
        HORROR,
        FAMILY,
        RACING,
        SHOOTER,
        ADVENTURE
    }
    public enum Console{
        NDS,
        GBA,
        GB,
        PS1,
        PS2,
        PS3,
        PS4
    }

    
    public Game(){

    }
    
    public Game (long id){
        this.id=id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Console getConsole() {
        return console;
    }

    public void setConsole(Console console) {
        this.console = console;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
    
}
