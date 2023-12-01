package service;

import authn.Secured;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.entities.Comment;
import model.entities.Game;

/**
 *
 * @author Jialiang
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
        if (super.find(game.getId())==null && query.getResultStream().toList().isEmpty()){
            super.create(game);
            return Response.status(Response.Status.CREATED).build();
        }else{
            return Response.status(Response.Status.CONFLICT).entity("The game already exists").build();
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findGameGenreConsole(@QueryParam("genre") String genre, @QueryParam("console") String console){
        Game.Console gameConsole;
        Game.Genre gameGenre;
        Query query;
        if (console==null && genre!=null){
            query = em.createNamedQuery("findGamesByGenre");
            gameGenre = Game.Genre.valueOf(genre);
            query.setParameter("genre", gameGenre);
        }
        else if (console!=null && genre==null){
            query = em.createNamedQuery("findGamesByConsole");
            gameConsole = Game.Console.valueOf(console);
            query.setParameter("console", gameConsole);
        }
        else if (console!=null && genre!=null){
            query = em.createNamedQuery("findGamesByGenreAndConsole");
            gameConsole = Game.Console.valueOf(console);
            gameGenre = Game.Genre.valueOf(genre);
            query.setParameter("genre", gameGenre);
            query.setParameter("console", gameConsole);
        }else{
            query = em.createNamedQuery("findAllGames");
        }
        return Response.ok(query.getResultList()).build();
    }
}