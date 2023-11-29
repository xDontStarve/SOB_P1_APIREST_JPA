package service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.entities.Customer;

/**
 *
 * @author Jialiang
 */
public class CustomerService extends AbstractFacade<Customer>{
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
