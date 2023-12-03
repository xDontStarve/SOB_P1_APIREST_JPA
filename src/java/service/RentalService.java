package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.LinkedList;
import java.util.List;
import model.entities.Customer;
import model.entities.Game;
import model.entities.Rental;
import model.entities.RentalDTO;
import model.entities.RentalGamesWrapper;

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
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postRental(RentalGamesWrapper rentalGamesWrapper){
        Rental rental = rentalGamesWrapper.getRental();
        Customer customer = em.find(Customer.class, rental.getCustomer().getId());
        //If customer does not exist abort.
        if (customer==null) return Response.status(Response.Status.NOT_FOUND).entity("Customer with this id cannot be found.").build();
        super.create(rental);
        List<Long> gameIDs = rentalGamesWrapper.getGameIDs();
        //Reset List (cannot pass the entire game list in json, too expensive)
        rental.setGames(new LinkedList());
        
        //Auxilar variables, gameList will get added all games in gameIDs
        Game game; List<Game> gameList = new LinkedList();
        //Set rental customer
        rental.setCustomer(customer);
        for (long id : gameIDs){
            game=em.find(Game.class, id);
            if (game!=null){
                gameList.add(game);
                rental.addGames(game);
                game.addRental(rental);
                em.merge(game);
            }else{
                return Response.status(Response.Status.NOT_FOUND).entity("Game with this id cannot be found.").build();
            }
        }
        rental.setGames(gameList);
        em.merge(rental);
        return Response.status(Response.Status.CREATED).entity(new RentalDTO(rental)).build();
    }
    
    
    
    @GET
    @Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRental(@PathParam("id") Long id){
        Rental rental = em.find(Rental.class, id);
        if (rental==null){
            return Response.status(Response.Status.NOT_FOUND).entity("No rental found with this id.").build();
        }
        RentalDTO rentalDTO = new RentalDTO(rental);
        return Response.status(Response.Status.OK).entity(rentalDTO).build();
    }
}
