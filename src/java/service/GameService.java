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
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Game;

/**
 *
 * @author Jialiang Chen
 */
@Stateless
@Path("/rest/api/v1/game")
public class GameService extends AbstractFacade<Game> {
    
    @PersistenceContext(unitName = "Homework1PU")
    private EntityManager em;
    
    public GameService() {
        super(Game.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    @POST
    @Secured
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response addGame(Game game){
        Query query = em.createNamedQuery("findSameGame");
            query.setParameter("genre", game.getGenre());
            query.setParameter("console", game.getConsole());
            query.setParameter("address", game.getAddress());
            query.setParameter("description", game.getDescription());
            query.setParameter("isAvailable", game.getIsAvailable());
            query.setParameter("name", game.getName());
            query.setParameter("price", game.getPrice());
        Game foundGame;
        if (super.find(game.getId())==null && query.getResultStream().toList().isEmpty()){
            super.create(game);
            return Response.status(Response.Status.CREATED).build();
        }else{
            foundGame = (Game) query.getResultStream().toList().get(0);
            foundGame.setUnits(game.getUnits());
            em.merge(foundGame);
            return Response.status(Response.Status.OK).entity("Added the units specified").build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findGameGenreConsole(@QueryParam("genre") String genre, @QueryParam("console") String console){
        Game.Console gameConsole;
        Game.Genre gameGenre;
        try {
        // Validate and convert genre parameter
            if (genre != null) {
                gameGenre = Game.Genre.valueOf(genre);
            }
        // Validate and convert console parameter
            if (console != null) {
                gameConsole = Game.Console.valueOf(console);
            }
        } catch (IllegalArgumentException e) {
        // Handle invalid enum constant
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid parameters. Genre or console value is not valid.").build();
        }
        Query query;
        if (console==null && genre!=null){
        // Return all because no params set
            query = em.createNamedQuery("findGamesByGenre");
            gameGenre = Game.Genre.valueOf(genre);
            query.setParameter("genre", gameGenre);
        }
        else if (console!=null && genre==null){
        // Return Game with matching genre
            query = em.createNamedQuery("findGamesByConsole");
            gameConsole = Game.Console.valueOf(console);
            query.setParameter("console", gameConsole);
        }
        else if (console!=null && genre!=null){
        // Return Game with matching console
            query = em.createNamedQuery("findGamesByGenreAndConsole");
            gameConsole = Game.Console.valueOf(console);
            gameGenre = Game.Genre.valueOf(genre);
            query.setParameter("genre", gameGenre);
            query.setParameter("console", gameConsole);
        }else{
        // Return game with matching genre and console.
            query = em.createNamedQuery("findAllGames");
        }
        return Response.ok(query.getResultList()).build();
    }
}