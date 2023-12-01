package service;

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
@Path("game")
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