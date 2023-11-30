package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.ws.rs.Path;
import model.entities.Customer;

/**
 *
 * @author Jialiang
 */
@Stateless
@Path("Customer")
public class CustomerService extends AbstractFacade<Customer> {
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;

    public CustomerService() {
        super(Customer.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}