package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Path;
import model.entities.Comment;
import model.entities.Rental;

/**
 *
 * @author Jialiang
 */
@Stateless
@Path("/rest/api/v1/Rental")
public class RentalService extends AbstractFacade<Rental> {
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public RentalService() {
        super(Rental.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
