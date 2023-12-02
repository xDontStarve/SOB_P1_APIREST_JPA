package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Comment;
import model.entities.Rental;
import model.entities.RentalDTO;

/**
 *
 * @author Jialiang Chen
 */
@Stateless
@Path("/rest/api/v1/rental")
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
    
    //@POST
    //@Produces(MediaType.APPLICATION_JSON)
    
    
    
    @GET
    @Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRental(@PathParam("id") Long id){
        Query query = em.createNamedQuery("findRentalById");
        query.setParameter("id", id);
        if (query.getResultList().isEmpty()){
            return Response.status(Response.Status.NOT_FOUND).entity("No rental found with this id.").build();
        }
        RentalDTO rentalDTO = new RentalDTO((Rental) query.getResultList().get(0));
        return Response.status(Response.Status.OK).entity(rentalDTO).build();
    }
}
