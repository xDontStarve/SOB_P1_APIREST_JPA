package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import model.entities.Customer;
import model.entities.CustomerDTO;

/**
 *
 * @author Jialiang Chen
 */
@Stateless
@Path("/rest/api/v1/customer")
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
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomers(){
        List<Customer> customers = super.findAll();
        List<CustomerDTO> customerDTOs = new LinkedList<CustomerDTO>();
        for(Customer c : customers){
            customerDTOs.add(new CustomerDTO(c));
        }
        return Response.ok(customerDTOs).build();
    }
    
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomer(@PathParam("id") Long id){
        Query query = em.createNamedQuery("findById");
        query.setParameter("id", id);
        List<Customer> customers = query.getResultList();
        //Only 1 customer may be found with the ID.
        if (!customers.isEmpty()){
            CustomerDTO customerDTO = new CustomerDTO(customers.get(0));
            return Response.status(Response.Status.OK).entity(customerDTO).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("Customer with this id does not exist.").build();
    }
    
    @PUT
    @Path("/{id}")
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putCustomer(@PathParam("id") Long id, Customer c){
        Query query = em.createNamedQuery("findById");
        query.setParameter("id", id);
        if (query.getResultStream().toList().isEmpty()){
            em.persist(c);
        }else{
            super.edit(c);
        }
        return Response.ok().entity(new CustomerDTO(c)).build();
    }
}