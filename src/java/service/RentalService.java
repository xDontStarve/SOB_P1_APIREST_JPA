package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.entities.Rental;

/**
 *
 * @author Jialiang
 */
public class RentalService extends AbstractFacade<Rental>{
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public RentalService() {
        super(Rental.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
