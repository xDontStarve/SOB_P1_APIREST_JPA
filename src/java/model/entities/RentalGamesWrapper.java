/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.entities;

/**
 *
 * @author Jialiang Chen
 */
import java.util.List;

public class RentalGamesWrapper {
    private Rental rental;
    private List<Long> gameIDs;

    public RentalGamesWrapper(){
    }

    public Rental getRental() {
        return rental;
    }

    public void setRental(Rental rental) {
        this.rental = rental;
    }

    public List<Long> getGameIDs() {
        return gameIDs;
    }

    public void setGameIDs(List<Long> gameIDs) {
        this.gameIDs = gameIDs;
    }
}